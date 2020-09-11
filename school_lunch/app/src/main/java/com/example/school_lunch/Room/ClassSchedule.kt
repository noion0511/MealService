package com.example.school_lunch.Room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.school_lunch.Fragment.InfomationF

@Entity(tableName = "cstable")
data class ClassSchedule(
    @PrimaryKey(autoGenerate = true) var id: Int,
    var position: Int,
    var className: String
) {
    constructor() : this(0, 0,"")
}