package app.anysa.ui.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import app.anysa.R
import kotlinx.android.synthetic.main.view_gradiented_button.view.*


class GradientedButton : ConstraintLayout {

    @JvmOverloads
    constructor(context: Context, attributes: AttributeSet? = null, defStyleAttr: Int = 0)
            : super(context, attributes, defStyleAttr) {

        View.inflate(context, R.layout.view_gradiented_button, this)
        attributes?.let { handleAttrs(context, attributes, defStyleAttr) }
    }

    var text: String?
        get() = tv_text.text.toString()
        set(text) {
            tv_text.text = text
        }

    var textColor: Int
        get() = tv_text.currentTextColor
        set(color) {
            tv_text.setTextColor(color)
        }

    var isLoading: Boolean
        get() = pb_loading.visibility == View.VISIBLE
        set(loading) {
            pb_loading.visibility = if (loading) View.VISIBLE else View.GONE
            cl_root.isEnabled = !loading
        }

    private fun handleAttrs(context: Context, attrs: AttributeSet, defStyleAttr: Int) {
        val a = context.theme.obtainStyledAttributes(attrs,
                R.styleable.GradientedButton, defStyleAttr, 0)
        try {
            val text = a.getString(R.styleable.GradientedButton_gb_text)

            if (a.hasValue(R.styleable.GradientedButton_gb_text_color)) {
                textColor = a.getColor(R.styleable.GradientedButton_gb_text_color, ContextCompat.getColor(context, R.color.white))
            }

            if (a.hasValue(R.styleable.GradientedButton_gb_background)) {
                val background = a.getDrawable(R.styleable.GradientedButton_gb_background)
                cl_root.background = background
            }

            this.text = text
        } finally {
            a.recycle()
        }
    }

    override fun setOnClickListener(l: OnClickListener?) {
        cl_root.setOnClickListener(l)
    }
}