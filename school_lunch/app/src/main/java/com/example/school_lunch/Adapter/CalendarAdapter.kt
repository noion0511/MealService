package com.example.school_lunch.Adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isInvisible
import androidx.recyclerview.widget.RecyclerView
import com.example.school_lunch.BaseCalendar
import com.example.school_lunch.Fragment.AcademicCalendarF

import com.example.school_lunch.R
import kotlinx.android.synthetic.main.cal_item.view.*

class CalendarAdapter(private val baseCalendar: BaseCalendar, private val scheduleList: List<AcademicCalendarF.Schedule>? = null) : RecyclerView.Adapter<CalendarAdapter.CalendarItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CalendarItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.cal_item, parent, false)
        return CalendarItemViewHolder(view)
    }

    override fun getItemCount(): Int {
        return BaseCalendar.LOW_OF_CALENDAR * BaseCalendar.DAYS_OF_WEEK
    }

    override fun onBindViewHolder(holder: CalendarItemViewHolder, position: Int) {
        if (position % BaseCalendar.DAYS_OF_WEEK == 0) holder.itemView.tv_date.setTextColor(Color.parseColor("#004445"))
        else if(position % BaseCalendar.DAYS_OF_WEEK == 6) holder.itemView.tv_date.setTextColor(Color.parseColor("#004445"))
        else holder.itemView.tv_date.setTextColor(Color.parseColor("#676d6e"))

        val day = baseCalendar.data[position]

        holder.itemView.tv_date.text = baseCalendar.data[position].toString()
        holder.itemView.academicSchedule.text = scheduleList?.find { schedule -> schedule.day == day }?.schedule ?: ""

        if(holder.itemView.academicSchedule.text != "") {
            holder.itemView.tv_date.setBackgroundColor(Color.parseColor("#2c786c"))
            holder.itemView.tv_date.setTextColor(Color.parseColor("#FFFFFF"))
        }

        if(scheduleList?.find { it.day == day }?.holiday==""){
            holder.itemView.tv_date.setBackgroundColor(Color.parseColor("#f8b400"))
            holder.itemView.tv_date.setTextColor(Color.parseColor("#393b44"))
        }

        if (position < baseCalendar.preMonth
            || position >= baseCalendar.preMonth + baseCalendar.currentMonth) {
            holder.itemView.tv_date.alpha = 0.3f
            holder.itemView.tv_date.setTextColor(Color.parseColor("#8d93ab"))
            holder.itemView.tv_date.setBackgroundColor(Color.WHITE)
            holder.itemView.academicSchedule.setText("")
        } else {
            holder.itemView.tv_date.alpha = 1f
        }
    }

    class CalendarItemViewHolder(containerView: View) : RecyclerView.ViewHolder(containerView)
}