package com.androidstudy.semina_08

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_my.*

class MyFragment : Fragment(){
    companion object { //싱글톤
        private var instance : MyFragment? =null
        @Synchronized
        fun getInstance(title: String, content : String) : MyFragment{
            if(instance == null){ //null 처리
                instance = MyFragment().apply {
                    arguments = Bundle().apply {
                        putString("title",title)
                        putString("content",content)
                    }
                }
                //apply 사용 안하면
                /*
                instance = MyFragment()
                instance!!.arguments = Bundle()
                instance!!.arguments!!.putString("title",title)
                instance!!.arguments!!.putString("content",content)
                */
            }
            return instance!! // instance반환
        }

        var title: String? = null
        var content : String? = null

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            title = it.getString("title")
            content = it.getString("content")
        }
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        tv_my_fragment_title.text = title
        tv_my_fragment_content.text = content
    }


}
