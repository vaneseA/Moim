package com.example.moim.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.example.moim.BaseActivity
import com.example.moim.R
import com.example.moim.ui.main.LoginActivity

class SplashActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {

    }

    override fun setValues() {
        val myHandler = Handler(Looper.getMainLooper())

        myHandler.postDelayed({

            val myIntent: Intent
            myIntent = Intent(mContext, LoginActivity::class.java)
//여기서 스플래쉬 다음 액티비티를 설정할수 있다
            startActivity(myIntent)
        }, 1800)
    }
}