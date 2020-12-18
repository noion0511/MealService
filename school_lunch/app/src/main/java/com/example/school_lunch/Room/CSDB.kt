package com.example.school_lunch.Room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [ClassSchedule::class], version = 4)
abstract class ClassTableDB : RoomDatabase() {
    abstract fun classTableDao(): ClassTableDao
    companion object {
        private var INSTANCE: ClassTableDB? = null

        fun getInstance(context: Context): ClassTableDB? {
            if(INSTANCE == null) {
                synchronized(ClassTableDB::class) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        ClassTableDB::class.java,"classtable.db")
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }
            return INSTANCE
        }
        fun destroyInstance() {
            INSTANCE = null
        }
    }
}

