package com.androidstudy.semina_08

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTransaction
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.toast

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        addFragment(MyFragment.getInstance("안녕하세요","안드로이드 파트원 이소희입니다."))
        setOnBtnClickListener()
    }

    private fun addFragment(fragment: Fragment){
        val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.fl_main_act_fragment,fragment)
        transaction.commit()
    }

    fun setOnBtnClickListener(){
        btn_main_act_show_first_dialog.setOnClickListener {
            val firstDialog : FirstDialogFragment = FirstDialogFragment()
            firstDialog.show(supportFragmentManager,"first dialog")
        }
    }

    fun showMainActToastMessage(){
        toast("메인 액티비티 함수 호출")
    }
}
