package com.mauriciog.kotlincrudbasic

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DataBaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, 1 ){
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL ("CREATE TABLE $TABLE_NAME(ID INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT" +
        ",EMAIL TEXT, MOBILE INTEGER)")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
    }

    //Recuperar Datos
    val allData : Cursor
    get() {val db = this.writableDatabase
        return db.rawQuery("Select * from ${TABLE_NAME}", null)
    }


    fun insertData(name : String, email : String, mobile : String): Boolean{
        val db = this.writableDatabase
        val cv = ContentValues()
        cv.put(COL_2, name)
        cv.put(COL_3, email)
        cv.put(COL_4, mobile)

        val result = db.insert(TABLE_NAME, null, cv)
        db.close()
        return if (result.equals(-1)){
            false
        }else{
            true
        }
    }

    companion  object{
        val DATABASE_NAME = "Student.db"
        val TABLE_NAME    = "Student_table"


        val COL_1 = "ID"
        val COL_2 = "NAME"
        val COL_3 = "EMAIL"
        val COL_4 = "MOBILE"
    }
}