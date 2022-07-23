package com.example.moim.adpaters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.moim.databinding.LayoutRecyclerMeetBaseItemBinding
import com.example.moim.models.GroupData

class MeetItemAdapter (
    val mContext : Context,
    val mList : List<GroupData>
        ): RecyclerView.Adapter<MeetItemAdapter.ItemViewHolder>(){

            inner class ItemViewHolder (val binding : LayoutRecyclerMeetBaseItemBinding) : RecyclerView.ViewHolder(binding.root) {
            fun bind(item : GroupData) {
                binding.meettingTitleTxt.text = item.title
                binding.locationTxt.text = item.title
                binding.purposeTxt.text = item.title
            }
            }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            LayoutRecyclerMeetBaseItemBinding.inflate(LayoutInflater.from(mContext),parent,false)
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(mList[position])
    }

    override fun getItemCount(): Int {
        return mList.size
    }
}