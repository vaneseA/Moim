package com.example.moim.ui.profile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.moim.BaseActivity
import com.example.moim.R
import com.example.moim.databinding.ActivityProfileBinding

class ProfileActivity : BaseActivity() {
    lateinit var binding: ActivityProfileBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {
        binding.profileImg.setOnClickListener {
            val myIntent = Intent(this,ImageProfileActivity::class.java)
            startActivity(myIntent)

        }
    }

    override fun setValues() {

    }

}