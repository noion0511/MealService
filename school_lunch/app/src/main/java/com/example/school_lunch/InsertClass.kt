package com.example.school_lunch

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import com.example.school_lunch.Fragment.ClassScheduleF
import kotlinx.android.synthetic.main.activity_insert_class.*

class InsertClass : AppCompatActivity() {

    var list_of_days = arrayOf("월요일", "화요일", "수요일", "목요일", "금요일")
    var list_of_period = arrayOf("1", "2", "3", "4", "5", "6", "7", "8")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_insert_class)

        spinner1.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, list_of_days)
        spinner2.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, list_of_period)

        cancelBtn.setOnClickListener {
            finish()
        }
    }
}