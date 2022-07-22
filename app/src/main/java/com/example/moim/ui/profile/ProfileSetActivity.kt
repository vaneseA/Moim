package com.example.moim.ui.profile

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.moim.BaseActivity
import com.example.moim.R
import com.example.moim.databinding.ActivityProfileSetBinding

class ProfileSetActivity : BaseActivity() {
    lateinit var binding:ActivityProfileSetBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_profile_set)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {

    }

    override fun setValues() {



    }
}