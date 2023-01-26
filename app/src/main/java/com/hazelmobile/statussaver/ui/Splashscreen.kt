package com.hazelmobile.statussaver.ui

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.hazelmobile.statussaver.R
import com.hazelmobile.statussaver.util.PermissionCheck

@SuppressLint("CustomSplashScreen")
class Splashscreen : AppCompatActivity() {

    private lateinit var sharedPreferences: SharedPreferences

   private lateinit var permissionCheck: PermissionCheck
   private var permissionAllow:Boolean=false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splashscreen)
        sharedPreferences = getSharedPreferences("PERMISSION_ALLOW", Context.MODE_PRIVATE)
        val read: Boolean = sharedPreferences.getBoolean("permissionCheck", false)

        permissionCheck=PermissionCheck()
        permissionAllow = permissionCheck.checkPermission(this)
        // Delay for 3 sec of splashScreen

        Handler(Looper.getMainLooper()).postDelayed({

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R){
                if(permissionAllow && read) {
                    Intent(this, HomeActivity::class.java).also {
                        startActivity(it)
                        finish()}
                }else{
                    Intent(this, PermissionScreenActivity::class.java).also {
                        startActivity(it)
                        finish()}
                }
            }else{
                if(permissionAllow) {

                    Intent(this, HomeActivity::class.java).also {
                        startActivity(it)
                        finish()}
                }else{
                    Intent(this, PermissionScreenActivity::class.java).also {
                        startActivity(it)
                        finish()}
                }
            }







        }, 3000)

    }
    }