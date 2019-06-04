package app.anysa.domain.repo.impl

import app.anysa.crypto.*
import app.anysa.domain.pojo.request.SignInRequest
import app.anysa.domain.pojo.request.SignUpRequest
import app.anysa.domain.pojo.response.SignInResponse
import app.anysa.domain.pojo.response.SignUpResponse
import app.anysa.domain.repo.AuthRepository
import app.anysa.domain.storage.AuthStorage
import app.anysa.network.EncryptedRequestBody
import app.anysa.network.api.AuthApi
import app.anysa.util.extensions.logd
import io.reactivex.Completable
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


    override fun login(signInRequest: SignInRequest): Completable {
//        return Observable.just(signInRequest.copy(password = if (signInRequest.password.length == 521) signInRequest.password else HashUtil.get_SHA_512_SecurePassword(signInRequest.password),
//                email = if (!signInRequest.email.isEmpty()) signInRequest.email else authStorage.email()))
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
        val jsonBody = "{\"loginname\":\"13322221313\", \"password\":\"d8578edf8458ce06fbc5bb76a58c5ca4\", \"device\":\"mobile\"}"
        logd("EncryptedRequestBody  $jsonBody")

        val keyFromServer = "-----BEGIN PUBLIC KEY-----\n" +
                "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDVt2zuhHAMejV8syGsImaEwiME\n" +
                "0hpUpHBWBz0ZGwG11aHollAuOjUEMxpVe85mii5ErGWILgBJ6wFNA5cJrshhrpz7\n" +
                "EzoWVXR/FUZAvbQ0Y9GuxsXUNS7ZYKqyGmGwiMYdLSFVGltv6Gu5OoC8OVyWWgiF\n" +
                "sb054PmHf0p5P3JBHwIDAQAB\n" +
                "-----END PUBLIC KEY-----"

        val randomPrivateKey = CryptoUtils.getRandomString(8)

        val encryptKey = RsaEncryptor.encrypt(randomPrivateKey, keyFromServer)
        val encryptedBody = AESencryptor.encrypt(randomPrivateKey.md5(), jsonBody)

        EncryptedRequestBody(signUpRequest, encryptedBody!!)
        return api.register("application/x-www-form-urlencoded", encryptKey, encryptedBody!!)
//        return getServerPublicKey()
////                .subscribeOn(Schedulers.io())
//                .flatMap {
//                    api.register(EncryptedRequestBody(signUpRequest, it))
//                }
    }

    override fun signIn(signInRequest: SignInRequest): Single<SignInResponse> {
        return getServerPublicKey()
                .onErrorResumeNext {
                    logd("onerrorresumenext: $it")
                    Single.just("-----BEGIN PUBLIC KEY-----\n" +
                            "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDVt2zuhHAMejV8syGsImaEwiME\n" +
                            "0hpUpHBWBz0ZGwG11aHollAuOjUEMxpVe85mii5ErGWILgBJ6wFNA5cJrshhrpz7\n" +
                            "EzoWVXR/FUZAvbQ0Y9GuxsXUNS7ZYKqyGmGwiMYdLSFVGltv6Gu5OoC8OVyWWgiF\n" +
                            "sb054PmHf0p5P3JBHwIDAQAB\n" +
                            "-----END PUBLIC KEY-----")
                }
                .flatMap {
                    val encryptedRequestBody = EncryptedRequestBody(signInRequest, it)
                    api.login(encryptedRequestBody.encryptKey, encryptedRequestBody.encryptedBody)
                }

    }
}
