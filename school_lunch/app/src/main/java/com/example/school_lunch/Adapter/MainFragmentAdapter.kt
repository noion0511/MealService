package com.example.school_lunch.Adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.school_lunch.Fragment.ClassScheduleF
import com.example.school_lunch.Fragment.NoticeF
import com.example.school_lunch.Fragment.SettingF
import com.example.school_lunch.Fragment.AcademicCalendarF
import com.example.school_lunch.Fragment.MealServiceF

class MainFragmentAdapter(fm : FragmentManager, private val fragmentCount : Int) : FragmentStatePagerAdapter(fm) {
    private val scheduleF = ClassScheduleF()
    private val calendarF = AcademicCalendarF()
    private val mealServiceF = MealServiceF()
    private val noticeF = NoticeF()
    private val settingF = SettingF()

    override fun getItem(position: Int): Fragment {
        return when(position) {
            0 -> calendarF
            1 -> scheduleF
            2 -> mealServiceF
            3 -> noticeF
            4 -> settingF
            else -> mealServiceF
        }
    }

    override fun getCount(): Int = fragmentCount
}