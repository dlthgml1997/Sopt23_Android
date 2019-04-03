package com.sopt.semina_06

import android.Manifest
import android.app.Activity
import android.content.CursorLoader
import android.content.Intent
import android.content.pm.PackageManager
import android.database.Cursor
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.util.Log
import com.bumptech.glide.Glide
import com.sopt.semina_06.db.SharedPreferenceController
import com.sopt.semina_06.network.ApplicationController
import com.sopt.semina_06.network.NetworkService
import com.sopt.semina_06.post.postWriteBoardResponse
import kotlinx.android.synthetic.main.activity_write.*
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import org.jetbrains.anko.toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File

class WriteActivity : AppCompatActivity() {
    val REQUEST_CODE_SELECT_IMAGE: Int = 1004 //startActivityForResult에서 쓰임 다른 액티비티 띄운다음에 결과를 보내줌.
    val My_READ_STORAGE_REQUEST_CODE :Int = 7777

    var imageURI : String? = null

    val networkService: NetworkService by lazy {
        ApplicationController.instance.networkService
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_write)
        setOnBtnClickListener()
    }

    private fun setOnBtnClickListener() {
       btn_write_act_show_album.setOnClickListener {
           //권한 허용후 앨범을 열도록하는 메소드 호출
           requestReadExternalStoragePermission()
        }
        btn_write_act_complete.setOnClickListener {
            getWriteBoardResponse()
        }
    }

    //앨범에서 사진을 선택했을때 실행되는 메소드
    //startActivityForResult를 통해 실행한 액티비티에 대한 callback을 처리하는 메소드입니다!
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE_SELECT_IMAGE) {
            if (resultCode == Activity.RESULT_OK) {
                //data.data에는 앨범에서 선택한 사진의 Uri가 들어있습니다!! 그러니까 제대로 선택됐는지 null인지 아닌지를 체크!
                if(data != null ){
                    val selectImageUri : Uri = data.data
                    //Uri를 getRealPathFromURI라는 메소드를 통해 절대 경로를 알아내고, 인스턴스 변수 imageURI에 넣어줍니다!
                    imageURI = getRealPathFromURI(selectImageUri)

                    Glide.with(this@WriteActivity)
                        .load(selectImageUri)
                        .thumbnail(0.1f)
                        .into(iv_write_act_choice_image)
                }
            }
        }
    }

    //Uri에 대한 절대 경로를 리턴하는 메소드.
    fun getRealPathFromURI(content : Uri) : String {
        val proj = arrayOf(MediaStore.Images.Media.DATA)
        val loader : CursorLoader = CursorLoader(this, content, proj, null, null, null)
        val cursor : Cursor = loader.loadInBackground()
        val column_idx = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
        cursor.moveToFirst()
        val result = cursor.getString(column_idx)
        cursor.close()
        return result
    }

    private fun requestReadExternalStoragePermission(){
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
            if(ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.READ_EXTERNAL_STORAGE)){
                //사용자에게 왜 권한을 허용해야되는지에 메시지를 주기위한 로직.
            }else{
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),My_READ_STORAGE_REQUEST_CODE)
            }
        }else{
            showAlbum()
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResult: IntArray){
        if(requestCode == My_READ_STORAGE_REQUEST_CODE){
            if(grantResult.size >0 && grantResult[0] == PackageManager.PERMISSION_GRANTED){
                showAlbum()
            }
        }else{
            finish()
        }

    }

    private fun showAlbum(){
        val intent = Intent(Intent.ACTION_PICK)
        intent.type= android.provider.MediaStore.Images.Media.CONTENT_TYPE
        intent.data = android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        startActivityForResult(intent,REQUEST_CODE_SELECT_IMAGE)
    }


    private fun getWriteBoardResponse() {
        val input_title = et_write_act_title.text.toString()
        val input_contents = et_write_act_content.text.toString()
        if (input_title.isNotEmpty() && input_contents.isNotEmpty()) {
            //Multipart 형식은 String을 RequestBody 타입으로 바꿔줘야 합니다!
            val token = SharedPreferenceController.getAuthorization(this)
            var title  = RequestBody.create(MediaType.parse("text/plain"), input_title)
            var contents = RequestBody.create(MediaType.parse("text/plain"), input_contents)

            //아래 세줄 : 이미지 파일을 서버로 보내기 위해 MultipartBody.Part 형식으로 만드는 로직
            //imageURI는 앨범에서 선택한 이미지에 대한 절대 경로가 담겨있는 인스턴스 변수
            val file : File = File(imageURI)
            val requestfile : RequestBody = RequestBody.create(MediaType.parse("multipart/form-data"),file)
            val data : MultipartBody.Part =  MultipartBody.Part.createFormData("photo",file.name,requestfile)



            val postWriteBoardResponse =
                networkService.postWriteBoardResponse(token, title, contents, data)

            postWriteBoardResponse.enqueue(object : Callback<postWriteBoardResponse> {
                override fun onFailure(call: Call<postWriteBoardResponse>, t: Throwable) {
                    Log.e("write fail", t.toString())
                }

                override fun onResponse(call: Call<postWriteBoardResponse>, response: Response<postWriteBoardResponse>) {
                    if (response.isSuccessful) {
                        toast(response.body()!!.message)
                        finish()
                    }
                }
            })
        }
    }
}
