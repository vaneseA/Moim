package com.example.moim.adpaters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.moim.fragments.*

class BottomViewPagerAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {
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