package app.anysa.ui.modules.authorization

import androidx.lifecycle.MutableLiveData
import app.anysa.crypto.md5
import app.anysa.domain.pojo.ApiResponse
import app.anysa.domain.pojo.request.SignInRequest
import app.anysa.domain.pojo.request.SignUpRequest
import app.anysa.domain.usecase.AuthUseCase
import app.anysa.ui.base.abs.AbsViewModel
import app.anysa.util.extensions.logd
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject

class AuthSharedViewModel @Inject constructor(private val signupUseCase: AuthUseCase) : AbsViewModel() {

    val signInData = MutableLiveData<ApiResponse<Any>>()

    fun signUp(phone: String, password: String, email: String = "", name: String = "", bio: String = "") {
        add(signupUseCase.signUp(SignUpRequest(phone, password.md5(), email, name, name, bio))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    logd("Success")
                }, { logd("Error") }))
    }

    fun signIn(phone: String, password: String) {
        add(signupUseCase.signIn(SignInRequest(phone, password.md5()))
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe {
                    signInData.value = ApiResponse.loading()
                }
                .subscribe({
                    signInData.value = ApiResponse.success()
                }, {
                    signInData.value = ApiResponse.error(it)
                }))
    }

}