package com.laylamac.finalprojectmade.view

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class TabAdapter (fm : FragmentManager) : FragmentPagerAdapter(fm) {

    private val  mFragmentList = mutableListOf<Fragment>()

    override fun getItem(position: Int): Fragment {
        return mFragmentList[position]
    }

    override fun getCount(): Int {
       return mFragmentList.size
    }

    fun addFragment (fragment: Fragment){
        mFragmentList.add(fragment)
    }

}
