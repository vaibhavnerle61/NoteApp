package com.example.pooja.notepad.activity

import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import com.example.pooja.notepad.R

class PaintActivity : AppCompatActivity() {
    var drawView : DrawView? = null
    var currentPaint : ImageButton? = null
    var paintLayout : LinearLayout? = null

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_paint)
        drawView = findViewById<DrawView>(R.id.drawView)
        paintLayout = findViewById(R.id.colorPalette)
        currentPaint = paintLayout?.getChildAt(0) as ImageButton?
        currentPaint?.setImageDrawable(resources.getDrawable(R.drawable.paint_pressed, null)
        )

        fun onDrawClcik (view: View?) {
            drawView?.setUpDrawing()
        }

        fun paintClicked(view: View?) {
            if (view != currentPaint) {
                var imageView: ImageView = view as ImageView
                var color: String = view.getTag().toString()
                drawView?.setColor(color)
                imageView.setImageDrawable(resources.getDrawable(R.drawable.paint_pressed, null))
                currentPaint?.setImageDrawable(resources.getDrawable(R.drawable.paint, null))
                currentPaint = view as ImageButton
            }
        }

        fun chooseBrushSize(view: View?) {
        }
    }
}
