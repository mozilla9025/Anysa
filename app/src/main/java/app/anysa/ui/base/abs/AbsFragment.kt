package app.anysa.ui.base.abs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import app.anysa.util.annotation.AnnotationUtil


abstract class AbsFragment<VM : AbsViewModel, B : ViewDataBinding> : AbsDaggerFragment<VM>() {

    protected var binding: B? = null

    protected open fun onBound(binding: B?) {}

    protected open fun getBindingVariable(): Int = -1

    fun <T> LiveData<T>.reObserve(owner: LifecycleOwner, observer: Observer<T>) {
        removeObserver(observer)
        observe(owner, observer)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val layoutId = AnnotationUtil.findViewId(this)
        return if (layoutId != -1) {
            binding = DataBindingUtil.inflate(inflater, layoutId, container, false)
            binding?.root
        } else {
            super.onCreateView(inflater, container, savedInstanceState)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        performDataBinding()
        onBound(binding)
    }

    private fun performDataBinding() {
        if (getBindingVariable() != -1) {
            binding?.setVariable(getBindingVariable(), getViewModel())
            binding?.executePendingBindings()
        }
    }
}