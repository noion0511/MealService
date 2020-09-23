package com.example.school_lunch.Adapter

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.school_lunch.Fragment.NoticeF
import com.example.school_lunch.R
import kotlinx.android.synthetic.main.notice_item.view.*

class NoticeAdapter(private val items: List<NoticeF.NoticeItem>, val context: Context?) : RecyclerView.Adapter<NoticeAdapter.ViewHolder>(){
    data class ViewHolder(var view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.notice_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.notice_title?.text = items[position].title
        holder.itemView.notice_writer?.text = items[position].writer
        holder.itemView.notice_day?.text = items[position].day
        holder.itemView.notice_number?.text = items[position].number

        if(items[position].number=="공 지"){
            holder.itemView.notice_number.setBackgroundColor(Color.parseColor("#fddb3a"))
        }
        else{
            holder.itemView.notice_number.setBackgroundColor(Color.parseColor("#FFFFFF"))
        }
        holder.itemView.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(items[position].url)
            context?.startActivity(intent)
        }
    }

}