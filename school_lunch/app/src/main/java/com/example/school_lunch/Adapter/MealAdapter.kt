package com.example.school_lunch.Adapter

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.school_lunch.Fragment.MealServiceF
import com.example.school_lunch.R
import kotlinx.android.synthetic.main.meal_item.view.*

class MealAdapter(val items: List<MealServiceF.Item>, val context: Context?) : RecyclerView.Adapter<MealAdapter.ViewHolder>(){

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.meal_item, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.meal_title?.text = items.get(position).day.toString()
        holder.itemView.meal_contents?.text = items.get(position).meal
    }
}