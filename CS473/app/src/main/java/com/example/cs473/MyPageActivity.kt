package com.example.cs473
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.view.View

class CustomView(context: Context) : View(context) {
    private val paint = Paint()
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        paint.color = Color.rgb(112, 255, 245) // Set the paint color to red
        canvas.drawRect(100f, 100f, 200f, 200f, paint) // Draw a rectangle
    }
}
class MyPageActivity {


}