package com.hazelmobile.statussaver.ui

import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.hazelmobile.statussaver.R
import com.hazelmobile.statussaver.ui.mainFragments.RecoveryFragment
import com.hazelmobile.statussaver.ui.mainFragments.StatusFragment
import com.hazelmobile.statussaver.ui.mainFragments.StickerFragment


class HomeActivity : AppCompatActivity() {

    lateinit var drawerLayout: DrawerLayout
    lateinit var actionBarDrawerToggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)




        loadFragment(StatusFragment())
        findViewById<BottomNavigationView>(R.id.bottomNavigation).setOnItemSelectedListener {

            when(it.itemId) {
                R.id.status_nav-> loadFragment(StatusFragment())
                R.id.sticker_nav->loadFragment(StickerFragment())
                else -> loadFragment(RecoveryFragment())

            }
            return@setOnItemSelectedListener true
        }
    }


    private fun loadFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container,fragment)
        transaction.commit()
    }


}