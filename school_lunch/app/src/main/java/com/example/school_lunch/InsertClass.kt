package com.example.school_lunch

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import com.example.school_lunch.Fragment.ClassScheduleF
import com.example.school_lunch.Room.ClassSchedule
import com.example.school_lunch.Room.ClassTableDB
import kotlinx.android.synthetic.main.activity_insert_class.*

class InsertClass : AppCompatActivity() {
    private var ctDb : ClassTableDB? = null

    var list_of_days = arrayOf("월요일", "화요일", "수요일", "목요일", "금요일")
    var list_of_period = arrayOf("1", "2", "3", "4", "5", "6", "7", "8")
    val listDays = spinner1.selectedItem.toString()
    val listPeriod = spinner2.selectedItem.toString()

    var height = 0
    var width = 0
    var position = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_insert_class)

        spinner1.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, list_of_days)
        spinner2.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, list_of_period)
        CreateTimeTable()

        ctDb = ClassTableDB.getInstance(this)
        val addRunnable = Runnable {
            val newClass = ClassSchedule()
            newClass.id = position
            newClass.name = addName.text.toString()
            ctDb?.classTableDao()?.update(newClass)
        }

        cancelBtn.setOnClickListener {
            finish()
        }

        saveBtn.setOnClickListener {
            val addThread = Thread(addRunnable)
            addThread.start()
            finish()
        }
    }

    private fun CreateTimeTable() {
        width = when(listDays){
            "월요일" -> 1
            "화요일" -> 2
            "수요일" -> 3
            "목요일" -> 4
            "금요일" -> 5
            else -> 0
        }
        height = when(listPeriod){
            "1" -> 1
            "2" -> 2
            "3" -> 3
            "4" -> 4
            "5" -> 5
            else -> 0
        }

        position = height*6 + height
    }
}