package app.anysa.domain.usecase

import app.anysa.domain.pojo.request.SignInRequest
import app.anysa.domain.pojo.request.SignUpRequest
import app.anysa.domain.pojo.response.SignUpResponse
import io.reactivex.Completable
import io.reactivex.Single

interface AuthUseCase {
    fun logout() : Completable
    fun isLoggedIn() : Completable
    fun signUp(signUpRequest: SignUpRequest) : Completable
    fun signIn(signInRequest: SignInRequest) : Completable
}