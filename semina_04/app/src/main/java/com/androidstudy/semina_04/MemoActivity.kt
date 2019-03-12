package com.androidstudy.semina_04

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import com.androidstudy.semina_04.db.SharedPreferenceController
import kotlinx.android.synthetic.main.activity_memo.*

class MemoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_memo)
        setOnBtnClickListener()
    }

    fun setOnBtnClickListener(){
        btn_memo_act_logout.setOnClickListener {
            SharedPreferenceController.clearUserSharedPreferences(this)

            val intent: Intent = Intent(this,LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}