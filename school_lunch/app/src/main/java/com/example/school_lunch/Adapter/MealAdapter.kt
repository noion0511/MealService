package com.example.school_lunch.Adapter

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.school_lunch.Fragment.MealServiceF
import com.example.school_lunch.R
import kotlinx.android.synthetic.main.fragment_meal_service.view.*
import kotlinx.android.synthetic.main.meal_item.view.*
import kotlinx.android.synthetic.main.meal_item.view.lunch_contents
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class MealAdapter(val items: List<MealServiceF.Item>, val context: Context?) : RecyclerView.Adapter<MealAdapter.ViewHolder>(){

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.meal_item, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val current = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("MM")
        val formatted = current.format(formatter)
        holder.itemView.meal_month?.text = formatted
        holder.itemView.meal_title?.text = items.get(position).day.toString()
        holder.itemView.lunch_contents?.text = items.get(position).meal
        val pos = holder.adapterPosition
        if(pos != RecyclerView.NO_POSITION) {
            if(pos == 2){
                holder.itemView.meal_item.meal_title.setBackgroundColor(Color.parseColor("#fddb3a"))
                holder.itemView.meal_item.meal_title_day.setBackgroundColor(Color.parseColor("#fddb3a"))
                holder.itemView.meal_item.meal_title_month.setBackgroundColor(Color.parseColor("#fddb3a"))
                holder.itemView.meal_item.meal_month.setBackgroundColor(Color.parseColor("#fddb3a"))
                holder.itemView.meal_item.meal_title.setTextColor(Color.parseColor("#000000"))
                holder.itemView.meal_item.meal_title_day.setTextColor(Color.parseColor("#000000"))
                holder.itemView.meal_item.meal_title_month.setTextColor(Color.parseColor("#000000"))
                holder.itemView.meal_item.meal_month.setTextColor(Color.parseColor("#000000"))
            }
        }
    }
}