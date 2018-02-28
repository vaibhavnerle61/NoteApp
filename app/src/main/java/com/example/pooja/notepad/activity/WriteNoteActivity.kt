package com.example.pooja.notepad.activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import com.example.pooja.notepad.R
import com.example.pooja.notepad.database.SqliteHandler
import com.example.pooja.notepad.fragment.MainActivityFragment
import kotlinx.android.synthetic.main.activity_write_note.*
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.toast


class WriteNoteActivity : AppCompatActivity() {
    lateinit var handler: SqliteHandler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_write_note)

        btnSave.setOnClickListener {
            insert()
            val intent = Intent(this@WriteNoteActivity, MainActivity::class.java)
            startActivity(intent)
        }
    }

    private fun insert(){
        handler = SqliteHandler(this)
        val txt: String = edtAddTitle.text.toString()
        val txtmsg: String = edtAddNote.text.toString()
        handler.insertName(txt, txtmsg)
        Toast.makeText(this@WriteNoteActivity, "Inserted Succefully", Toast.LENGTH_SHORT).show()
    }

//    fun onBrush (){
//        val  intent = Intent(this@WriteNoteActivity,PaintActivity ::class.java)
//        startActivity(intent)
//    }

}

