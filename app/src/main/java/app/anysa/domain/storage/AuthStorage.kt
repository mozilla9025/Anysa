package app.anysa.domain.storage

import app.anysa.domain.pojo.response.SignInResponse
import io.reactivex.Completable

interface AuthStorage {

    fun clear(): Completable

    fun saveAuthInfo(data: SignInResponse)
    fun getAuthInfo(): SignInResponse?
    fun hasAuthInfo(): Completable
}
