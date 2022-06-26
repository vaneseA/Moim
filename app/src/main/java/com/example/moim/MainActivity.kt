package com.example.moim

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.moim.adpaters.MainViewPagerAdapter
import com.example.moim.databinding.ActivityMainBinding

class MainActivity : BaseActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var mAdapter : MainViewPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        mAdapter = MainViewPagerAdapter(supportFragmentManager)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {
    }

    override fun setValues() {
        binding.mainViewPager.adapter = mAdapter
        binding.mainTabLayout.setupWithViewPager(binding.mainViewPager)
    }

}