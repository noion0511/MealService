package com.example.school_lunch.Fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.school_lunch.Adapter.ClassAdapter
import com.example.school_lunch.InsertClass
import com.example.school_lunch.R
import com.example.school_lunch.Room.ClassSchedule
import com.example.school_lunch.Room.ClassTableDB
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.lang.Exception

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class ClassScheduleF : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    var class_rv_list : RecyclerView? = null
    private var ctdb : ClassTableDB? = null
    private var ctList = listOf<ClassSchedule>()
    lateinit var mAdapter : ClassAdapter

    private var classItems: List<ClassSchedule> = listOf(
        ClassSchedule(0,""), ClassSchedule(1,"월"), ClassSchedule(2,"화"), ClassSchedule(3,"수"), ClassSchedule(4,"목"),
        ClassSchedule(5,"금"), ClassSchedule(6,"1"), ClassSchedule(7,""), ClassSchedule(8,""), ClassSchedule(9,""),
        ClassSchedule(10,""), ClassSchedule(11,""), ClassSchedule(12,"2"), ClassSchedule(13,""), ClassSchedule(14,""),
        ClassSchedule(15,""), ClassSchedule(16,""), ClassSchedule(17,""), ClassSchedule(18,"3"), ClassSchedule(19,""),
        ClassSchedule(20,""), ClassSchedule(21,""), ClassSchedule(22,""), ClassSchedule(23,""), ClassSchedule(24,"4"),
        ClassSchedule(25,""), ClassSchedule(26,""), ClassSchedule(27,""), ClassSchedule(28,""), ClassSchedule(29,""),
        ClassSchedule(30,"5"), ClassSchedule(31,""), ClassSchedule(32,""), ClassSchedule(33,""), ClassSchedule(34,""),
        ClassSchedule(35,""), ClassSchedule(36,"6"), ClassSchedule(37,""), ClassSchedule(38,""), ClassSchedule(39,""),
        ClassSchedule(40,""), ClassSchedule(41,""), ClassSchedule(42,"7"), ClassSchedule(43,""), ClassSchedule(44,""),
        ClassSchedule(45,""), ClassSchedule(46,""), ClassSchedule(47,""), ClassSchedule(48,"8"), ClassSchedule(49,""),
        ClassSchedule(50,""), ClassSchedule(51,""), ClassSchedule(52,""), ClassSchedule(53,"")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_class_schedule, container, false)

        class_rv_list = view.findViewById(R.id.class_rv_list)
        ctdb = ClassTableDB.getInstance(view.context)

        val r = Runnable {
            //데이터를 읽고 쓸때 사용?
            try {
                ctList = ctdb?.classTableDao()?.getAll()!!
                mAdapter = ClassAdapter(view.context, classItems)

                class_rv_list?.adapter = mAdapter
                class_rv_list?.layoutManager = GridLayoutManager(view.context,6)
                class_rv_list?.setHasFixedSize(true)

                mAdapter.notifyDataSetChanged()
            } catch (e: Exception) {
                Log.d("tag", "Error = $e")
            }
        }

        val thread = Thread(r)
        thread.start()

        val classPlus = view.findViewById<FloatingActionButton>(R.id.class_plus_button)
        classPlus.setOnClickListener {
            val intent = Intent(activity, InsertClass::class.java)
            startActivity(intent)
        }

        return view
    }

    override fun onDestroy() {
        ClassTableDB.destroyInstance()
        super.onDestroy()
    }
}
