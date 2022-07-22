package com.example.moim.api

import android.content.Context
import com.example.moim.utils.ContextUtil
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ServerApi {

    companion object {

//        서버주소
        private val baseUrl = "https://keepthetime.xyz"

        private var retrofit : Retrofit? = null

        fun getRetrofit(context : Context) : Retrofit {

            if (retrofit == null) {

//                API요청이 발생하면 => 가로채서 => 헤더를 추가해주자.
//                자동으로 헤더를 달아주는 효과 발생

                val interceptor = Interceptor {
                    with(it) {
                        val newRequest = request().newBuilder()
                            .addHeader("X-Http-Token", ContextUtil.getLoginToken(context))
                            .build()
                        proceed(newRequest)
                    }
                }

//                retrofit : OkHttp의 확장판 => retrofit도 OkHttpClient 형태의 클라이언트 활용
//                클라이언트에게 우리가 만든 인터셉터를 달아주자( 클라이언트 커스터 마이징 )
                val myClient = OkHttpClient.Builder().addInterceptor(interceptor).build()

                retrofit = Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .client(myClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()

            }

            return retrofit!!
        }

    }

}