package com.example.moim.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import com.example.moim.BaseActivity
import com.example.moim.MainActivity
import com.example.moim.R
import com.example.moim.models.BasicResponse
import com.example.moim.ui.main.LoginActivity
import com.example.moim.utils.ContextUtil
import com.example.moim.utils.GlobalData
import com.kakao.sdk.common.util.Utility
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SplashActivity : BaseActivity() {

    var isTokenOk = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        getKeyHash()
        setupEvents()
        setValues()
    }

    override fun setupEvents() {
        apiList.getRequestMyInfo(ContextUtil.getLoginToken(mContext)).enqueue(object : Callback<BasicResponse>{
            override fun onResponse(call: Call<BasicResponse>, response: Response<BasicResponse>) {
                if (response.isSuccessful) {
                    val br = response.body()!!

                    isTokenOk = true
                    GlobalData.loginUser = br.data.user
                }
            }

            override fun onFailure(call: Call<BasicResponse>, t: Throwable) {

            }
        })

    }

    override fun setValues() {
        val myHandler = Handler(Looper.getMainLooper())

        myHandler.postDelayed({

            val myIntent: Intent
            if(isTokenOk && ContextUtil.getAutoLogin(mContext)) {
                myIntent = Intent(mContext, MainActivity::class.java)
            }
            else {
                myIntent = Intent(mContext, LoginActivity::class.java)
            }
            startActivity(myIntent)
            finish()
        }, 1800)
    }
    fun getKeyHash() {
        var keyHash = Utility.getKeyHash(this)
        Log.d("kakao_keyHash", keyHash)
    }
}