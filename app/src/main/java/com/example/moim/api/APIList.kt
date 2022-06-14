package com.example.moim.api


import com.example.moim.models.BasicResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface APIList {

//    user
    @GET("/user/check")
    fun getRequestUserCheck (
    @Query("type") type : String,
    @Query("value") value : String,
) : Call<BasicResponse>

}