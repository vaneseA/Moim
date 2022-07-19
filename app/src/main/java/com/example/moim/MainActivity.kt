package com.example.moim

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.viewpager2.widget.ViewPager2
import com.example.moim.adpaters.BottomViewPagerAdapter
import com.example.moim.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationView

class MainActivity : BaseActivity() {

    lateinit var binding: ActivityMainBinding
//    lateinit var mAdapter : MainViewPagerAdapter
    lateinit var mBottomAdapter : BottomViewPagerAdapter
    lateinit var toggle : ActionBarDrawerToggle
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


        val drawerLayout : DrawerLayout = findViewById(R.id.drawerLayout)
        val navView : NavigationView = findViewById(R.id.nav_view)
        val header = navView.getHeaderView(0)

        header.findViewById<RelativeLayout>(R.id.profileBtn)
        header.setOnClickListener{
            val myIntent = Intent(mContext,ProfileSetActivity::class.java )
            startActivity(myIntent)
        }

        toggle = ActionBarDrawerToggle(this,drawerLayout,R.string.open,R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        navView.setNavigationItemSelectedListener{
            when(it.itemId){
                R.id.navigation_menu_interest -> Toast.makeText(applicationContext,"Clicked navigation_menu_interest", Toast.LENGTH_SHORT).show()
                R.id.navigation_menu_favorite_meet -> Toast.makeText(applicationContext,"Clicked navigation_menu_favorite_meet", Toast.LENGTH_SHORT).show()
                R.id.navigation_menu_recent_meet -> Toast.makeText(applicationContext,"Clicked navigation_menu_recent_meet", Toast.LENGTH_SHORT).show()
                R.id.navigation_menu__premium_meet -> Toast.makeText(applicationContext,"Clicked navigation_menu__premium_meet", Toast.LENGTH_SHORT).show()
                R.id.navigation_menu_mk_charge_class -> Toast.makeText(applicationContext,"Clicked navigation_menu_mk_charge_class", Toast.LENGTH_SHORT).show()
                R.id.navigation_menu_setting -> Toast.makeText(applicationContext,"Clicked navigation_menu_setting", Toast.LENGTH_SHORT).show()
                R.id.navigation_menu_logout -> Toast.makeText(applicationContext,"Clicked navigation_menu_logout", Toast.LENGTH_SHORT).show()

            }
            true
        }


    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if(toggle.onOptionsItemSelected(item)){
            return true
        }

        return super.onOptionsItemSelected(item)
    }
}