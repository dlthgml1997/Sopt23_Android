package com.androidstudy.fragmentpractice.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.androidstudy.fragmentpractice.CategoryFragment
import com.androidstudy.fragmentpractice.MainFragment
import com.androidstudy.fragmentpractice.MypageFragment

class MainBottomTabPagerAdapter(fm: FragmentManager, private val fragNum : Int) : FragmentStatePagerAdapter(fm){
    override fun getItem(position: Int): Fragment? {
        return when(position){
            0-> MainFragment()
            1-> CategoryFragment()
            2-> MypageFragment()
            else ->null
        }
    }

    override fun getCount(): Int = fragNum
}