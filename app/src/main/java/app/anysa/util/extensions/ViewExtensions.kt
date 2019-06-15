package app.anysa.util.extensions

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager


fun View.openKeyboard() {
    val inputMethodManager = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.showSoftInput(this, 0)
}

fun View.closeKeyboard() {
    val inputMethodManager = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(this.windowToken, 0)
}