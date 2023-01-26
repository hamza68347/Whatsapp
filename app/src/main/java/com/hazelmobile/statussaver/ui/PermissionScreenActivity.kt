package com.hazelmobile.statussaver.ui

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.os.storage.StorageManager
import android.provider.Settings
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.hazelmobile.statussaver.R
import com.hazelmobile.statussaver.util.PermissionCheck
import com.permissionx.guolindev.PermissionX


class PermissionScreenActivity : AppCompatActivity() {

    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var sharedPreferencesEditor: SharedPreferences.Editor
   private lateinit var permissionCheck: PermissionCheck

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_permission_screen)
        sharedPreferences = getSharedPreferences("PERMISSION_ALLOW", Context.MODE_PRIVATE)
        permissionCheck=PermissionCheck()


       findViewById<Button>(R.id.understand).setOnClickListener {
           if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
               if(permissionCheck.checkPermission(this)){
                   permissionForScopeStorage()
               }else {
                   try {

                       val intent = Intent()
                       intent.action = Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION
                       val uri = Uri.fromParts("package", this.packageName, null)
                       intent.data = uri
                       storageActivityResultLauncher.launch(intent)
                   } catch (e: Exception) {
                       val intent = Intent()
                       intent.action = Settings.ACTION_MANAGE_ALL_FILES_ACCESS_PERMISSION
                       storageActivityResultLauncher.launch(intent)
                   }
               }

           }else{
               PermissionX.init( this)
                   .permissions(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                   .request { allGranted, grantedList, deniedList ->
                       if (allGranted) {
                           Toast.makeText(this, "All permissions are granted", Toast.LENGTH_LONG).show()
                           Intent(this, HomeActivity::class.java).also {
                               startActivity(it)
                               finish()}

                       } else {
                           Toast.makeText(this, "These permissions are denied: $deniedList", Toast.LENGTH_LONG).show()
                           PermissionX.init(this)
                               .permissions(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                               .explainReasonBeforeRequest()
                       }
                   }
           }
       }
       }

    private val storageActivityResultLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()){

        //here we will handle the result of our intent
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R){
            //Android is 11(R) or above
            if (Environment.isExternalStorageManager()){

                permissionForScopeStorage()


            }
        }
        else{
            //Android is below 11(R)
        }
    }

    private fun permissionForScopeStorage() {
        val storageManager = application.getSystemService(Context.STORAGE_SERVICE) as StorageManager
        try {
            if (Build.VERSION.SDK_INT > Build.VERSION_CODES.Q) {
                val intent =
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                        storageManager.primaryStorageVolume.createOpenDocumentTreeIntent()
                    } else {
                        TODO("VERSION.SDK_INT < Q")
                    }
                val startDir = "Android%2Fmedia%2Fcom.whatsapp%2FWhatsApp%2FMedia%2F.Statuses"
                var uri = intent.getParcelableExtra<Uri>("android.provider.extra.INITIAL_URI")
                var scheme = uri.toString()
                scheme = scheme.replace("/root/", "/document/")
                scheme += "%3A$startDir"
                uri = Uri.parse(scheme)
                intent.putExtra("android.provider.extra.INITIAL_URI", uri)
                startActivityForResult(intent, 2001)
            }
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        }
    }

    @Suppress("DEPRECATION")
    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        Log.d("URICHECK", (data?.data).toString())
        if (data?.data.toString().contains("WhatsApp%2FMedia%2F.Statuses")) {

            sharedPreferences = getSharedPreferences("PERMISSION_ALLOW", Context.MODE_PRIVATE)
            sharedPreferencesEditor = sharedPreferences.edit()
            sharedPreferencesEditor.apply {
                putBoolean("permissionCheck", true)
                commit()
            }
            Intent(this, HomeActivity::class.java).also {
                startActivity(it)
                finish()}

        }


    }


}