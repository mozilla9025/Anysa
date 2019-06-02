package app.anysa.ui.base.abs

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import app.anysa.di.ViewModelProviderFactory
import app.anysa.util.annotation.AnnotationUtil
import dagger.android.support.AndroidSupportInjection
import dagger.android.support.DaggerFragment
import javax.inject.Inject

abstract class AbsDaggerFragment<VM: AbsViewModel> : DaggerFragment() {

    @field:Inject
    lateinit var vmFactory: ViewModelProviderFactory

    private var baseViewModel: VM? = null

    override fun onAttach(context: Context) {
        inject()
        super.onAttach(context)
    }

    private fun inject() {
        if (AnnotationUtil.hasViewModel(this)) {
            AndroidSupportInjection.inject(this)
            initViewModel()
        } else if (AnnotationUtil.hasInject(this)) {
            AndroidSupportInjection.inject(this)
        }
    }

    private fun initViewModel() {
        val vmClass: Class<out AbsViewModel> = AnnotationUtil.findViewModelClass(this)
        baseViewModel = ViewModelProviders.of(this, vmFactory).get(vmClass) as VM
        baseViewModel?.onAttached()
    }

    fun getViewModel(): VM? {
        if (baseViewModel == null)
            throw IllegalStateException("VM not found")
        return baseViewModel
    }

}