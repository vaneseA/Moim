package com.example.moim.ui.main

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.moim.BaseActivity
import com.example.moim.R
import com.example.moim.databinding.ActivityLoginBinding
import com.example.moim.models.BasicResponse
import com.example.moim.ui.signup.SignUpActivity
import com.example.moim.utils.ContextUtil
import com.example.moim.utils.GlobalData
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.model.ClientError
import com.kakao.sdk.common.model.ClientErrorCause
import com.kakao.sdk.user.UserApiClient
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : BaseActivity() {

    lateinit var binding: ActivityLoginBinding
    var TAG = "LoginActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {

        binding.loginBtn.setOnClickListener {
            val inputEmail = binding.emailEdt.text.toString()
            val inputPw = binding.passwordEdt.text.toString()

            apiList.postRequestLogin(inputEmail, inputPw).enqueue(object : Callback<BasicResponse> {
                override fun onResponse(
                    call: Call<BasicResponse>,
                    response: Response<BasicResponse>
                ) {

                    if (response.isSuccessful) {
                        val br = response.body()!!

                        ContextUtil.setLoginToken(mContext, br.data.token)
                        ContextUtil.setAutoLogin(mContext, binding.autoLoginCb.isChecked)
                        GlobalData.loginUser = br.data.user

                        Toast.makeText(
                            mContext,
                            "${br.data.user.nickname}님 환영합니다.",
                            Toast.LENGTH_SHORT
                        ).show()

                        val myIntent = Intent(mContext, MainActivity::class.java)
                        startActivity(myIntent)
                        finish()
                    } else {
                        val errorBodyStr = response.errorBody()!!.string()
                        val jsonObj = JSONObject(errorBodyStr)
                        val code = jsonObj.getInt("code")
                        val message = jsonObj.getString("message")

                        Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show()
                    }

                }

                override fun onFailure(call: Call<BasicResponse>, t: Throwable) {

                }
            })
        }

        binding.signUpBtn.setOnClickListener {
            val myIntent = Intent(mContext, SignUpActivity::class.java)
            startActivity(myIntent)
        }
        binding.kakaoLoginBtn.setOnClickListener {
            kakaoLogin()
        }
    }

    override fun setValues() {

    }

    fun kakaoLogin() {
// 카카오계정으로 로그인 공통 callback 구성
// 카카오톡으로 로그인 할 수 없어 카카오계정으로 로그인할 경우 사용됨
        val callback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
            if (error != null) {
                Log.e(TAG, "카카오계정으로 로그인 실패", error)
            } else if (token != null) {
                Log.i(TAG, "카카오계정으로 로그인 성공 ${token.accessToken}")
                getKakaoUserInfo()
            }
        }

// 카카오톡이 설치되어 있으면 카카오톡으로 로그인, 아니면 카카오계정으로 로그인
        if (UserApiClient.instance.isKakaoTalkLoginAvailable(mContext)) {
            UserApiClient.instance.loginWithKakaoTalk(mContext) { token, error ->
                if (error != null) {
                    Log.e(TAG, "카카오톡으로 로그인 실패", error)

                    // 사용자가 카카오톡 설치 후 디바이스 권 한 요청 화면에서 로그인을 취소한 경우,
                    // 의도적인 로그인 취소로 보고 카카오계정으로 로그인 시도 없이 로그인 취소로 처리 (예: 뒤로 가기)
                    if (error is ClientError && error.reason == ClientErrorCause.Cancelled) {
                        return@loginWithKakaoTalk
                    }

                    // 카카오톡에 연결된 카카오계정이 없는 경우, 카카오계정으로 로그인 시도
                    UserApiClient.instance.loginWithKakaoAccount(mContext, callback = callback)
                } else if (token != null) {
                    Log.i(TAG, "카카오톡으로 로그인 성공 ${token.accessToken}")
                    getKakaoUserInfo()
                }
            }
        } else {
            UserApiClient.instance.loginWithKakaoAccount(mContext, callback = callback)
        }
    }

    fun getKakaoUserInfo() {
        // 사용자 정보 요청 (기본)
        UserApiClient.instance.me { user, error ->
            if (error != null) {
                Log.e(TAG, "사용자 정보 요청 실패", error)
            } else if (user != null) {
                Log.i(
                    TAG, "사용자 정보 요청 성공" +
                            "\n회원번호: ${user.id}" +
                            "\n닉네임: ${user.kakaoAccount?.profile?.nickname}")
                socialLogin("kakao", "${user.id}", "${user.kakaoAccount?.profile?.nickname}")

            }
        }
    }
    fun socialLogin(provider : String, uid : String, nickname : String) {
        apiList.postRequestSocialLogin(provider, uid, nickname).enqueue(object : Callback<BasicResponse>{
            override fun onResponse(call: Call<BasicResponse>, response: Response<BasicResponse>) {
                if (response.isSuccessful) {
                    val br = response.body()!!

                    ContextUtil.setLoginToken(mContext, br.data.token)
                    ContextUtil.setAutoLogin(mContext, binding.autoLoginCb.isChecked)
                    GlobalData.loginUser = br.data.user

                    Toast.makeText(
                        mContext,
                        "${GlobalData.loginUser!!.nickname}님 환영합니다.",
                        Toast.LENGTH_SHORT
                    ).show()

                    val myIntent = Intent(mContext, MainActivity::class.java)
                    startActivity(myIntent)
                    finish()
                }
            }

            override fun onFailure(call: Call<BasicResponse>, t: Throwable) {

            }
        })
    }
}