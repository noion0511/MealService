package com.example.school_lunch.Room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ClassTableDao {
    @Query("SELECT * FROM classTable")
    fun getAll(): List<ClassSchedule>

    @Insert
    fun insert(vararg ClassSchedule: ClassSchedule)

    @Delete
    fun delete(ClassSchedule: ClassSchedule)

    @Delete
    fun deleteAll()

}