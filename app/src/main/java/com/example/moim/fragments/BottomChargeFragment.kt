package com.example.moim.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.moim.R
import com.example.moim.databinding.FragmentBottomTab2ChargeBinding

class BottomChargeFragment : BaseFragment() {

    lateinit var binding: FragmentBottomTab2ChargeBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_bottom_tab2_charge,
            container,
            false
        )
        return binding.root
        setupEvents()
        setValues()
    }

    override fun setupEvents() {

    }

    override fun setValues() {

    }
}
