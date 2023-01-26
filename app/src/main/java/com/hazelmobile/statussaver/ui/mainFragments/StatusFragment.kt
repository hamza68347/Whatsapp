package com.hazelmobile.statussaver.ui.mainFragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.hazelmobile.statussaver.R
import com.hazelmobile.statussaver.util.TabStatusAdopter

class StatusFragment : Fragment() {

    lateinit var tabLayout: TabLayout;
    lateinit var viewPager: ViewPager;

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view:View=inflater.inflate(R.layout.fragment_status, container, false)

//        tabLayout=requireActivity().findViewById(R.id.statusTab) as TabLayout
//        viewPager=requireActivity().findViewById(R.id.status_viewPager) as ViewPager
//
//        viewPager.adapter=TabStatusAdopter(parentFragmentManager)
//       tabLayout.setupWithViewPager(viewPager)

        return view
    }

}