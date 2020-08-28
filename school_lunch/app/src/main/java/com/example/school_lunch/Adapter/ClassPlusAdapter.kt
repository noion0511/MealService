package com.example.school_lunch.Adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.school_lunch.Fragment.ClassScheduleF
import com.example.school_lunch.R
import kotlinx.android.synthetic.main.plus_item.view.*

class ClassPlusAdapter : RecyclerView.Adapter<ClassPlusAdapter.ClassViewHolder>(){
    var classItems: MutableList<ClassScheduleF.ClassData> = mutableListOf(
        ClassScheduleF.ClassData(""), ClassScheduleF.ClassData("월"),
        ClassScheduleF.ClassData("화"), ClassScheduleF.ClassData("수"),
        ClassScheduleF.ClassData("목"), ClassScheduleF.ClassData("금"),
        ClassScheduleF.ClassData("1"), ClassScheduleF.ClassData(""),
        ClassScheduleF.ClassData(""), ClassScheduleF.ClassData(""),
        ClassScheduleF.ClassData(""), ClassScheduleF.ClassData(""),
        ClassScheduleF.ClassData("2"), ClassScheduleF.ClassData(""),
        ClassScheduleF.ClassData(""), ClassScheduleF.ClassData(""),
        ClassScheduleF.ClassData(""), ClassScheduleF.ClassData(""),
        ClassScheduleF.ClassData("3"), ClassScheduleF.ClassData(""),
        ClassScheduleF.ClassData(""), ClassScheduleF.ClassData(""),
        ClassScheduleF.ClassData(""), ClassScheduleF.ClassData(""),
        ClassScheduleF.ClassData("4"), ClassScheduleF.ClassData(""),
        ClassScheduleF.ClassData(""), ClassScheduleF.ClassData(""),
        ClassScheduleF.ClassData(""), ClassScheduleF.ClassData(""),
        ClassScheduleF.ClassData("5"), ClassScheduleF.ClassData(""),
        ClassScheduleF.ClassData(""), ClassScheduleF.ClassData(""),
        ClassScheduleF.ClassData(""), ClassScheduleF.ClassData(""),
        ClassScheduleF.ClassData("6"), ClassScheduleF.ClassData(""),
        ClassScheduleF.ClassData(""), ClassScheduleF.ClassData(""),
        ClassScheduleF.ClassData(""), ClassScheduleF.ClassData(""),
        ClassScheduleF.ClassData("7"), ClassScheduleF.ClassData(""),
        ClassScheduleF.ClassData(""), ClassScheduleF.ClassData(""),
        ClassScheduleF.ClassData(""), ClassScheduleF.ClassData(""),
        ClassScheduleF.ClassData("8"), ClassScheduleF.ClassData(""),
        ClassScheduleF.ClassData(""), ClassScheduleF.ClassData(""),
        ClassScheduleF.ClassData(""), ClassScheduleF.ClassData("")
    )

    override fun onCreateViewHolder(parent: ViewGroup, p1:Int) = ClassViewHolder(parent)

    override fun getItemCount(): Int = classItems.size

    override fun onBindViewHolder(holder: ClassPlusAdapter.ClassViewHolder, position: Int) {
        classItems[position].let{ item ->
            with(holder) {
                tvClass.text = item.content
            }
        }
        holder.itemView.setOnClickListener {
            holder.itemView.plus_item.setBackgroundColor(Color.parseColor("#ffc93c"))
        }
    }

    inner class ClassViewHolder(parent: ViewGroup): RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.plus_item, parent, false)) {
        val tvClass = itemView.plus_date
    }
}