package com.example.moim

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.viewpager2.widget.ViewPager2
import com.example.moim.adpaters.BottomViewPagerAdapter
import com.example.moim.adpaters.MainViewPagerAdapter
import com.example.moim.databinding.ActivityMainBinding

class MainActivity : BaseActivity() {

    lateinit var binding: ActivityMainBinding
//    lateinit var mAdapter : MainViewPagerAdapter
    lateinit var mBottomAdapter : BottomViewPagerAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
//        mAdapter = MainViewPagerAdapter(supportFragmentManager)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {
    }

    override fun setValues() {
        titleTxt.text = "모임찾기"

        mBottomAdapter = BottomViewPagerAdapter(this)
        binding.bottomViewPager.adapter = mBottomAdapter

        binding.bottomViewPager.registerOnPageChangeCallback(
            object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    binding.bottomNav.menu.getItem(position).isChecked = true
                    titleTxt.text = when (position){
                        0 -> "모임찾기"
                        1 -> "유료클래스"
                        2 -> "내모임"
                        else -> "주변검색"
                    }
                }
            })

        binding.bottomNav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.bnv_tab1 -> binding.bottomViewPager.currentItem = 0
                R.id.bnv_tab2 -> binding.bottomViewPager.currentItem = 1
                R.id.bnv_tab3 -> binding.bottomViewPager.currentItem = 2
                R.id.bnv_tab4 -> binding.bottomViewPager.currentItem = 3
            }
            return@setOnItemSelectedListener true
        }

    }

}