package com.example.pooja.notepad.activity

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
/**
 * Created by Pooja on 27-02-2018.
 */
class DrawView : View {

    constructor(context: Context?) : super(context) {
        // setUpDrawing()
    }
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        // setUpDrawing()
    }
    val drawPath = Path()
    val drawPaint = Paint()
    var canvasPaint: Paint? = null
    var paintColor = 0x00000000
    var drawCanvas: Canvas? = null
    var canvasBitmap: Bitmap? = null
    fun setUpDrawing() {
        // drawPath = Path()
        //  drawPaint.color = paintColor.toInt()
        drawPaint.setColor(paintColor)
        drawPaint.isAntiAlias = true
        drawPaint.strokeWidth = 10f
        drawPaint.style = Paint.Style.STROKE
        drawPaint.strokeJoin = Paint.Join.ROUND
        drawPaint.strokeCap = Paint.Cap.ROUND
        canvasPaint = Paint(Paint.DITHER_FLAG)
        // drawPaint.isDither = true
    }
    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        canvasBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888)
        drawCanvas = Canvas(canvasBitmap)
    }
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.drawBitmap(canvasBitmap, 0f, 0f, canvasPaint)
        canvas?.drawPath(drawPath, drawPaint)
    }
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        var touchX: Float = event!!.x
        var touchY: Float = event!!.y
        when (event.action) {
            MotionEvent.ACTION_DOWN -> drawPath?.moveTo(touchX, touchY)
            MotionEvent.ACTION_MOVE -> drawPath?.lineTo(touchX, touchY)
            MotionEvent.ACTION_UP -> {
                drawCanvas?.drawPath(drawPath, drawPaint)
                drawPath?.reset()
            }
            else -> return false
        }
        invalidate()
        return true
    }
    fun setColor(newColor: String) {
        invalidate()
        paintColor = Color.parseColor(newColor)
        drawPaint.color = paintColor
    }
}