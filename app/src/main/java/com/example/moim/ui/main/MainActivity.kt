package com.example.moim.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.viewpager2.widget.ViewPager2
import com.example.moim.BaseActivity
import com.example.moim.R
import com.example.moim.adpaters.BottomViewPagerAdapter
import com.example.moim.databinding.ActivityMainBinding
import com.example.moim.ui.*
import com.example.moim.ui.profile.ProfileSetActivity
import com.example.moim.utils.GlobalData
import com.google.android.material.navigation.NavigationView
import de.hdodenhof.circleimageview.CircleImageView


class MainActivity : BaseActivity() {
    private var profileImg: CircleImageView? = null
    private var nicknameEdt: TextView? = null
    private var userId: String? = null
    lateinit var binding: ActivityMainBinding


    lateinit var mBottomAdapter: BottomViewPagerAdapter
    lateinit var toggle: ActionBarDrawerToggle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {

    }

    override fun setValues() {
    /////클리어 필요
        when (GlobalData.loginUser!!.provider) {
            "kakao" -> {}
            "facebook" -> {}
            else -> profileImg?.visibility = View.GONE
        }
        titleTxt.text = "모임찾기"



        mBottomAdapter = BottomViewPagerAdapter(this)
        binding.bottomViewPager.adapter = mBottomAdapter

        binding.bottomViewPager.registerOnPageChangeCallback(
            object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    binding.bottomNav.menu.getItem(position).isChecked = true
                    titleTxt.text = when (position) {
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


        val drawerLayout: DrawerLayout = findViewById(R.id.drawerLayout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val header = navView.getHeaderView(0)
        nicknameEdt = header.findViewById(R.id.nicknameTxt)
        profileImg = header.findViewById(R.id.profileImg)

        //네비게이션 헤더
        Glide.with(mContext)
            .load(GlobalData.loginUser!!.profileImg)
            .into(profileImg!!)
        nicknameEdt?.text = GlobalData.loginUser!!.nickname

        header.findViewById<RelativeLayout>(R.id.profileBtn)
        header.setOnClickListener {
            val myIntent = Intent(mContext, ProfileSetActivity::class.java)
            startActivity(myIntent)
        }

        toggle = ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        navView.setNavigationItemSelectedListener {
            val myIntent = Intent(mContext, LoginActivity::class.java)
            myIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK

            when (it.itemId) {
                R.id.navigation_menu_interest -> Toast.makeText(
                    applicationContext,
                    "Clicked navigation_menu_interest",
                    Toast.LENGTH_SHORT
                ).show()
                R.id.navigation_menu_favorite_meet -> startActivity(Intent(mContext,
                    CheckedActivity::class.java))
                R.id.navigation_menu_recent_meet -> startActivity(Intent(mContext, RecentActivity::class.java))
                R.id.navigation_menu__premium_meet -> startActivity(Intent(mContext,
                    PremiumActivity::class.java))
                R.id.navigation_menu_mk_charge_class -> startActivity(Intent(mContext,
                    NewChargeClassActivity::class.java))
                R.id.navigation_menu_setting -> startActivity(Intent(mContext,
                    MyPageSettingActivity::class.java))
                R.id.navigation_menu_logout -> startActivity(myIntent)
            }
            true
        }


    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if (toggle.onOptionsItemSelected(item)) {
            return true
        }

        return super.onOptionsItemSelected(item)
    }

//    fun setUserData() {
//        Glide.with(mContext)
//            .load(GlobalData.loginUser!!.profileImg)
////            .into(profileImg)
//        nicknameTxt?.text = GlobalData.loginUser!!.nickname
//    }
}