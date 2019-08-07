package app.anysa.ui.modules.main.profilenav.profile

import androidx.lifecycle.MutableLiveData
import app.anysa.domain.pojo.ApiResponse
import app.anysa.domain.pojo.CurrentUser
import app.anysa.domain.usecase.AuthUseCase
import app.anysa.domain.usecase.CurrentUserUseCase
import app.anysa.ui.base.abs.AbsViewModel
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject

class ProfileViewModel @Inject constructor(
        private val currentUserUseCase: CurrentUserUseCase,
        private val authUseCase: AuthUseCase) : AbsViewModel() {

    val currentUserResponse = MutableLiveData<ApiResponse<CurrentUser>>()
    val logoutData = MutableLiveData<ApiResponse<Completable>>()

    fun getCurrentUser() {
        add(currentUserUseCase.getCurrentUser()
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe {
                    currentUserResponse.value = ApiResponse.loading()
                }
                .subscribe({
                    currentUserResponse.value = ApiResponse.success(it)
                }, {
                    currentUserResponse.value = ApiResponse.error(it)
                    it.printStackTrace()
                }))
    }

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