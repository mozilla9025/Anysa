package app.anysa.domain.repo.impl

import app.anysa.domain.pojo.exception.InvalidAuthDataException
import app.anysa.domain.pojo.exception.PhoneAlreadyRegisteredException
import app.anysa.domain.pojo.request.SignInRequest
import app.anysa.domain.pojo.request.SignUpRequest
import app.anysa.domain.pojo.response.SignInResponse
import app.anysa.domain.repo.AuthRepository
import app.anysa.domain.storage.AuthStorage
import app.anysa.network.EncryptedRequestBody
import app.anysa.network.api.AuthApi
import io.reactivex.Completable
import io.reactivex.Single
import okhttp3.ResponseBody
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class AuthRepositoryImpl @Inject constructor(
        private val api: AuthApi,
        private val authStorage: AuthStorage)
    : AuthRepository {


    override fun getServerPublicKey(): Single<ResponseBody> = api.getPubKey()

    override fun logoutCurrentUser(): Completable = authStorage.clear()

    override fun signUp(signUpRequest: SignUpRequest): Completable {
        return getServerPublicKey()
                .flatMap {
                    val encryptedRequestBody = EncryptedRequestBody(signUpRequest, it.string())
                    api.register(encryptedRequestBody.encryptKey, encryptedRequestBody.encryptedBody)
                }.flatMapCompletable {
                    if (it.isSuccessful()) {
                        signIn(SignInRequest(signUpRequest.phone, signUpRequest.password))
                    } else {
                        if (it.code == 5) {
                            Completable.error(PhoneAlreadyRegisteredException())
                        } else {
                            Completable.error(InvalidAuthDataException())
                        }
                    }
                }
    }

    override fun signIn(signInRequest: SignInRequest): Completable {
        return getServerPublicKey()
                .flatMap {
                    val encryptedRequestBody = EncryptedRequestBody(signInRequest, it.string())
                    api.login(encryptedRequestBody.encryptKey, encryptedRequestBody.encryptedBody)
                }.flatMapCompletable {
                    if (it.isSuccessful()) {
                        it.data<SignInResponse>()?.let { it1 -> authStorage.saveAuthInfo(it1) }
                        Completable.complete()
                    } else {
                        Completable.error(InvalidAuthDataException())
                    }
                }

    }
}
