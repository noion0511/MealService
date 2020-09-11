package com.example.school_lunch.Room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [ClassSchedule::class], version = 1)
abstract class CSDB : RoomDatabase() {
    abstract fun csDao(): CSDao
}
