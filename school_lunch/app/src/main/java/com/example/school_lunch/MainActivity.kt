package com.example.school_lunch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.RelativeLayout
import com.example.school_lunch.Adapter.MainFragmentAdapter
import kotlinx.android.synthetic.main.activity_main.*
import com.google.android.material.tabs.TabLayout

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        configureBottomNavigation()
    }


    private fun configureBottomNavigation() {
        val pagerAdapter =  MainFragmentAdapter(supportFragmentManager, 5)

        vp_ac_main_frag_pager.adapter = pagerAdapter
        main_menu.setupWithViewPager(vp_ac_main_frag_pager)

        val bottomNaviLayout: View = this.layoutInflater.inflate(R.layout.bottom_navi_tab, null, false)

        main_menu.getTabAt(0)!!.customView = bottomNaviLayout.findViewById(R.id.btn_navi_calendar_tab) as RelativeLayout
        main_menu.getTabAt(1)!!.customView = bottomNaviLayout.findViewById(R.id.btn_navi_schedule_tab) as RelativeLayout
        main_menu.getTabAt(2)!!.customView = bottomNaviLayout.findViewById(R.id.btn_navi_meal_tab) as RelativeLayout
        main_menu.getTabAt(3)!!.customView = bottomNaviLayout.findViewById(R.id.btn_navi_community_tab) as RelativeLayout
        main_menu.getTabAt(4)!!.customView = bottomNaviLayout.findViewById(R.id.btn_navi_setting_tab) as RelativeLayout

        main_menu.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(p0: TabLayout.Tab?) {

            }

            override fun onTabUnselected(p0: TabLayout.Tab?) {

            }

            override fun onTabSelected(p0: TabLayout.Tab?) {
                val title = when(p0) {
                    main_menu.getTabAt(0)->resources.getText(R.string.calendar)
                    main_menu.getTabAt(1)->resources.getText(R.string.schedule)
                    main_menu.getTabAt(2)->resources.getText(R.string.meal)
                    main_menu.getTabAt(3)->resources.getText(R.string.community)
                    main_menu.getTabAt(4)->resources.getText(R.string.setting)
                    else -> "Error"
                }
                set_date.text = title
            }
        })
    }
}
