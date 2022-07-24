package com.example.moim.models

import com.google.gson.annotations.SerializedName
import java.util.*

class GroupData (
    val id : Int,
    //모임이름
    val email : String,

    //지역
    @SerializedName("nick_name")
    val nickname : String,

    //대표이미지
    @SerializedName("profile_img")
    val profileImg : String,

    //모임 목적
    val password : String,
){
}