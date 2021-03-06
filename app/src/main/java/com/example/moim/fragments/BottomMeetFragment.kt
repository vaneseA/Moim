package com.example.moim.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.example.moim.R
import com.example.moim.adpaters.MeetItemAdapter
import com.example.moim.adpaters.ViewPagerFragmentStateAdapter
import com.example.moim.databinding.FragmentBottomTab1MeetBinding
import com.example.moim.models.BasicResponse
import com.example.moim.models.GroupData
import com.example.moim.ui.CreateActivity
import com.example.moim.utils.GlobalData
import com.google.android.material.tabs.TabLayoutMediator
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class BottomMeetFragment : BaseFragment() {
    lateinit var binding: FragmentBottomTab1MeetBinding
    lateinit var mMeetItemAdapter: MeetItemAdapter
    var mMoimList = ArrayList<GroupData>()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // 1. View Binding 설정
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_bottom_tab1_meet, container, false)

        // 2. View Pager의 FragmentStateAdapter 설정
        binding.viewPager.adapter = activity?.let { ViewPagerFragmentStateAdapter(it) }

        // 3. View Pager의 Orientation 설정
        binding.viewPager.orientation = ViewPager2.ORIENTATION_HORIZONTAL

        // 4. TabLayout + ViewPager2 연동 (ViewPager2에 Adapter 연동 후에)
        TabLayoutMediator(binding.tabLayout, binding.viewPager){ tab, position ->
            tab.text = getTabTitle(position)
        }.attach()

        // 5. return Fragment Layout View
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {
        binding.meettingAddBtn.setOnClickListener {
            startActivity(Intent(mContext, CreateActivity::class.java))
        }
    }

    override fun onResume() {
        super.onResume()
        getMoimListFromServer()
    }

    override fun setValues() {
        mMeetItemAdapter = MeetItemAdapter(mContext, mMoimList)
//        binding.recyclerItems.adapter = mMeetItemAdapter
//        binding.recyclerItems.layoutManager = LinearLayoutManager(mContext)

    }

    fun getMoimListFromServer() {
        apiList.getRequestMoimList().enqueue(object : Callback<BasicResponse> {
            override fun onResponse(call: Call<BasicResponse>, response: Response<BasicResponse>) {
                if (response.isSuccessful) {
                    val br = response.body()!!
                    mMoimList.clear()
//                    mMoimList.addAll(br.data.appointments)
                    mMeetItemAdapter.notifyDataSetChanged()
                }
            }

            override fun onFailure(call: Call<BasicResponse>, t: Throwable) {

            }
        })
    }

    // Tab & ViewPager 연동 및 Tab title 설정
    private fun getTabTitle(position: Int): String? {
        return when (position) {
            0 -> "전체"
            1 -> "아웃도어/   여행"
            2 -> "운동/스포츠"
            else -> "인문학/책/글"
        }
    }
}