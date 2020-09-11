package com.example.school_lunch.Fragment

import android.os.AsyncTask
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.school_lunch.Adapter.ClassPlusAdapter
import com.example.school_lunch.Room.ClassSchedule
import com.example.school_lunch.R
import com.example.school_lunch.Room.CSDB
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.fragment_infomation.*
import kotlinx.android.synthetic.main.plus_item.view.*

class InfomationF : BottomSheetDialogFragment(){
    private var plusRv :RecyclerView? = null
    private var classPlusAdapter : ClassPlusAdapter? = null

    private var csList = listOf<ClassSchedule>()
    private lateinit var db: CSDB
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_infomation, container, false)
        val saveBtn:Button = view.findViewById<Button>(R.id.saveBtn)
        db = Room.databaseBuilder(context!!, CSDB::class.java, "cs.db").build()
        classPlusAdapter = ClassPlusAdapter()

        plusRv = view.findViewById(R.id.plus_rv_list)
        plusRv?.adapter = classPlusAdapter
        plusRv?.layoutManager = GridLayoutManager(view.context,6)

//        val addRunnable = Runnable {
//            val newClass = ClassSchedule()
//            newClass.className = classTitle2.text.toString()
//            newClass.position = plus_rv_list.plus_date.text.toString().toInt()
//            db.csDao().insert(newClass)
//        }

        refreshData()

        saveBtn.setOnClickListener {
//            val addThread = Thread(addRunnable)
//            addThread.start()
            closeFragment()
        }

        return view
    }

    private fun closeFragment() {
        val trasaction = activity?.supportFragmentManager?.beginTransaction()
        trasaction?.remove(this)
        trasaction?.commit()
    }

//
//    private fun insert(){
//        val title : String = classTitle2?.text?.toString() ?: ""
//        val selectedClass : Set<Schedule> = classPlusAdapter?.getSelectedSchedule() ?: setOf()
//        if(title.isNotEmpty() && selectedClass.isNotEmpty()){
//            val schedule = ClassSchedule(selectedClass.toString(),title)
//            csdb?.CSDao()?.insert(schedule)
//            Toast.makeText(this.context,"Try to Save success", Toast.LENGTH_LONG).show()
//            Log.d("Try to Insert", selectedClass.toString())
//        }else{
//            Toast.makeText(this.context,"Please Fill All Data's", Toast.LENGTH_LONG).show()
//        }
//    }


//    private fun delete(){
//        val title : String = classTitle2?.text?.toString() ?: ""
//        val selectedClass : Set<Schedule> = classPlusAdapter?.getSelectedSchedule() ?: setOf()
//        if(title.isNotEmpty() && selectedClass.isNotEmpty()){
//            val schedule = ClassSchedule("",classTitle2.text.toString())
//            csdb?.CSDao()?.delete(schedule)
//            Toast.makeText(this.context,"Try to Save success", Toast.LENGTH_LONG).show()
//            Log.d("Try to Insert", selectedClass.toString())
//        }else{
//            Toast.makeText(this.context,"Please Fill All Data's", Toast.LENGTH_LONG).show()
//        }
//    }


    private fun refreshData(){
        val getScheduleTask = object : GetAllScheduleAsyncTask() {
            override fun onGetAllClassSchedule(scheduleList: List<ClassSchedule>) {
                csList = scheduleList
                val adapter = ClassPlusAdapter()
                plus_rv_list.adapter = adapter
            }
        }
        getScheduleTask.execute(db)
    }



    data class Schedule(val day: Int, val order: Int)

    abstract class GetAllScheduleAsyncTask : AsyncTask<CSDB, Unit, List<ClassSchedule>>() {
        override fun doInBackground(vararg params: CSDB?): List<ClassSchedule> {
            val db = params[0]
            return  db?.csDao()?.getAll() ?: ArrayList()
        }

        override fun onPostExecute(result: List<ClassSchedule>?) {
        super.onPostExecute(result)
        onGetAllClassSchedule(result ?: ArrayList())
    }

    abstract fun onGetAllClassSchedule(scheduleList: List<ClassSchedule>)
}
}

