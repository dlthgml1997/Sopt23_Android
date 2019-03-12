package com.androidstudy.semina_04

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.androidstudy.semina_04.db.SharedPreferenceController
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        setOnBtnClickListener()

        if (SharedPreferenceController.getUserID(this).isNotEmpty()){
            //anko ver
            //startActivity<MemoActivity>()

            //original ver
            val intent : Intent = Intent(this,MemoActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    fun setOnBtnClickListener(){
        btn_login_act_sign_in.setOnClickListener {
            val input_id : String = et_login_act_id.text.toString()
            val input_pw : String = et_login_act_pw.text.toString()
            if(input_id.isNotEmpty()&& input_pw.isNotEmpty()) {
                SharedPreferenceController.setUserID(this, input_id)
                SharedPreferenceController.setUserPW(this, input_pw)

                val intent: Intent = Intent(this,MemoActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }
}
