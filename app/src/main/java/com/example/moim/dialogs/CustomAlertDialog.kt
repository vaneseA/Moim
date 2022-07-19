package com.neppplus.a20220530_keepthetime.dialogs

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.widget.LinearLayout
import com.example.moim.databinding.CustomAlertDialogBinding


class CustomAlertDialog (val mContext : Context, val activity : Activity){

    val dialog = Dialog(mContext)
    lateinit var binding : CustomAlertDialogBinding

    fun myDialog () {
        binding = CustomAlertDialogBinding.inflate(activity.layoutInflater)
        dialog.setContentView(binding.root)

        dialog.window?.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)

        dialog.setCancelable(true)

        dialog.show()
    }
}