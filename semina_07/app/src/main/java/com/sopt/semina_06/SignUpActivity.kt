package com.sopt.semina_06

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log


import com.google.gson.JsonObject
import com.google.gson.JsonParser
import com.sopt.semina_06.network.ApplicationController
import com.sopt.semina_06.network.NetworkService
import com.sopt.semina_06.post.PostSignUpResponse
import kotlinx.android.synthetic.main.activity_signup.*
import org.jetbrains.anko.toast
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignUpActivity : AppCompatActivity() {
    val netWorkService : NetworkService by lazy{
        ApplicationController.instance.networkService
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        setOnBtnClickListener()
    }

    private fun setOnBtnClickListener() {
        btn_sign_up_act_complete.setOnClickListener {
            getSignUpResponseData()
        }
        btn_sign_up_act_close.setOnClickListener {
            finish()
        }
    }
        private fun getSignUpResponseData(){
//edittext값 받아오기
        val input_name : String = et_sign_up_act_name.text.toString()
        val input_pw : String = et_sign_up_act_pw.text.toString()
        val input_email  : String = et_sign_up_act_email.text.toString()
        val input_part : String = et_sign_up_act_part.text.toString()
//json형식의 개체만들기
        var jsonObject = JSONObject()
        jsonObject.put("name", input_name)
        jsonObject.put("email", input_email)
        jsonObject.put("password", input_pw)
        jsonObject.put("part", input_part)
//Gson 라이브러리의 Json Parser을 통해 객체를 Json으로!
        val gsonObject = JsonParser().parse(jsonObject.toString()) as JsonObject

        val postSignUpResponse : Call<PostSignUpResponse> =
            netWorkService.PostSignUpResponse("application/json",gsonObject)
    postSignUpResponse.enqueue(object: Callback<PostSignUpResponse>{
        override fun onFailure(call: Call<PostSignUpResponse>, t: Throwable) {
            Log.e("Sign up fail",t.toString())
        }

        override fun onResponse(call: Call<PostSignUpResponse>, response: Response<PostSignUpResponse>) {
            if(response.isSuccessful){
                toast(response.body()!!.message)
                finish()
            }
        }
    })
    }
    }
