package com.hazelmobile.statussaver.util

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.hazelmobile.statussaver.ui.statusFragments.ImagesFragment
import com.hazelmobile.statussaver.ui.statusFragments.SavedFragment
import com.hazelmobile.statussaver.ui.statusFragments.VideoFragment

class TabStatusAdopter(fm:FragmentManager): FragmentPagerAdapter(fm) {
    override fun getCount(): Int {
        return 3
    }

    override fun getPageTitle(position: Int): CharSequence? {

        when(position){
            0->(return "Images")
            1-> (return  "Video")
            else-> (return  "Saved")
        }
    }

    override fun getItem(position: Int): Fragment {
        when(position){
          0->(return ImagesFragment())
            1-> (return  VideoFragment())
            else-> (return  SavedFragment())
        }
    }
}