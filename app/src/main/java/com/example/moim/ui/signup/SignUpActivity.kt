package com.example.moim.ui.signup

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.databinding.DataBindingUtil
import com.example.moim.BaseActivity
import com.example.moim.R
import com.example.moim.databinding.ActivitySignUpBinding
import com.example.moim.models.BasicResponse
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignUpActivity : BaseActivity() {

    lateinit var binding : ActivitySignUpBinding

    var isEmailDupOk = false
    var isNickDupOk = false
    var isPwDupOk = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sign_up)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {
        binding.signUpBtn.setOnClickListener {
//            1. 이메일 중복 체크
            if (!isEmailDupOk) {
                Toast.makeText(mContext, "이메일 중복 체크를 해주세요.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
//            2. 비밀번호 중복 체크
            else if (!isPwDupOk) {
                Toast.makeText(mContext, "비밀번호가 일치하지 않습니다.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
//            3. 닉네임 중복 체크
            else if (!isNickDupOk) {
                Toast.makeText(mContext, "닉네임 중복 체크를 해주세요.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
//            실제 회원가입
            else {
                signUp()
            }
        }

        binding.emailDupBtn.setOnClickListener {
            dupCheck("EMAIL", binding.emailEdt.text.toString())
        }
        binding.nickDupBtn.setOnClickListener {
            dupCheck("NICK_NAME", binding.nickEdt.text.toString())
        }

        binding.emailEdt.addTextChangedListener {
            isEmailDupOk = false
        }

        binding.nickEdt.addTextChangedListener {
            isNickDupOk = false
        }

        binding.passwordEdt.addTextChangedListener {
            isPwDupOk = (it.toString() == binding.pwDupEdt.text.toString())
        }

        binding.pwDupEdt.addTextChangedListener {
            isPwDupOk = (it.toString() == binding.passwordEdt.text.toString())
        }
    }

    override fun setValues() {

    }

    //    실제로 모든 조건 통과시 실행할 회원 가입 API
    fun signUp() {
        val inputEmail = binding.emailEdt.text.toString()
        val inputPw = binding.passwordEdt.text.toString()
        val inputNick = binding.nickEdt.text.toString()

        apiList.putRequestSignUp(
            inputEmail,
            inputPw,
            inputNick
        ).enqueue(object : Callback<BasicResponse>{
            override fun onResponse(call: Call<BasicResponse>, response: Response<BasicResponse>) {
                if (response.isSuccessful) {
                    val br = response.body()!!
                    Toast.makeText(
                        mContext,
                        "${br.data.user.nickname}님 가입을 환영합니다.",
                        Toast.LENGTH_SHORT
                    ).show()
                    finish()
                }
                else {
                    val errorBodyStr = response.errorBody()!!.string()
                    val jsonObj = JSONObject(errorBodyStr)
                    val code = jsonObj.getInt("code")
                    val message = jsonObj.getString("message")

                    if (code == 400) {
                        Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show()
                    }
                    else {
                        Log.e("회원가입", "errorCode : ${code}, message : ${message}")
                    }
                }
            }

            override fun onFailure(call: Call<BasicResponse>, t: Throwable) {

            }
        })
    }
    fun dupCheck(type : String, value : String) {
        apiList.getRequestUserCheck(type, value).enqueue(object : Callback<BasicResponse>{
            override fun onResponse(call: Call<BasicResponse>, response: Response<BasicResponse>) {
                if (response.isSuccessful) {
                    val br = response.body()!!
                    Toast.makeText(mContext, br.message, Toast.LENGTH_SHORT).show()

                    when (type) {
                        "EMAIL" -> isEmailDupOk = true
                        "NICK_NAME" -> isNickDupOk = true
                    }
                }
                else {
                    val errorBodyStr = response.errorBody()!!.string()
                    val jsonObj = JSONObject(errorBodyStr)
                    val message = jsonObj.getString("message")

                    Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show()

                    when (type) {
                        "EMAIL" -> isEmailDupOk = false
                        "NICK_NAME" -> isNickDupOk = false
                    }
                }
            }

            override fun onFailure(call: Call<BasicResponse>, t: Throwable) {

            }
        })
    }
}