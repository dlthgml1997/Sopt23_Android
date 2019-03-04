package com.androidstudy.fragmentpractice


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import java.util.zip.Inflater
//싱글톤 : 생성자가 여러 차례 호출되더라도 실제로 생성되는 객체는 하나이고 최초 생성 이후에 호출된 생성자는 최초의 생성자가 생성한 객체를 리턴한다.
class MyShoppingFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    val mypageFragmentView : View = inflater!!.inflate(R.layout.fragment_myshopping,container,false)

        return mypageFragmentView
    }
    companion object {
        private var instance : MyShoppingFragment? = null

        @Synchronized
        fun getInstance() : MyShoppingFragment {
            if (instance == null) {
                instance = MyShoppingFragment()
            }
            return instance!!
        }
    }

    // fragment View를 건들 수 있는 메소드
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
//rl_main_act_home_frag_background. backgroundColor = Color.parseColor("#BBDEFB")

    }
}