package com.androidstudy.fragmentpractice


import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.RelativeLayout
import com.androidstudy.fragmentpractice.adapter.MainBottomTabPagerAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        configureBottomTab()
    }

    private fun configureBottomTab(){
        viewpager_main_act.adapter = MainBottomTabPagerAdapter(supportFragmentManager, 3)
        viewpager_main_act.offscreenPageLimit = 3
        tl_main_act_bottom_tab.setupWithViewPager(viewpager_main_act)

        val bottomTabLayout : View = (this.getSystemService(android.content.Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater)
            .inflate(R.layout.bottomtablayout,null,false)

        tl_main_act_bottom_tab.getTabAt(0)!!.customView= bottomTabLayout.findViewById(R.id.btn_home_bottom_tab) as RelativeLayout
        tl_main_act_bottom_tab.getTabAt(1)!!.customView= bottomTabLayout.findViewById(R.id.btn_category_bottom_tab) as RelativeLayout
        tl_main_act_bottom_tab.getTabAt(2)!!.customView= bottomTabLayout.findViewById(R.id.btn_mypage_bottom_tab) as RelativeLayout
    }
}
