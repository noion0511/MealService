package com.example.school_lunch.Room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface CSDao {
    @Query("SELECT * FROM cstable")
    fun getAll(): List<ClassSchedule>

    @Insert
    fun insert(vararg cSchedule: ClassSchedule)

    @Delete
    fun delete(cSchedule: ClassSchedule)
}