package com.example.moim

import android.app.Application
import com.kakao.sdk.common.KakaoSdk

class Moim : Application(){

    override fun onCreate() {
        super.onCreate()

        // Kakao SDK 초기화
        KakaoSdk.init(this, "a2a7578cb5b69f32a7a7aec2ec976a92")

    }}