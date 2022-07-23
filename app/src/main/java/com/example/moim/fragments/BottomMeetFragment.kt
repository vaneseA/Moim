package com.example.moim.fragments

import android.content.Intent
import android.content.res.Resources
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.moim.R
import com.example.moim.databinding.FragmentBottomTab1MeetBinding
import com.example.moim.ui.CreateActivity
import com.example.moim.ui.RecentActivity

class BottomMeetFragment : BaseFragment() {
//    private var categories: Array<String>
//    private var resources: Resources? = null
    lateinit var binding: FragmentBottomTab1MeetBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_bottom_tab1_meet, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {

    }

    override fun setValues() {

        binding.meettingAddBtn.setOnClickListener {
            startActivity(Intent(mContext, CreateActivity::class.java))
        }
//categoryRecycler build
//        resources = getResources()
//        categories = resources!!.getStringArray(R.array.category_for_interest_in_meet)
//        categoryAdapter = CategoryAdapter(activity, categories, this)
//        recyclerCategory.setAdapter(categoryAdapter)
//        buttonAdd.setOnClickListener(View.OnClickListener {
//            val intent = Intent(activity, CreateActivity::class.java)
//            startActivity(intent)
//        })
    }
}