package com.example.moim.adpaters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.moim.fragments.*

class BottomViewPagerAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {

//    override fun getPageTitle(position: Int): CharSequence? {
//        return when (position) {
//            0 -> "모임"
//            1 -> "유료클래스"
//            2 -> "내모임"
//            else -> "주변검색"
//        }
//    }
    override fun getItemCount() = 4


    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> BottomMeetFragment()
            1 -> BottomChargeFragment()
            2 -> BottomMyPageFragment()
            else -> BottomLocationFragment()
        }
    }
}