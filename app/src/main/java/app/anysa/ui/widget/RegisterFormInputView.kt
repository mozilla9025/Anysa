package app.anysa.ui.widget

import android.content.Context
import android.telephony.PhoneNumberFormattingTextWatcher
import android.text.Editable
import android.text.InputType
import android.text.TextUtils
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import app.anysa.R
import app.anysa.helper.locale.ApplicationLanguage
import kotlinx.android.synthetic.main.view_register_form_input.view.*


class RegisterFormInputView : ConstraintLayout {

    companion object {
        val TYPE_TEXT = 0
        val TYPE_PHONE = 1
        val TYPE_PASSWORD = 2
    }

    @JvmOverloads
    constructor(context: Context, attributes: AttributeSet? = null, defStyleAttr: Int = 0)
            : super(context, attributes, defStyleAttr) {

        View.inflate(context, R.layout.view_register_form_input, this)
        initListeners()
        attributes?.let { handleAttrs(context, attributes, defStyleAttr) }
    }
    var text: String
        get() = edit_text.text.toString()
        set(text) = edit_text.setText(text)


    private fun initListeners() {
        edit_text.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                setError(null)
            }

            override fun afterTextChanged(s: Editable) {
            }
        })
    }

    private fun handleAttrs(context: Context, attrs: AttributeSet, defStyleAttr: Int) {
        val a = context.theme.obtainStyledAttributes(attrs,
                R.styleable.RegisterFormInputView, defStyleAttr, 0)

        try {
            val text = a.getString(R.styleable.RegisterFormInputView_rfiv_text)
            val hint = a.getString(R.styleable.RegisterFormInputView_rfiv_hint)
            val title = a.getString(R.styleable.RegisterFormInputView_rfiv_title)
            val type = a.getInteger(R.styleable.RegisterFormInputView_rfiv_type, TYPE_TEXT)
            val maxLines = a.getInteger(R.styleable.RegisterFormInputView_rfiv_maxLines, 1)

            tv_title.text = title
            edit_text.setText(text)
            edit_text.hint = hint
            edit_text.maxLines = maxLines

            when (type) {
                TYPE_TEXT -> {
                }
                TYPE_PASSWORD -> {
                    text_input_layout.isPasswordVisibilityToggleEnabled = true
                    edit_text.inputType = InputType.TYPE_TEXT_VARIATION_PASSWORD
                }
                TYPE_PHONE -> {
                    text_input_layout.isPasswordVisibilityToggleEnabled = true
                    edit_text.inputType = InputType.TYPE_CLASS_PHONE
                    edit_text.addTextChangedListener(PhoneNumberFormattingTextWatcher(ApplicationLanguage.CN.languageCode))
                }
            }
        } finally {
            a.recycle()
        }
    }

    fun setError(errorText: String?) {
        if (!TextUtils.isEmpty(errorText)) {
            if (tv_error.visibility != View.VISIBLE)
                tv_error.visibility = View.VISIBLE
            if (!edit_text.isSelected)
                edit_text.isSelected = true

            tv_error.text = errorText
        } else {
            tv_error.text = null
            if (tv_error.visibility != View.GONE)
                tv_error.visibility = View.GONE
            if (edit_text.isSelected)
                edit_text.isSelected = false
        }
    }
}