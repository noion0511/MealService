package com.example.school_lunch.Room

import androidx.room.*

@Dao
interface ClassTableDao {
    @Query("SELECT * FROM classTable")
    fun getAll(): List<ClassSchedule>

    @Insert
    fun insert(vararg ClassSchedule: ClassSchedule)

    @Update
    fun update(vararg ClassSchedule: ClassSchedule)

    @Delete
    fun delete(ClassSchedule: ClassSchedule)
}