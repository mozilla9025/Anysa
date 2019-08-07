package app.anysa.ui.modules.main.profilenav.profile.settings.password

import androidx.lifecycle.MutableLiveData
import app.anysa.crypto.md5
import app.anysa.domain.pojo.ApiResponse
import app.anysa.domain.usecase.CurrentUserUseCase
import app.anysa.ui.base.abs.AbsViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject

class ChangePasswordViewModel @Inject constructor(
        private val currentUserUseCase: CurrentUserUseCase) : AbsViewModel() {

    val updatePasswordResponse = MutableLiveData<ApiResponse<Any>>()

    fun updateUserInfo(password: String) {
        add(currentUserUseCase.changePassword(password.md5())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe {
                    updatePasswordResponse.value = ApiResponse.loading()
                }
                .subscribe({
                    if (it.rest == true.toString()) {
                        updatePasswordResponse.value = ApiResponse.success()
                    } else {
                        updatePasswordResponse.value = ApiResponse.error()
                    }
                }, {
                    updatePasswordResponse.value = ApiResponse.error(it)
                    it.printStackTrace()
                }))
    }
}
