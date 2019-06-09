package app.anysa.ui.modules.main

import androidx.lifecycle.MutableLiveData
import app.anysa.domain.pojo.ApiResponse
import app.anysa.domain.usecase.AuthUseCase
import app.anysa.ui.base.abs.AbsViewModel
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject

class MainViewModel @Inject constructor(
        private val authUseCase: AuthUseCase) : AbsViewModel() {

    val logoutData = MutableLiveData<ApiResponse<Completable>>()

    fun logout() {
        add(authUseCase.logout()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    logoutData.value = ApiResponse.success()
                }, {
                    logoutData.value = ApiResponse.error(it)
                }))
    }

}