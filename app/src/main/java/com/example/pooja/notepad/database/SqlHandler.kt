package com.example.pooja.notepad.database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

/**
 * Created by Pooja on 15-02-2018.
 * */

class SqliteHandler (context : Context) : SQLiteOpenHelper(context, dbname, factory, version) {

    companion object {
        internal val dbname = "mydatabase"
        internal val factory = null
        internal val version = 1
    }

    fun insertName(name: String, message: String) {
        val db: SQLiteDatabase = writableDatabase
        val values: ContentValues
        values = ContentValues()
        values.put("", name)
        values.put("", message)
        db.insert("NotesApp", null, values)
    }

    fun fetchName(): ArrayList<String> {
        var list: ArrayList<String>
        list = arrayListOf()
        val db: SQLiteDatabase = readableDatabase
        val c = db.rawQuery("select * from NotesApp", null)
        while (c.moveToNext()) {
            var name: String = c.getString(c.getColumnIndex("name"))
            var message: String = c.getString(c.getColumnIndex("message"))
            list.add(name)
            list.add(message)
        }
       return list
    }
    override fun onCreate(p0: SQLiteDatabase?) {
        p0?.execSQL("create table NotesApp (id integer primary key autoincrement,name varchar(256),message varchar(256))")
    }
    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}