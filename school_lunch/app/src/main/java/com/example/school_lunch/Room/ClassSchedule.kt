package com.example.school_lunch.Room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "classTable")
data class ClassSchedule(
    @PrimaryKey(autoGenerate = true) var id: Int?,
    var name: String
) {
    constructor() : this(null,"")
}