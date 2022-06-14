package com.example.moim.api


import com.example.moim.models.BasicResponse
import retrofit2.Call
import retrofit2.http.*

interface APIList {
    //    user
    @FormUrlEncoded
    @PUT("/user")
    fun putRequestSignUp(
        @Field("email") email : String,
        @Field("password") pw : String,
        @Field("nick_name") nickname : String,
    ) : Call<BasicResponse>

//    user
    @GET("/user/check")
    fun getRequestUserCheck (
    @Query("type") type : String,
    @Query("value") value : String,
) : Call<BasicResponse>

}