package app.anysa.domain.repo

import app.anysa.domain.pojo.request.SignInRequest
import app.anysa.domain.pojo.request.SignUpRequest
import app.anysa.domain.pojo.response.SignInResponse
import app.anysa.domain.pojo.response.SignUpResponse
import io.reactivex.Completable
import io.reactivex.Single

interface AuthRepository {

    fun saveServerPublicKey(key: String)
    fun getServerPublicKey(): Single<String>

    fun login(signInRequest: SignInRequest): Completable
    fun isLoggedIn(): Completable
    fun logoutCurrentUser(): Completable

    fun saveEmail(email: String)
    fun getEmail(): String

    fun signUp(signUpRequest: SignUpRequest): Single<SignUpResponse>
    fun signIn(signInRequest: SignInRequest): Single<SignInResponse>
}