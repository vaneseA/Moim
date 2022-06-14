package com.example.moim

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.moim.api.APIList
import com.example.moim.api.ServerApi
import retrofit2.Retrofit

abstract class BaseActivity : AppCompatActivity() {

    lateinit var mContext : Context

    lateinit var retrofit : Retrofit
    lateinit var apiList : APIList

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mContext = this

        retrofit = ServerApi.getRetrofit()
        apiList = retrofit.create(APIList::class.java)
    }

    abstract fun setupEvents()

    abstract fun setValues()
}