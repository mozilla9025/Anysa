package app.anysa.ui.widget

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.inputmethod.EditorInfo
import androidx.constraintlayout.widget.ConstraintLayout
import app.anysa.R
import app.anysa.util.extensions.closeKeyboard
import io.reactivex.BackpressureStrategy
import io.reactivex.disposables.Disposable
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.view_search.view.*
import java.util.concurrent.TimeUnit

class SearchView @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val subject = PublishSubject.create<String>()

    var onTextChangedCallback: ((String) -> Unit)? = null
    var subscribe: Disposable
    val searchQuery: String
        get() = etSearch.text.toString()


    init {
        inflate(context, R.layout.view_search, this)

        val attributes = context.obtainStyledAttributes(attrs, R.styleable.SearchView)

        val hint = attributes.getString(R.styleable.SearchView_sv_hint)
        if (hint?.isNotEmpty()!!) {
            etSearch.hint = hint
        }

        attributes.recycle()

        subscribe = subject.debounce(300, TimeUnit.MILLISECONDS)
                .toFlowable(BackpressureStrategy.LATEST)
                .onBackpressureLatest()
                .subscribe {
                    onTextChangedCallback?.invoke(it)
                }

        setupCallbacks()
    }

    private fun setupCallbacks() {
//        etSearch.onFocusChangeListener = (OnFocusChangeListener { v, hasFocus ->
//            ibSearchCancel.visibility = if (hasFocus) View.VISIBLE else View.GONE
//        })

        etSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                subject.onNext(s.toString())
            }
        })

        etSearch.setOnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                onTextChangedCallback?.invoke(etSearch.text.toString())
                closeKeyboardAndClearFocus()
                true
            }
            false
        }

//        ibSearchCancel.setOnClickListener {
//            etSearch.setText("")
//            closeKeyboardAndClearFocus()
//        }
    }

    fun closeKeyboardAndClearFocus() {
        closeKeyboard()
        etSearch.clearFocus()
        requestFocus()
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        subscribe.dispose()
    }
}