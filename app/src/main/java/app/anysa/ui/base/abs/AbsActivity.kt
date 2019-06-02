package app.anysa.ui.base.abs

import android.app.AlertDialog
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Toast
import androidx.annotation.DrawableRes
import androidx.annotation.Nullable
import androidx.annotation.StringRes
import androidx.core.view.ViewCompat
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import app.anysa.R
import app.anysa.util.annotation.AnnotationUtil

abstract class AbsActivity<B : ViewDataBinding, VM : AbsViewModel> : AbsDaggerActivity<VM>() {

    protected var binding: B? = null

    protected open fun getBindingVariable(): Int = -1

    protected open fun onBound(binding: B?) {}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setView()
        performDataBinding()

    }

    private fun setView() {
        val layoutId = AnnotationUtil.findViewId(this)
        if (layoutId != -1) {
            binding = DataBindingUtil.setContentView(this, layoutId)
            onBound(binding)
        }
    }

    fun <T> LiveData<T>.reObserve(owner: LifecycleOwner, observer: Observer<T>) {
        removeObserver(observer)
        observe(owner, observer)
    }

    private fun performDataBinding() {
        binding?.setVariable(getBindingVariable(), getViewModel())
        binding?.executePendingBindings()
    }
}
