package app.anysa.ui.widget

import android.annotation.SuppressLint
import android.content.Context
import android.telephony.PhoneNumberFormattingTextWatcher
import android.text.*
import android.text.method.DigitsKeyListener
import android.util.AttributeSet
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import app.anysa.R
import app.anysa.helper.locale.ApplicationLanguage
import kotlinx.android.synthetic.main.view_register_form_input.view.*
import java.text.DecimalFormatSymbols


class RegisterFormInputView : ConstraintLayout {

    companion object {
        const val TYPE_TEXT = 0
        const val TYPE_PHONE = 1
        const val TYPE_PASSWORD = 2
        const val TYPE_EMAIL = 3
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
            val isOptional = a.getBoolean(R.styleable.RegisterFormInputView_rfiv_isOptional, false)

            tv_title.text = title
            edit_text.setText(text)
            edit_text.hint = hint
            edit_text.maxLines = maxLines
            edit_text_phone.hint = hint
            tv_optional.visibility = if(isOptional) VISIBLE else GONE

            when (type) {
                TYPE_TEXT -> {
                    text_input_layout.visibility = View.VISIBLE
                    edit_text_phone.visibility = View.GONE

                    edit_text.inputType = InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS
                }
                TYPE_PASSWORD -> {
                    text_input_layout.visibility = View.VISIBLE
                    edit_text_phone.visibility = View.GONE

                    edit_text.inputType = InputType.TYPE_TEXT_VARIATION_PASSWORD
                }
                TYPE_PHONE -> {
                    text_input_layout.visibility = View.GONE
                    edit_text_phone.visibility = View.VISIBLE

                    edit_text_phone.inputType = InputType.TYPE_CLASS_PHONE
                    edit_text_phone.addTextChangedListener(PhoneNumberFormattingTextWatcher(ApplicationLanguage.CN.languageCode))

                    edit_text_phone.addTextChangedListener(object : TextWatcher {
                        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                        }

                        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                        }

                        @SuppressLint("SetTextI18n")
                        override fun afterTextChanged(s: Editable?) {
                            val text = edit_text_phone.text ?: return

                            if (text.isEmpty() or text.startsWith("+86")) return

                            if (text.startsWith("+")) {
                                if (text.startsWith("+8")) {
                                    if (!text.startsWith("+86") && text.length > 2) {
                                        edit_text_phone.setText("+86${text.substring(2, text.length)}")
                                    }
                                } else {
                                    if (text.length > 1) {
                                        edit_text_phone.setText("+8${text.substring(1, text.length)}")
                                    }
                                }
                            } else {
                                if (text.startsWith("86") || text.startsWith("8")) {
                                    edit_text_phone.setText("+$text")
                                } else {
                                    edit_text_phone.setText("+86$text")
                                }
                            }
                            edit_text_phone.setSelection(edit_text_phone.text!!.length)
                        }
                    })
                }
                TYPE_EMAIL -> {
                    text_input_layout.visibility = View.VISIBLE
                    edit_text_phone.visibility = View.GONE

                    edit_text.inputType = InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS
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