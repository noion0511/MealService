package com.example.school_lunch.Fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.school_lunch.Adapter.ClassPlusAdapter
import com.example.school_lunch.ClassSchedule
import com.example.school_lunch.DataBaseHandler
import com.example.school_lunch.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.fragment_infomation.*
import kotlinx.android.synthetic.main.plus_item.*

class InfomationF : BottomSheetDialogFragment(){
    private val db = DataBaseHandler(this.context)
    private var plusRv :RecyclerView? = null
    private var classPlusAdapter : ClassPlusAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_infomation, container, false)
        val saveBtn:Button = view.findViewById<Button>(R.id.saveBtn)
        val deleteBtn:Button = view.findViewById<Button>(R.id.deleteBtn)
        classPlusAdapter = ClassPlusAdapter()

        plusRv = view.findViewById(R.id.plus_rv_list)
        plusRv?.adapter = classPlusAdapter
        plusRv?.layoutManager = GridLayoutManager(view.context,6)

        saveBtn.setOnClickListener {
            insert()
        }
        deleteBtn.setOnClickListener{
            db.deleteData()
        }

        return view
    }


    private fun insert(){
        val title : String = classTitle2?.text?.toString() ?: ""
        val selectedClass : Set<Schedule> = classPlusAdapter?.getSelectedSchedule() ?: setOf()
        if(title.isNotEmpty() && selectedClass.isNotEmpty()){
//            val schedule = ClassSchedule(plus_date.text.toString().toInt(),classTitle2.text.toString())
//            db.insertData(schedule)
            Toast.makeText(this.context,"Try to Save success", Toast.LENGTH_LONG).show()
            Log.d("Try to Insert", selectedClass.toString())
        }else{
            Toast.makeText(this.context,"Please Fill All Data's", Toast.LENGTH_LONG).show()
        }
    }

    data class Schedule(val day: Int, val order: Int)
}

