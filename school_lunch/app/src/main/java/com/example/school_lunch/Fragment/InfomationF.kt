package com.example.school_lunch.Fragment

import android.annotation.SuppressLint
import android.app.Dialog
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.school_lunch.Adapter.ClassPlusAdapter
import com.example.school_lunch.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class InfomationF : BottomSheetDialogFragment(){
    var plus_rv_list :RecyclerView? = null

    @SuppressLint("RestrictedApi")
    override fun setupDialog(dialog: Dialog, style: Int) {
        super.setupDialog(dialog, style)
        val view = View.inflate(context, R.layout.fragment_infomation, null)

        plus_rv_list = view.findViewById(R.id.plus_rv_list)
        plus_rv_list?.adapter = ClassPlusAdapter()
        plus_rv_list?.layoutManager = GridLayoutManager(view.context,6)

        dialog.setContentView(view)
    }
}