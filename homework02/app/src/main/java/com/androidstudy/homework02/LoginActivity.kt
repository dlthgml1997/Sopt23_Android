package com.androidstudy.homework02

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.androidstudy.homework02.R.id.et_login_act_id
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.startActivityForResult
import org.jetbrains.anko.toast

class LoginActivity : AppCompatActivity() {
    private val REQUEST_CODE_SIGN_IN = 777
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        setOnBtnClickListener()
    }

    fun setOnBtnClickListener() {
        btn_login_act_sign_up.setOnClickListener {
            var id = et_login_act_id.text.toString()
            val intent: Intent = Intent(this, SignUpActivity::class.java)
            intent.putExtra("loginId", id)
            startActivityForResult(intent, REQUEST_CODE_SIGN_IN)

            //anko 버전
            //startActivityForResult<SignUpActivity>(REQUEST_CODE_SIGN_IN,"id" to id)
        }

        btn_login_act_sign_in.setOnClickListener { toast("login") }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE_SIGN_IN) {
            if (resultCode == Activity.RESULT_OK) {
                if (data != null) et_login_act_id.setText(data!!.getStringExtra("id"))
            }
        }
    }

}
