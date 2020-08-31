package com.example.school_lunch.Fragment

import android.annotation.SuppressLint
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
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


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_infomation, container, false)
        val saveBtn:Button = view.findViewById<Button>(R.id.saveBtn)
        val deleteBtn:Button = view.findViewById<Button>(R.id.deleteBtn)

        saveBtn.setOnClickListener {
            insert()
        }
        deleteBtn.setOnClickListener{
            db.deleteData()
        }

        return view
    }


    @SuppressLint("RestrictedApi")
    override fun setupDialog(dialog: Dialog, style: Int) {
        super.setupDialog(dialog, style)
        val view = View.inflate(context, R.layout.fragment_infomation, null)

        plusRv = view.findViewById(R.id.plus_rv_list)
        plusRv?.adapter = ClassPlusAdapter()
        plusRv?.layoutManager = GridLayoutManager(view.context,6)

        dialog.setContentView(view)
    }


    private fun insert(){
        if(classTitle2.text.toString().isNotEmpty() && plus_date.text.toString().isNotEmpty()){
            val schedule = ClassSchedule(plus_date.text.toString().toInt(),classTitle2.text.toString())
            db.insertData(schedule)
        }else{
            Toast.makeText(this.context,"Please Fill All Data's", Toast.LENGTH_LONG).show()
        }
    }
}