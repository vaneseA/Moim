package com.example.moim

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.viewpager2.widget.ViewPager2
import com.example.moim.adpaters.BottomViewPagerAdapter
import com.example.moim.adpaters.MainViewPagerAdapter
import com.example.moim.databinding.ActivityMainBinding

class MainActivity : BaseActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var mAdapter : MainViewPagerAdapter
    lateinit var mBottomAdapter : BottomViewPagerAdapter
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
        mBottomAdapter = BottomViewPagerAdapter(this)
        binding.bottomViewPager.adapter = mBottomAdapter

        binding.mainViewPager.registerOnPageChangeCallback(
            object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    binding.bottomNav.menu.getItem(position).isChecked = true
                }
            })

        binding.bottomNav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.bnv_tab1 -> binding.mainViewPager.currentItem = 0
                R.id.bnv_tab2 -> binding.mainViewPager.currentItem = 1
                R.id.bnv_tab3 -> binding.mainViewPager.currentItem = 2
            }
            return@setOnItemSelectedListener true
        }

    }

}