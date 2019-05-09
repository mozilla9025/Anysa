package app.anysa.ui.base

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import app.anysa.R
import app.anysa.util.extensions.getChildFragments

abstract class BaseNavFragment : BaseFragment() {

    abstract val navHostFragmentId: Int


    override fun onBackPressed(): Boolean {
        val fragments = getChildFragments() ?: return super.onBackPressed()

        var result = false

        fragments.forEach { childFragment ->
            val fragment = childFragment as BaseFragment
            result = fragment.onBackPressed()
        }

        if (result)
            return true

        val navHostFragment = childFragmentManager.findFragmentById(navHostFragmentId)
        if (navHostFragment != null && navHostFragment.view != null) {
            val navController = Navigation.findNavController(navHostFragment.view!!)
            val popped = navController.popBackStack()
        }

        return false
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val fragments = getChildFragments() ?: return
        for (childFragment in fragments) {
            childFragment.onActivityResult(requestCode, resultCode, data)
        }
    }
}