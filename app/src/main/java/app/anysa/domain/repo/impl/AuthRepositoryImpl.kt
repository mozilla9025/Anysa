package app.anysa.domain.repo.impl

import app.anysa.crypto.*
import app.anysa.domain.pojo.exception.InvalidAuthDataException
import app.anysa.domain.pojo.request.SignInRequest
import app.anysa.domain.pojo.request.SignUpRequest
import app.anysa.domain.pojo.response.SignUpResponse
import app.anysa.domain.repo.AuthRepository
import app.anysa.domain.storage.AuthStorage
import app.anysa.network.EncryptedRequestBody
import app.anysa.network.api.AuthApi
import app.anysa.util.extensions.logd
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


//    override fun saveEmail(email: String) = authStorage.email(email)
//    override fun getEmail(): String = authStorage.email()
//
//    override fun isLoggedIn(): Completable = authStorage.hasToken()

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

    override fun signIn(signInRequest: SignInRequest): Completable {
        return getServerPublicKey()
                .flatMap {
                    val encryptedRequestBody = EncryptedRequestBody(signInRequest, it.string())
                    api.login(encryptedRequestBody.encryptKey, encryptedRequestBody.encryptedBody)
                }.flatMapCompletable {
                    logd("signIn: it")
                    if (it.isSuccessful()) {
                        authStorage.saveAuthInfo(it.data)
                        Completable.complete()
                    } else {
                        Completable.error(InvalidAuthDataException())
                    }
                }

    }
}
