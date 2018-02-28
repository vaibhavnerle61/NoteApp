package com.example.pooja.notepad.database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, 1) {
    companion object {
        private const val DATABASE_NAME="user.db"
        private const val TABLE_NAME="tbl_user"
        private const val CAL_NO1="Category_img"
    }
    fun insertData(Category_img: ByteArray): Boolean {
        val db=writableDatabase
        val cv=ContentValues()
        cv.put(CAL_NO1, Category_img)
        val result=db.insert(TABLE_NAME, null, cv)
        return if(result.equals(-1))
            false
             else
               true
    }
    fun getdata(): ByteArray {
        val db=writableDatabase
        val res=db.rawQuery("select * from " + TABLE_NAME, null)
        if (res.moveToFirst()) {
            do {
                return res.getBlob(0)
            } while (res.moveToNext())
        }
        return byteArrayOf()
    }
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("Create Table $TABLE_NAME(ID INTEGER PRIMARY KEY AUTOINCREMENT,Category_img BLOB)")
    }
    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME)
        onCreate(db)
    }
}