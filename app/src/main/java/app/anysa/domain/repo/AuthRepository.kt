package app.anysa.domain.repo

import app.anysa.domain.pojo.request.LoginRequest
import app.anysa.domain.pojo.request.SignUpRequest
import app.anysa.domain.pojo.response.SignUpResponse
import io.reactivex.Completable
import io.reactivex.Single

interface AuthRepository {

    fun saveServerPublicKey(key: String)
    fun getServerPublicKey(): Single<String>

    fun login(loginRequest: LoginRequest): Completable
    fun isLoggedIn(): Completable
    fun logoutCurrentUser(): Completable

    fun saveEmail(email: String)
    fun getEmail(): String

    fun signUp(signUpRequest: SignUpRequest): Single<SignUpResponse>

}