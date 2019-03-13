package com.androidstudy.semina_03

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.androidstudy.semina_03.adapter.KakaoTalkRoomRecyclerViewAdapter
import com.androidstudy.semina_03.data.KakaoTalkRoomData
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var kakaoTalkRoomRecyclerViewAdapter: KakaoTalkRoomRecyclerViewAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setRecyclerView()
    }

    fun setRecyclerView(){
        var dataList: ArrayList<KakaoTalkRoomData> = ArrayList()
        dataList.add(KakaoTalkRoomData("[DoIT_SOPT] 안드로이드파트","안녕",23,"오후 9:09"))
        dataList.add(KakaoTalkRoomData("[DoIT_SOPT] 서버파트","반가워 ",12,"오후 9:09"))
        dataList.add(KakaoTalkRoomData("[Open_SOPT] 안드로이드파트","잘부탁해",0,"오후 9:09"))
        dataList.add(KakaoTalkRoomData("[Open_SOPT] 안드로이드파트","열심히하자",1,"오후 9:09"))
        dataList.add(KakaoTalkRoomData("[DoIT_SOPT] 디자인파트","리사이클러뷰 더 알아보자~!",123,"오후 9:09"))
        dataList.add(KakaoTalkRoomData("[Open_SOPT] 안드로이드파트","온액티비티크리에이티드",243,"오후 9:09"))
        dataList.add(KakaoTalkRoomData("[Open_SOPT] 기획파트","이소힁",2,"오후 9:09"))
        dataList.add(KakaoTalkRoomData("[DoIT_SOPT] 안드로이드파트","안드로이드 스터디",8,"오후 9:09"))
        dataList.add(KakaoTalkRoomData("[DoIT_SOPT] ios파트","열심히",97,"오후 9:09"))

        kakaoTalkRoomRecyclerViewAdapter = KakaoTalkRoomRecyclerViewAdapter(this, dataList)
        rv_main_frag_kakao_talk_room_list.adapter = kakaoTalkRoomRecyclerViewAdapter
        rv_main_frag_kakao_talk_room_list.layoutManager = LinearLayoutManager(this)
    }
}