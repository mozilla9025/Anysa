package app.anysa.util.extensions

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment


fun Fragment.getChildFragments(): List<Fragment>? {
    val navHostFragment = this.childFragmentManager.fragments[0]
    return navHostFragment?.childFragmentManager?.fragments
}

fun AppCompatActivity.getChildFragments(): List<Fragment>? {
    val navHostFragment = this.supportFragmentManager.fragments[0]
    return navHostFragment?.childFragmentManager?.fragments
}