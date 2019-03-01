package com.androidstudy.fragmentpractice


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import java.util.zip.Inflater

class CategoryFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val categoryFragmentView : View = inflater!!.inflate(R.layout.fragment_category,container,false)

        return categoryFragmentView
    }

    // fragment View를 건들 수 있는 메소드
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
//rl_main_act_home_frag_background. backgroundColor = Color.parseColor("#BBDEFB")

    }
}