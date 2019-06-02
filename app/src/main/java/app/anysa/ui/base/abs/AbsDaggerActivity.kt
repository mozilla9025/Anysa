package app.anysa.ui.base.abs

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import app.anysa.util.annotation.AnnotationUtil
import dagger.android.AndroidInjection
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject


/**
 * Represents activity that injects viewModel factory
 *
 * @param <VM>
</VM> */
abstract class AbsDaggerActivity<VM : AbsViewModel> : DaggerAppCompatActivity() {


    private var absViewModel: VM? = null

    @set:Inject
    var vmFactory: ViewModelProvider.Factory? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        inject()
        super.onCreate(savedInstanceState)
    }

    fun getViewModel(): VM? {
        if (absViewModel == null)
            return absViewModel
        //throw IllegalStateException("ViewModel not found")
        return absViewModel as VM
    }


    private fun inject() {
        if (AnnotationUtil.hasViewModel(this)) {
            AndroidInjection.inject(this)
            setViewModel()
        } else if (AnnotationUtil.hasInject(this)) {
            AndroidInjection.inject(this)
        }
    }

    private fun setViewModel() {
        val viewModelClass = AnnotationUtil.findViewModelClass(this)

        absViewModel = ViewModelProviders.of(this, vmFactory).get(viewModelClass) as VM
        absViewModel?.onAttached()
    }
}
