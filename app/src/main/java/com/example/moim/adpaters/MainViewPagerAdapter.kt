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
}