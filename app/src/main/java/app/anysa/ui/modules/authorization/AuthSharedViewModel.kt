package app.anysa.ui.modules.authorization

import android.widget.Toast
import app.anysa.crypto.md5
import app.anysa.domain.pojo.request.SignInRequest
import app.anysa.domain.pojo.request.SignUpRequest
import app.anysa.domain.usecase.AuthUseCase
import app.anysa.ui.base.abs.AbsViewModel
import app.anysa.util.extensions.logd
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject

class AuthSharedViewModel @Inject constructor(private val signupUseCase: AuthUseCase) : AbsViewModel() {


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
                .subscribe({
                    logd("Success $it")
                }, { logd("Error") }))
    }

}