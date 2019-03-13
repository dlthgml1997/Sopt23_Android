package com.androidstudy.homework03.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.androidstudy.homework03.R
import com.androidstudy.homework03.data.MyItemData
import kotlinx.android.synthetic.main.rv_item_grid_view.view.*
import org.w3c.dom.Text

class GridRecyclerViewAdapter(val ctx: Context, val dataList: ArrayList<MyItemData>) :
    RecyclerView.Adapter<GridRecyclerViewAdapter.Holder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view: View = LayoutInflater.from(ctx).inflate(R.layout.rv_item_grid_view, parent, false)
        return Holder(view)
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.counter.text = dataList[position].counter.toString()
        if (!dataList[position].isLike) holder.likeImage.visibility = View.GONE
    }

    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val counter: TextView = itemView.findViewById(R.id.tv_rv_item_grid_counter) as TextView
        val likeImage: ImageView = itemView.findViewById(R.id.iv_rv_item_grid_islike) as ImageView
    }
}