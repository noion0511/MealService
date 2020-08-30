package com.example.school_lunch

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast

val DATABASE_NAME = "MyClass"
val TABLE_NAME = "ClassSchedules"
val COL_POSITION = "position"
val COL_NAME = "name"
val COL_ID = "id"

class DataBaseHandler(var context: Context?) :SQLiteOpenHelper(context, DATABASE_NAME, null, 1){
    override fun onCreate(db: SQLiteDatabase?) {
        val createTable = "CREATE TABLE $TABLE_NAME ($COL_ID INTEGER PRIMARY KEY AUTOINCREMENT,$COL_POSITION INTEGER,$COL_NAME VARCHAR(50))"
        db?.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun insertData(classSchedule:ClassSchedule){
        val db = this.writableDatabase
        val cv = ContentValues()
        cv.put(COL_POSITION,classSchedule.position)
        cv.put(COL_NAME,classSchedule.classname)

        val result = db.insert(TABLE_NAME,null,cv)

        if(result == (-1).toLong())
            Toast.makeText(context,"Failed",Toast.LENGTH_LONG).show()
        else
            Toast.makeText(context,"Success",Toast.LENGTH_LONG).show()
    }


    fun readData():MutableList<ClassSchedule>{
        val list :MutableList<ClassSchedule> = ArrayList()
        val db = this.readableDatabase
        val query = "Select * from $TABLE_NAME"
        val result: Cursor = db.rawQuery(query,null)

        if(result.moveToFirst()){
            do {
                val user = ClassSchedule()
                user.id = result.getInt(result.getColumnIndex(COL_ID))
                user.position = result.getInt(result.getColumnIndex(COL_POSITION))
                user.classname = result.getString(result.getColumnIndex(COL_NAME))
                list.add(user)
            }while (result.moveToNext())
        }else
            Toast.makeText(context,"There is no data.",Toast.LENGTH_LONG).show()

        result.close()
        db.close()
        return list
    }


    fun deleteData(){
        val db = this.writableDatabase

        db.delete(TABLE_NAME, null,null)
        db.close()
    }
}