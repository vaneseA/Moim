package com.example.moim.adpaters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.moim.fragments.AllFragment
import com.example.moim.fragments.BookFragment
import com.example.moim.fragments.OutdoorFragment
import com.example.moim.fragments.SportsFragment

class MainViewPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {
    override fun getCount(): Int {
        return 4
    }

    override fun getItem(position: Int): Fragment {
        if (position == 0) {
            return AllFragment()
        } else if (position == 1) {
            return OutdoorFragment()
        } else if (position == 2) {
            return SportsFragment()
        }else {
            return BookFragment()
        }
    }
}