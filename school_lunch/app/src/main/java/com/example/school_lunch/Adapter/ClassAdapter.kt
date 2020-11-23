package com.example.school_lunch.Adapter

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewParent
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.school_lunch.Fragment.ClassScheduleF
import com.example.school_lunch.R
import com.example.school_lunch.Room.ClassSchedule
import kotlinx.android.synthetic.main.class_item.view.*


class ClassAdapter(val context: Context, val schedule : List<ClassSchedule>) : RecyclerView.Adapter<ClassAdapter.Holder>(){
    var classItems: MutableList<ClassScheduleF.ClassData> = mutableListOf(
        ClassScheduleF.ClassData(""),ClassScheduleF.ClassData("월"),
        ClassScheduleF.ClassData("화"),ClassScheduleF.ClassData("수"),
        ClassScheduleF.ClassData("목"),ClassScheduleF.ClassData("금"),
        ClassScheduleF.ClassData("1"),ClassScheduleF.ClassData(""),
        ClassScheduleF.ClassData(""),ClassScheduleF.ClassData(""),
        ClassScheduleF.ClassData(""),ClassScheduleF.ClassData(""),
        ClassScheduleF.ClassData("2"),ClassScheduleF.ClassData(""),
        ClassScheduleF.ClassData(""),ClassScheduleF.ClassData(""),
        ClassScheduleF.ClassData(""),ClassScheduleF.ClassData(""),
        ClassScheduleF.ClassData("3"),ClassScheduleF.ClassData(""),
        ClassScheduleF.ClassData(""),ClassScheduleF.ClassData(""),
        ClassScheduleF.ClassData(""),ClassScheduleF.ClassData(""),
        ClassScheduleF.ClassData("4"),ClassScheduleF.ClassData(""),
        ClassScheduleF.ClassData(""),ClassScheduleF.ClassData(""),
        ClassScheduleF.ClassData(""),ClassScheduleF.ClassData(""),
        ClassScheduleF.ClassData("5"),ClassScheduleF.ClassData(""),
        ClassScheduleF.ClassData(""),ClassScheduleF.ClassData(""),
        ClassScheduleF.ClassData(""),ClassScheduleF.ClassData(""),
        ClassScheduleF.ClassData("6"),ClassScheduleF.ClassData(""),
        ClassScheduleF.ClassData(""),ClassScheduleF.ClassData(""),
        ClassScheduleF.ClassData(""),ClassScheduleF.ClassData(""),
        ClassScheduleF.ClassData("7"),ClassScheduleF.ClassData(""),
        ClassScheduleF.ClassData(""),ClassScheduleF.ClassData(""),
        ClassScheduleF.ClassData(""),ClassScheduleF.ClassData(""),
        ClassScheduleF.ClassData("8"),ClassScheduleF.ClassData(""),
        ClassScheduleF.ClassData(""),ClassScheduleF.ClassData(""),
        ClassScheduleF.ClassData(""),ClassScheduleF.ClassData("")
    )

    override fun getItemCount(): Int = classItems.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(schedule[position])
        /*if (position % 6 == 0) {
            holder.itemView.class_item.setBackgroundColor(Color.parseColor("#CCE5FF"))
            holder.itemView.classView1.visibility = View.INVISIBLE
            holder.itemView.classView2.visibility = View.INVISIBLE
            holder.itemView.classView3.visibility = View.INVISIBLE
        }
        for(indexNum in 0..5) {
            if(position == indexNum) {
                holder.itemView.class_item.setBackgroundColor(Color.parseColor("#CCE5FF"))
                holder.itemView.classView1.visibility = View.INVISIBLE
                holder.itemView.classView2.visibility = View.INVISIBLE
                holder.itemView.classView3.visibility = View.INVISIBLE
            }
        }*/
//        classItems[position].let{ item ->
//            with(holder) {
//                tvClass.text = item.content
//            }
//        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(context).inflate(R.layout.class_item, parent, false)
        return Holder(view)
    }

    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameTv = itemView.findViewById<TextView>(R.id.class_item)

        fun bind(classSchedule: ClassSchedule) {
            nameTv?.text = classSchedule.name
        }
    }
}