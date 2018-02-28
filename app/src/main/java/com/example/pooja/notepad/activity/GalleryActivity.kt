package com.example.pooja.notepad.activity

import android.app.Activity
import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Bitmap
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.ImageView
import com.example.pooja.notepad.R
import com.example.pooja.notepad.database.DatabaseHelper
import java.io.ByteArrayOutputStream


class GalleryActivity : AppCompatActivity() {

    var dbSq: DatabaseHelper=DatabaseHelper(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_write_note)

        val imgView= findViewById<ImageView>(R.id.imgView)
        imgView.setOnClickListener { selectImage() }
    }
    private fun selectImage() {
        val options=arrayOf<CharSequence>("Take Photo", "Choose from Gallery", "Cancel")
        val builder=AlertDialog.Builder(this@GalleryActivity)
        builder.setTitle("Add Photo!")
        builder.setItems(options, DialogInterface.OnClickListener { dialog, item ->
            when {
                options[item] == "Take Photo" -> {
                    val cameraIntent=Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                    startActivityForResult(cameraIntent, 101)
                }
                options[item] == "Choose from Gallery" -> {
                    val intent=Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                    startActivityForResult(intent, 2)
                }
                options[item] == "Cancel" -> dialog.dismiss()
            }
        }).show()
    }
    public override fun onActivityResult(requestcode: Int, resultcode: Int, intent: Intent){
        super.onActivityResult(requestcode, resultcode, intent)
        if (resultcode == Activity.RESULT_OK) {
            if (requestcode == 101) {
                val photo=intent.extras!!.get("data") as Bitmap
                val stream=ByteArrayOutputStream()
                photo.compress(Bitmap.CompressFormat.PNG, 100, stream)
                val byteArray=stream.toByteArray()
                Log.d("check",dbSq.insertData(byteArray).toString().plus(" "))
            }
        }
    }
}
