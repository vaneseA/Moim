package com.example.moim.ui

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.moim.BaseActivity
import com.example.moim.R
import com.example.moim.databinding.ActivityMyPageSettingBinding

class MyPageSettingActivity : BaseActivity(){
    lateinit var binding: ActivityMyPageSettingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_my_page_setting)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {

    }

    override fun setValues() {

    }
    }
