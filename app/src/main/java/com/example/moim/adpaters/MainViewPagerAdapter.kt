package com.example.moim.adpaters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.moim.fragments.AllFragment
import com.example.moim.fragments.BookFragment
import com.example.moim.fragments.OutdoorFragment
import com.example.moim.fragments.SportsFragment

class MainViewPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    override fun getCount() = 4

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> AllFragment()
            1 -> OutdoorFragment()
            2 -> SportsFragment()
            else -> BookFragment()
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 -> "전체"
            1 -> "아웃도어/여행"
            2 -> "운동/스포츠"
            else -> "인문학/책/글"
        }
    }
}