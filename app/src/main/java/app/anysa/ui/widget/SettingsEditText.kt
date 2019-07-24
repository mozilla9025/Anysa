package app.anysa.ui.widget

import android.content.Context
import android.text.InputType
import android.util.AttributeSet
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import app.anysa.R
import kotlinx.android.synthetic.main.view_settings_edittext.view.*


class SettingsEditText : ConstraintLayout {

    @JvmOverloads
    constructor(context: Context, attributes: AttributeSet? = null, defStyleAttr: Int = 0)
            : super(context, attributes, defStyleAttr) {

        View.inflate(context, R.layout.view_settings_edittext, this)
        attributes?.let { handleAttrs(context, attributes, defStyleAttr) }
    }

    var title: String?
        get() = tv_title.text.toString()
        set(text) {
            tv_title.text = text
        }

    var text: String
        get() = et_text.text.toString()
        set(text) = et_text.setText(text)

    var hint: String?
        get() = et_text.hint.toString()
        set(text) {
            et_text.hint = text
        }

    var maxLines: Int
        get() = et_text.maxLines
        set(maxLines) {
            et_text.maxLines = maxLines
            et_text.minLines = if (maxLines > 3) maxLines - 2 else 1
        }

    private fun handleAttrs(context: Context, attrs: AttributeSet, defStyleAttr: Int) {
        val a = context.theme.obtainStyledAttributes(attrs,
                R.styleable.SettingsEditText, defStyleAttr, 0)

        try {

            val title = a.getString(R.styleable.SettingsEditText_set_title)
            var text = a.getString(R.styleable.SettingsEditText_set_text)
            val hint = a.getString(R.styleable.SettingsEditText_set_hint)
            val maxLines = a.getInteger(R.styleable.SettingsEditText_set_maxlines, 1)
            val isPassword = a.getBoolean(R.styleable.SettingsEditText_set_isPassword, false)

            if(!isPassword){
                et_text.inputType = InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS
            }

            if (text == null) text = ""

            this.title = title
            this.text = text
            this.hint = hint
            this.maxLines = maxLines
        } finally {
            a.recycle()
        }
    }
}