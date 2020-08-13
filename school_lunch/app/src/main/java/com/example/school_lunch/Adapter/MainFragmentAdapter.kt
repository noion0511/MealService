package com.example.school_lunch.Adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.school_lunch.Fragment.ClassScheduleF
import com.example.school_lunch.Fragment.CommunityF
import com.example.school_lunch.Fragment.SettingF
import com.example.school_lunch.Fragment.AcademicCalendarF
import com.example.school_lunch.Fragment.MealServiceF

class MainFragmentAdapter(fm : FragmentManager, val fragmentCount : Int) : FragmentStatePagerAdapter(fm) {
    private val scheduleF = ClassScheduleF()
    private val calendarF = AcademicCalendarF()
    private val mealServiceF = MealServiceF()
    private val communityF = CommunityF()
    private val settingF = SettingF()

    override fun getItem(position: Int): Fragment {
        when(position) {
            0 -> return AcademicCalendarF()
            1 -> return ClassScheduleF()
            2 -> return MealServiceF()
            3 -> return CommunityF()
            4 -> return SettingF()
            else -> return MealServiceF()
        }
    }

    override fun getCount(): Int = fragmentCount
}