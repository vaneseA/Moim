package com.example.moim.ui.profile

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.example.moim.BaseActivity
import com.example.moim.R
import com.example.moim.databinding.ActivityProfileSetBinding
import com.example.moim.models.BasicResponse
import com.example.moim.utils.GlobalData
import com.example.moim.utils.URIPathHelper
import com.gun0912.tedpermission.PermissionListener
import com.gun0912.tedpermission.normal.TedPermission
import com.neppplus.a20220530_keepthetime.dialogs.CustomAlertDialog
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File

class ProfileSetActivity : BaseActivity() {
    lateinit var binding: ActivityProfileSetBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_profile_set)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {
//        프로필 이미지 변경 이벤트
        binding.profileImg.setOnClickListener {
//            갤러리를 개발자가 이용 : 유저 허락을 받아야한다. => 권한 세팅
//            TedPermission 라이브러리
            val pl = object : PermissionListener {
                override fun onPermissionGranted() {
//                  권한 Ok
                    val myIntent = Intent()

//            갤러리로 사진을 가지러 이동(추가작업) => Intent (4)
                    myIntent.action = Intent.ACTION_PICK
                    myIntent.type = android.provider.MediaStore.Images.Media.CONTENT_TYPE

                    startForResult.launch(myIntent)
                }

                override fun onPermissionDenied(deniedPermissions: MutableList<String>?) {
//                    권한이 Denied

                }

            }

//            권한이 OK 일때
            TedPermission.create()
                .setPermissionListener(pl)
                .setPermissions(Manifest.permission.READ_EXTERNAL_STORAGE)
//            테드 퍼미션이 지원하는 Denied 경우의 Alert
                .setDeniedMessage("[설정] > [권한]에서 갤러리 권한을 열어주세요.")
                .check()

        }

//        닉네임 변경 이벤트
        binding.saveBtn.setOnClickListener {
            val inputTitle = binding.nicknameEdt.text.toString()
            apiList.patchRequestEditUserInfo(
                "nickname",
                binding.nicknameEdt.text.toString()
            ).enqueue(object : Callback<BasicResponse> {
                override fun onResponse(
                    call: Call<BasicResponse>,
                    response: Response<BasicResponse>
                ) {
                    if (inputTitle.isBlank()) {
                        return Toast.makeText(mContext, "수정사항이 없습니다", Toast.LENGTH_SHORT).show()

                    }
                    if (response.isSuccessful) {
                        val br = response.body()!!
                        val message = "프로필 수정 완료"
                        GlobalData.loginUser = br.data.user

                        setUserData()

                        Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show()
                    }
//                        뭔가 중복된 닉네임과 같은 문제가 발생
                    else {
                        val errorBodyStr = response.errorBody()!!.string()
                        val jsonObj = JSONObject(errorBodyStr)
                        val message = jsonObj.getString("message")

                        Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<BasicResponse>, t: Throwable) {

                }
            })

        }

    }

    override fun setValues() {

        setUserData()
    }

    fun setUserData() {
        Glide.with(mContext)
            .load(GlobalData.loginUser!!.profileImg)
            .into(binding.profileImg)
        binding.nicknameEdt.hint = GlobalData.loginUser!!.nickname
    }

    val startForResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == Activity.RESULT_OK) {
//            어떤 사진을 골랏는지? 파악해보자
//            임시 : 고른 사진을 profileImg에 바로 적용만 (서버전송 X)

//            data? => 이전 화면이 넘겨준 intent
//            data?.data => 선택한 사진이 들어있는 경로 정보 (Uri)
                val dataUri = it.data?.data

//            Uri -> 이미지뷰의 사진 (GLide)
//            Glide.with(mContext).load(dataUri).into(binding.profileImg)

//                        API 서버에 사진을 전송 => PUT 메쏘드 + ("/user/image")
//            파일을 같이 첨부해야 => Multipart 형식의 데이터 첨부 활용 (기존 FromData와는 다르다!!)

//            Uri -> File 형태로 변환 -> 그 파일의 실제 경로를 얻어낼 필요가 있다.
                val file = File(URIPathHelper().getPath(mContext, dataUri!!))

//            파일을 retrofit에 첨부할 수 있는 => RequestBody => MultipartBody 형태로 변환
                val fileReqBody = RequestBody.create(MediaType.get("image/*"), file)
                val body =
                    MultipartBody.Part.createFormData("profile_image", "myFile.jpg", fileReqBody)

                apiList.putRequestUserImage(body).enqueue(object : Callback<BasicResponse> {
                    override fun onResponse(
                        call: Call<BasicResponse>,
                        response: Response<BasicResponse>
                    ) {
                        if (response.isSuccessful) {
//                        1. 선택한 이미지로 UI 프사 변경
                            GlobalData.loginUser = response.body()!!.data.user

                            Glide.with(mContext).load(GlobalData.loginUser!!.profileImg)
                                .into(binding.profileImg)

//                        2. 토스트로 성공 메세지
                            Toast.makeText(mContext, "프로필 사진이 변경되었습니다.", Toast.LENGTH_SHORT).show()
                        }
                    }

                    override fun onFailure(call: Call<BasicResponse>, t: Throwable) {

                    }
                })
            }
        }
}