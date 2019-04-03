package com.sopt.semina_06.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.sopt.semina_06.CommentActivity
import com.sopt.semina_06.R
import com.sopt.semina_06.R.id.img_rv_item_board_like
import com.sopt.semina_06.data.BoardData
import com.sopt.semina_06.db.SharedPreferenceController
import com.sopt.semina_06.network.ApplicationController
import com.sopt.semina_06.network.NetworkService
import com.sopt.semina_06.post.PostLikedResponse
import okhttp3.MediaType
import okhttp3.RequestBody
import org.jetbrains.anko.startActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class BoardRecyclerViewAdapter(val ctx: Context, val dataList: ArrayList<BoardData>) : RecyclerView.Adapter<BoardRecyclerViewAdapter.Holder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(ctx).inflate(R.layout.rv_item_board, parent, false)
        return Holder(view)
    }
    val networkService: NetworkService by lazy {
        ApplicationController.instance.networkService
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.title.text = dataList[position].b_title
        holder.like_cnt.text = dataList[position].b_like.toString()
        if (dataList[position].like == true){
            //하트 채워진것
            holder.img_rv_item_board_like.setImageResource(R.drawable.ic_heart_actived_24dp)
        } else {
            //하트 빈거
            holder.img_rv_item_board_like.setImageResource(R.drawable.ic_heart_unselected_24dp)
        }
        val requestOptions = RequestOptions()
//        requestOptions.placeholder(R.drawable.기본적으로 띄울 이미지)
//        requestOptions.error(R.drawable.에러시 띄울 이미지)
//        requestOptions.override(150)
        Glide.with(ctx)
            .setDefaultRequestOptions(requestOptions)
            .load(dataList[position].b_photo)
            .thumbnail(0.5f)
            .into(holder.image)

        holder.btn_show_detailed.setOnClickListener {
          ctx.startActivity<CommentActivity>("b_id" to dataList[position].b_id)
        }

            postLikedResponse(holder.img_rv_item_board_like, position)
        holder.img_rv_item_board_like.setOnClickListener {
            postLikedResponse(holder.img_rv_item_board_like, position)
        }

    }

    private fun postLikedResponse(view : View, position : Int){
        val token = SharedPreferenceController.myAuth
        val postLikedResponse = networkService.postLikedResponse("application/json", token ,30)
        postLikedResponse.enqueue(object : Callback<PostLikedResponse> {
            override fun onFailure(call: Call<PostLikedResponse>, t: Throwable) {
                Log.e("board list fail", t.toString())
            }

            override fun onResponse(call: Call<PostLikedResponse>, response: Response<PostLikedResponse>) {

                Log.v("img_like","하트통신!!")
                if (response.isSuccessful){
                    val temp : BoardData = response.body()!!.data
                    val like= temp.like
                    if (like == true){
                        (view as ImageView).setImageResource(R.drawable.ic_heart_actived_24dp)
                        dataList[position].like = true
                        dataList[position].b_like++
                    } else {
                        (view as ImageView).setImageResource(R.drawable.ic_heart_unselected_24dp)
                        dataList[position].like = false
                        dataList[position].b_like--
                    }
                }
            }
        })
    }


    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val img_rv_item_board_like : ImageView = itemView.findViewById(R.id.img_rv_item_board_like) as ImageView
        val title: TextView = itemView.findViewById(R.id.tv_rv_item_board_title) as TextView
        val like_cnt: TextView = itemView.findViewById(R.id.tv_rv_item_board_like_cnt) as TextView
        val image: ImageView = itemView.findViewById(R.id.iv_rv_item_board_image) as ImageView
        val btn_show_detailed : RelativeLayout = itemView.findViewById(R.id.btn_rv_item_board_go_comment) as RelativeLayout
    }
}