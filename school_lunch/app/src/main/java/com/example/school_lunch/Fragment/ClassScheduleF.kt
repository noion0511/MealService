package com.example.school_lunch.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.school_lunch.Adapter.ClassAdapter
import com.example.school_lunch.R
import com.example.school_lunch.Room.CSDB
import com.example.school_lunch.Room.ClassSchedule
import com.google.android.material.floatingactionbutton.FloatingActionButton

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class ClassScheduleF : Fragment() {
    private var param1: String? = null
    private var param2: String? = null
    var class_rv_list : RecyclerView? = null

    private var csdb : CSDB? = null
    private var csList = listOf<ClassSchedule>()

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
        class_rv_list?.adapter = ClassAdapter()
        class_rv_list?.layoutManager = GridLayoutManager(view.context,6)

        val classPlus = view.findViewById<FloatingActionButton>(R.id.class_plus_button)
        classPlus.setOnClickListener {
            val fragmentManager = activity?.supportFragmentManager ?: throw Exception("Can't get fragment manager")
            val bottomSheetDialogFragment = InfomationF()
            val transaction = fragmentManager.beginTransaction();
            transaction.add(R.id.overlay_layout, bottomSheetDialogFragment)
            transaction.commit()
        }
        return view
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ClassScheduleF().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    data class ClassData(val content:String)
}
