package com.example.moim.adpaters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.moim.databinding.LayoutRecyclerMeetBaseItemBinding
import com.example.moim.models.GroupData

class MeetItemAdapter(
    val mContext: Context,
    val mList: List<GroupData>
) : RecyclerView.Adapter<MeetItemAdapter.ItemViewHolder>() {

    inner class ItemViewHolder(val binding: LayoutRecyclerMeetBaseItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: GroupData) {

            //서버이용을위해 이메일 닉네임 패스워드를 각각 장소 모임명 모임목적으로 이용.
            binding.locationTxt.text = item.email
            binding.meettingTitleTxt.text = item.nickname
            binding.purposeTxt.text = item.password
//                binding.meetingImg.imgview = item.profileImg
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            LayoutRecyclerMeetBaseItemBinding.inflate(LayoutInflater.from(mContext), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(mList[position])
    }

    override fun getItemCount(): Int {
        return mList.size
    }
}