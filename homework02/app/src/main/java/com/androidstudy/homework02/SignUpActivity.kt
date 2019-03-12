package com.androidstudy.homework02

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_signup.*
import org.jetbrains.anko.startActivityForResult

class SignUpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
        setOnBtnClickListener()
        setInitEditText()
    }

    fun setInitEditText(){
        //LoginActivity에서 받아 온 값 et 초기값으로 설정하기
        et_signup_act_id.setText(intent!!.getStringExtra("loginId"))

    }

    fun setOnBtnClickListener(){
        btn_signup_act_submit.setOnClickListener {
            //LoginActivity에서 startActivityForResult를 했기 때문에
            val intent: Intent = Intent()
            intent.putExtra("id", et_signup_act_id.text.toString())
            setResult(Activity.RESULT_OK, intent)
            finish()
        }
    }
}