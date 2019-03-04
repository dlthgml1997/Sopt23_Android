package com.androidstudy.fragmentpractice


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import com.androidstudy.fragmentpractice.adapter.HomeTopTabPagerAdapter
import kotlinx.android.synthetic.main.fragment_home.*
import java.util.zip.Inflater

class HomeFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val homeFragmentView: View = inflater!!.inflate(R.layout.fragment_home, container, false)

        return homeFragmentView
    }

    // fragment View를 건들 수 있는 메소드
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        configureTopTab()
//rl_main_act_home_frag_background. backgroundColor = Color.parseColor("#BBDEFB")

    }

    private fun configureTopTab(){
        vp_home_frag.adapter= HomeTopTabPagerAdapter(childFragmentManager,2)
        vp_home_frag.offscreenPageLimit = 2
        tl_home_frag_top.setupWithViewPager(vp_home_frag)

        val topTabLayout : View = (activity!!.getSystemService(android.content.Context.LAYOUT_INFLATER_SERVICE)as LayoutInflater)
            .inflate(R.layout.top_tab_layout,null,false)

        tl_home_frag_top.getTabAt(0)!!.customView = topTabLayout.findViewById(R.id.btn_top_tab_profile)as RelativeLayout
        tl_home_frag_top.getTabAt(1)!!.customView = topTabLayout.findViewById(R.id.btn_top_tab_myshopping)as RelativeLayout
    }
}