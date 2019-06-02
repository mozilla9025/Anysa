package app.anysa.ui.base.abs

import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import app.anysa.app.ApplicationLoader
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class AbsViewModel : ViewModel() {

    private val disposables = CompositeDisposable()

    open fun onAttached() {}

    override fun onCleared() {
        dispose()
        super.onCleared()
        Log.e("Clear view model %s", this.javaClass.simpleName)
    }

    fun add(disposable: Disposable) {
        disposables.add(disposable)
    }

    private fun dispose() {
        disposables.dispose()
    }

    fun clear() = disposables.clear()

}
