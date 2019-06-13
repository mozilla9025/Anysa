package app.anysa.domain.repo

import app.anysa.domain.pojo.BaseResponse
import app.anysa.domain.pojo.request.SignInRequest
import app.anysa.domain.pojo.request.SignUpRequest
import app.anysa.domain.pojo.response.SignInResponse
import app.anysa.domain.pojo.response.SignUpResponse
import io.reactivex.Completable
import io.reactivex.Single
import okhttp3.ResponseBody

interface AuthRepository {

    fun getServerPublicKey(): Single<ResponseBody>

    fun signIn(signInRequest: SignInRequest): Completable
    fun logoutCurrentUser(): Completable

    fun signUp(signUpRequest: SignUpRequest): Completable
}