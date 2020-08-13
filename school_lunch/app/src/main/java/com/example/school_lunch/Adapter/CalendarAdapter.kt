package com.example.school_lunch.Adapter


import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.school_lunch.BaseCalendar
import com.example.school_lunch.Fragment.AcademicCalendarF

import com.example.school_lunch.R
import kotlinx.android.synthetic.main.cal_item.view.*
import java.util.*

class CalendarAdapter(private val scheduleList: List<AcademicCalendarF.Schedule>? = null, private val onMonthChangeListener: OnMonthChangeListener? = null) : RecyclerView.Adapter<CalendarAdapter.CalendarItemViewHolder>() {

    private val baseCalendar = BaseCalendar()

    init {
        baseCalendar.initBaseCalendar {
            onMonthChangeListener?.onMonthChanged(it)
        }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CalendarItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.cal_item, parent, false)
        return CalendarItemViewHolder(view)
    }

    override fun getItemCount(): Int {
        return BaseCalendar.LOW_OF_CALENDAR * BaseCalendar.DAYS_OF_WEEK
    }

    override fun onBindViewHolder(holder: CalendarItemViewHolder, position: Int) {
        if (position % BaseCalendar.DAYS_OF_WEEK == 0) holder.itemView.tv_date.setTextColor(Color.parseColor("#5512BB"))
        else if(position % BaseCalendar.DAYS_OF_WEEK == 6) holder.itemView.tv_date.setTextColor(Color.parseColor("#1712BB"))
        else holder.itemView.tv_date.setTextColor(Color.parseColor("#676d6e"))

        val day = baseCalendar.data[position]

        if (position < baseCalendar.preMonth
            || position >= baseCalendar.preMonth + baseCalendar.currentMonth) {
            holder.itemView.tv_date.alpha = 0.3f
        } else {
            holder.itemView.tv_date.alpha = 1f
        }
        holder.itemView.tv_date.text = baseCalendar.data[position].toString()
        holder.itemView.academicSchedule.text = scheduleList?.find { schedule -> schedule.day == day }?.schedule ?: ""


        if(scheduleList?.find { it.day == day }?.holiday==""){
            holder.itemView.tv_date.setTextColor(Color.parseColor("#00C3CF"))
        }

        Log.d("Schedule size", "size of schedule = ${scheduleList?.size ?: 0}")
    }


    fun changeToPrevMonth() {
        baseCalendar.changeToPrevMonth {
            onMonthChangeListener?.onMonthChanged(it)
            notifyDataSetChanged()
        }
    }

    fun changeToNextMonth() {
        baseCalendar.changeToNextMonth {
            onMonthChangeListener?.onMonthChanged(it)
            notifyDataSetChanged()
        }
    }

    interface OnMonthChangeListener {
        fun onMonthChanged(calendar : Calendar)
    }

    class CalendarItemViewHolder(containerView: View) : RecyclerView.ViewHolder(containerView)
}