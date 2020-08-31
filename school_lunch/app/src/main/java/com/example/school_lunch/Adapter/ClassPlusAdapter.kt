package com.example.school_lunch.Adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.school_lunch.Fragment.InfomationF
import com.example.school_lunch.R
import kotlinx.android.synthetic.main.plus_item.view.*

class ClassPlusAdapter : RecyclerView.Adapter<ClassPlusAdapter.ClassViewHolder>(){
    val selectedItemPosition = HashSet<InfomationF.Schedule>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClassViewHolder =
        ClassViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.plus_item, parent, false), this)

    override fun getItemCount(): Int = 54

    override fun onBindViewHolder(holder: ClassViewHolder, position: Int) {
        val xPos = position % 6
        val yPos = position / 6

        holder.xPos = xPos
        holder.yPos = yPos
        holder.tvClass.text = when {
            yPos == 0 -> {
                when(xPos) {
                    1 -> "월"
                    2 -> "화"
                    3 -> "수"
                    4 -> "목"
                    5 -> "금"
                    else -> ""
                }
            }
            xPos == 0 -> {
                when(yPos) {
                    1 -> "1"
                    2 -> "2"
                    3 -> "3"
                    4 -> "4"
                    5 -> "5"
                    6 -> "6"
                    7 -> "7"
                    8 -> "8"
                    else -> ""
                }
            }
            else -> {
                ""
            }
        }
        holder.tvClass.setBackgroundColor(if(selectedItemPosition.contains(InfomationF.Schedule(xPos, yPos))) Color.parseColor("#fddb3a") else Color.WHITE)
    }

    data class ClassViewHolder(val view: View, val adapter: ClassPlusAdapter): RecyclerView.ViewHolder(view) {
        var xPos = 0
        var yPos = 0
        val tvClass: TextView = view.findViewById(R.id.plus_date)
        init {
            tvClass.setOnClickListener {
                adapter.onItemClicked(xPos, yPos)
            }
        }
    }

    private fun onItemClicked(xPos: Int, yPos: Int) {
        val position = InfomationF.Schedule(xPos, yPos)
        val recyclerViewPosition = yPos * 6 + xPos

        if( xPos == 0 || yPos == 0) {
            return
        } else if(selectedItemPosition.contains(position)) {
            selectedItemPosition.remove(position)
        } else {
            selectedItemPosition.add(position)
        }

        notifyItemChanged(recyclerViewPosition)
    }

    fun getSelectedSchedule(): Set<InfomationF.Schedule> {
        return selectedItemPosition
    }
}

