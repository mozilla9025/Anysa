package app.anysa.domain.repo.impl

import app.anysa.domain.pojo.request.LoginRequest
import app.anysa.domain.pojo.request.SignUpRequest
import app.anysa.domain.pojo.response.SignUpResponse
import app.anysa.domain.repo.AuthRepository
import app.anysa.domain.storage.AuthStorage
import app.anysa.network.EncryptedRequestBody
import app.anysa.network.api.AuthApi
import app.anysa.util.extensions.logd
import com.google.gson.JsonObject
import io.reactivex.Completable
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class AuthRepositoryImpl @Inject constructor(
        private val api: AuthApi,
        private val authStorage: AuthStorage)
    : AuthRepository {


    override fun saveServerPublicKey(key: String) = authStorage.serverPublicKey(key)
    override fun getServerPublicKey(): Single<String> = api.getPubKey()


    override fun login(loginRequest: LoginRequest): Completable {
//        return Observable.just(loginRequest.copy(password = if (loginRequest.password.length == 521) loginRequest.password else HashUtil.get_SHA_512_SecurePassword(loginRequest.password),
//                email = if (!loginRequest.email.isEmpty()) loginRequest.email else authStorage.email()))
//                .flatMap {
//                    authStorage.email(it.email)
//                    authStorage.password(it.password)
//                    return@flatMap api.login(it)
//                }
//                .flatMapCompletable {
//                    return@flatMapCompletable authStorage.token(it)
//                }
        return Completable.error(Throwable("not implemented"))
    }

    override fun saveEmail(email: String) = authStorage.email(email)
    override fun getEmail(): String = authStorage.email()

    override fun isLoggedIn(): Completable = authStorage.hasToken()

    override fun logoutCurrentUser(): Completable = authStorage.clear()


    override fun signUp(signUpRequest: SignUpRequest): Single<SignUpResponse> {

        return getServerPublicKey()
//                .subscribeOn(Schedulers.io())
                .flatMap {
                    api.register(EncryptedRequestBody(signUpRequest, it))
                }
    }
}
