package app.anysa.util.extensions

import android.content.Context
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

fun Fragment.getChildFragments(): List<Fragment>? {
    val navHostFragment = this.childFragmentManager.fragments[0]
    return navHostFragment?.childFragmentManager?.fragments
}

fun AppCompatActivity.getChildFragments(): List<Fragment>? {
    val navHostFragment = this.supportFragmentManager.fragments[0]
    return navHostFragment?.childFragmentManager?.fragments
}

fun Context.showToast(@StringRes textRes: Int) {
    Toast.makeText(this, textRes, Toast.LENGTH_SHORT).show()
}
fun Context.showToast(text: String) {
    Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
}

inline fun <reified T> Gson.fromJson(json: String) = this.fromJson<T>(json, object: TypeToken<T>() {}.type)