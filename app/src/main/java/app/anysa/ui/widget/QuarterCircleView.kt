package app.anysa.ui.widget

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import app.anysa.R

class QuarterCircleView @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private val path = Path()
    private val fill = Paint(Paint.ANTI_ALIAS_FLAG)

    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        val arcSweep = 100f
        val arcOffset = (180 - arcSweep) / 2

        val outerRect = RectF(0f, 0f, width.toFloat(), width.toFloat())

        path.arcTo(outerRect, arcOffset, arcSweep)

        path.close()

        fill.color = context.resources.getColor(R.color.avatarEditBackground)
        canvas?.drawPath(path, fill)
    }
}