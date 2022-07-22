package com.example.moim

import android.content.Context
import android.os.Bundle
import android.view.Menu
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.moim.api.APIList
import com.example.moim.api.ServerApi
import retrofit2.Retrofit

abstract class BaseActivity : AppCompatActivity() {


    lateinit var mContext : Context

    lateinit var retrofit : Retrofit
    lateinit var apiList : APIList

//    lateinit var drawerBtn : ImageView
    lateinit var titleTxt : TextView
    lateinit var addBtn : ImageView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mContext = this

        supportActionBar?.let {
            setCustomActionBar()
        }

        retrofit = ServerApi.getRetrofit(mContext)
        apiList = retrofit.create(APIList::class.java)
    }

    abstract fun setupEvents()

    abstract fun setValues()

    fun setCustomActionBar () {
        val defActionBar = supportActionBar!!

        defActionBar.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
        defActionBar.setCustomView(R.layout.my_custom_action_bar)

        val toolbar = defActionBar.customView.parent as Toolbar
        toolbar.setContentInsetsAbsolute(0,0)


//        drawerBtn = defActionBar.customView.findViewById<ImageView>(R.id.drawerBtn)
        titleTxt = defActionBar.customView.findViewById(R.id.titleTxt)
        addBtn = defActionBar.customView.findViewById(R.id.addBtn)


//        drawerBtn.setOnClickListener {
//            finish()
//        }

    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val menuInflater = menuInflater
        menuInflater.inflate(R.menu.menu_option_main, menu)
        return super.onCreateOptionsMenu(menu)
    }
}