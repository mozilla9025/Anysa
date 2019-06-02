package app.anysa.ui.modules.authorization.register

import app.anysa.crypto.md5
import app.anysa.domain.pojo.request.SignUpRequest
import app.anysa.domain.usecase.SignUpUseCase
import app.anysa.ui.base.abs.AbsViewModel
import app.anysa.util.extensions.logd
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject

class RegisterViewModel @Inject constructor(private val signupUseCase: SignUpUseCase) : AbsViewModel() {


    fun signUp(phone: String, password: String, email: String = "", name: String = "", bio: String = "") {
        add(signupUseCase.execute(SignUpRequest(phone, password.md5(), email, name, name, bio))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    logd("Success")
                }, { logd("Error") }))
    }

}