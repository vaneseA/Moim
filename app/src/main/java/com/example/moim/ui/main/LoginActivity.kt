package com.example.moim.ui.main

import android.content.Intent
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.moim.BaseActivity
import com.example.moim.R
import com.example.moim.databinding.ActivityLoginBinding
import com.example.moim.ui.signup.SignUpActivity

class LoginActivity : BaseActivity() {

    lateinit var binding : ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {
        binding.loginBtn.setOnClickListener {

        }

        binding.signUpBtn.setOnClickListener {
            val myIntent = Intent(mContext, SignUpActivity::class.java)
            startActivity(myIntent)
        }
    }

    override fun setValues() {

    }
}