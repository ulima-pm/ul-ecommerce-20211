package pe.edu.ulima.pm.ulecommerce.views

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import pe.edu.ulima.pm.ulecommerce.MainActivity

interface OnFaceClickListener {
    fun onClick(view : ULFaceView)
}

class ULFaceView: View {
//    constructor(context : Context) : super(context) {
//        // Por codigo
//    }
    var paint : Paint? = null
    var size : Int? = null
    var anchoBoca : Float? = null
    var altoBoca : Float? = null

    private var listener  : OnFaceClickListener? = null

    constructor(context : Context, attrs : AttributeSet) : super(context, attrs) {
        // Por xml
        paint = Paint()
        size = 1000
    }

    fun setOnFaceClickListener(listener : OnFaceClickListener) {
        this.listener = listener
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        this.listener?.onClick(this)// [ULFaceView : View] ---> [OnFaceClickListener]
        invalidate()
        return super.onTouchEvent(event)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val width = View.MeasureSpec.getSize(widthMeasureSpec)
        val height = View.MeasureSpec.getSize(heightMeasureSpec)

        size = Math.min(width, height)
        anchoBoca = size!!/2f
        altoBoca = size!!/4f
        setMeasuredDimension(size!!, size!!)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        drawFaceFondo(canvas)
        drawBoca(canvas)
        drawOjos(canvas)
    }

    private fun drawOjos(canvas: Canvas?) {
        paint!!.color = Color.BLACK
        paint!!.style = Paint.Style.FILL_AND_STROKE
        canvas!!.drawCircle(
                size!! / 4f,
                size!!/ 4f,
                size!! / 8f,
                paint!!
        )

        canvas!!.drawCircle(
                size!! / 2f + size!!/4 ,
                size!!/ 4f,
                size!! / 8f,
                paint!!
        )
    }

    private fun drawBoca(canvas: Canvas?) {
        val mouthRect = RectF(
                size!!/2f - anchoBoca!! / 2f,
                size!!/2f + size!!/4 - altoBoca!!/2f,
                size!!/2f + anchoBoca!! / 2f,
                size!!/2f + size!!/4f + altoBoca!! / 2f!!
        )
        paint!!.color = Color.BLACK
        paint!!.style = Paint.Style.FILL_AND_STROKE
        canvas!!.drawOval(mouthRect, paint!!)
    }

    private fun drawFaceFondo(canvas: Canvas?) {
        paint!!.color = Color.YELLOW
        paint!!.style = Paint.Style.FILL_AND_STROKE

        canvas!!.drawCircle(size!!/2f, size!!/2f, size!!/2f, paint!!)

        paint!!.color = Color.BLACK
        paint!!.style = Paint.Style.STROKE

        canvas!!.drawCircle(size!!/2f, size!!/2f, size!!/2f, paint!!)
    }
}