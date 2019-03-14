package com.androidstudy.homework03

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.widget.GridLayout
import com.androidstudy.homework03.R.id.rv_main_act_grid_view
import com.androidstudy.homework03.adapter.GridRecyclerViewAdapter
import com.androidstudy.homework03.data.MyItemData
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var gridRecyclerViewAdapter: GridRecyclerViewAdapter
    var dataList: ArrayList<MyItemData> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        makeData()
        setRecyclerView()
    }

    fun makeData() {
        for(i in 1..30){
            if(i%2 ==0) dataList.add(MyItemData(i,true))
            else dataList.add(MyItemData(i,false))
        }
    }

    fun setRecyclerView() {
        gridRecyclerViewAdapter = GridRecyclerViewAdapter(this, dataList)
        rv_main_act_grid_view.adapter = gridRecyclerViewAdapter
        rv_main_act_grid_view.layoutManager = GridLayoutManager(this, 3)
    }

}
