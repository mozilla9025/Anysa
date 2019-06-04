package app.anysa.domain.usecase

import app.anysa.domain.pojo.request.SignInRequest
import app.anysa.domain.pojo.request.SignUpRequest
import app.anysa.domain.pojo.response.SignInResponse
import app.anysa.domain.pojo.response.SignUpResponse
import io.reactivex.Single

interface AuthUseCase {
    fun signUp(signUpRequest: SignUpRequest) : Single<SignUpResponse>
    fun signIn(signInRequest: SignInRequest) : Single<SignInResponse>
}