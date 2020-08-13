package com.example.school_lunch.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.ViewParent
import androidx.recyclerview.widget.RecyclerView
import com.example.school_lunch.Fragment.ClassScheduleF
import com.example.school_lunch.R
import kotlinx.android.synthetic.main.class_item.view.*


class ClassAdapter : RecyclerView.Adapter<ClassAdapter.ClassViewHolder>(){
    var classItems: MutableList<ClassScheduleF.MainData> = mutableListOf(
        ClassScheduleF.MainData("Title1", "Content1"),
        ClassScheduleF.MainData("Title2", "Content2"), ClassScheduleF.MainData("Title3", "Content3"),ClassScheduleF.MainData("Title1", "Content1"),
        ClassScheduleF.MainData("Title2", "Content2"), ClassScheduleF.MainData("Title3", "Content3"),
        ClassScheduleF.MainData("Title1", "Content1"),
        ClassScheduleF.MainData("Title2", "Content2"), ClassScheduleF.MainData("Title3", "Content3"),
        ClassScheduleF.MainData("Title1", "Content1"),
        ClassScheduleF.MainData("Title2", "Content2"), ClassScheduleF.MainData("Title3", "Content3"),
        ClassScheduleF.MainData("Title1", "Content1"),
        ClassScheduleF.MainData("Title2", "Content2"), ClassScheduleF.MainData("Title3", "Content3"),
        ClassScheduleF.MainData("Title1", "Content1"),
        ClassScheduleF.MainData("Title2", "Content2"), ClassScheduleF.MainData("Title3", "Content3")
    )

    override fun onCreateViewHolder(parent: ViewGroup, p1:Int) = ClassViewHolder(parent)

    override fun getItemCount(): Int = classItems.size

    override fun onBindViewHolder(holder: ClassAdapter.ClassViewHolder, position: Int) {
        classItems[position].let{ item ->
            with(holder) {
                tvClass.text = item.content
            }
        }
    }

    inner class ClassViewHolder(parent: ViewGroup): RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.class_item, parent, false)) {
        val tvClass = itemView.class_date
    }
}