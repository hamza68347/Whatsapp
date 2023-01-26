package com.hazelmobile.statussaver.ui.statusFragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.hazelmobile.statussaver.R
import com.hazelmobile.statussaver.util.TabStatusAdopter

class ImagesFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       val view:View=inflater.inflate(R.layout.fragment_images, container, false)


        return view
    }

}