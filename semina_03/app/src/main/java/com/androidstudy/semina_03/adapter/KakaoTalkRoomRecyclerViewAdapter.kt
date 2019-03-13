package com.androidstudy.semina_03.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.androidstudy.semina_03.R
import com.androidstudy.semina_03.data.KakaoTalkRoomData

class KakaoTalkRoomRecyclerViewAdapter(val ctx: Context, val dataList: ArrayList<KakaoTalkRoomData>) :
    RecyclerView.Adapter<KakaoTalkRoomRecyclerViewAdapter.Holder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view: View = LayoutInflater.from(ctx).inflate(R.layout.rv_item_kakao_talk_room, parent, false)
        return Holder(view)
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.title.text = dataList[position].title
        holder.like.text = dataList[position].like
        holder.like_cnt.text = dataList[position].like_cnt.toString()
        holder.time.text = dataList[position].time
    }

    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.tv_rv_item_board_title) as TextView
        val like: TextView = itemView.findViewById(R.id.tv_rv_item_board_like) as TextView
        val like_cnt: TextView = itemView.findViewById(R.id.tv_rv_item_board_like_cnt) as TextView
        val time: TextView = itemView.findViewById(R.id.tv_rv_item_board_date) as TextView
    }
}