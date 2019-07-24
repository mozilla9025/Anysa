package app.anysa.domain.repo.impl

import app.anysa.domain.pojo.BaseResponse
import app.anysa.domain.pojo.User
import app.anysa.domain.pojo.request.ModifyUserRequest
import app.anysa.domain.pojo.request.PhoneRequest
import app.anysa.domain.repo.ContactsRepository
import app.anysa.domain.storage.AuthStorage
import app.anysa.network.EncryptedRequestBody
import app.anysa.network.api.ContactsApi
import app.anysa.util.extensions.logd
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ContactsRepositoryImpl @Inject constructor(
        private val api: ContactsApi,
        private val authStorage: AuthStorage)
    : ContactsRepository {

    override fun getUserByPhone(phone: PhoneRequest): Single<BaseResponse<User>> {
        val authInfo = authStorage.getAuthInfo()
        val encryptedRequestBody = EncryptedRequestBody(phone, method = EncryptedRequestBody.Method.ENCRYPT_MAIN,
                session = authInfo?.session!!, password = authInfo.password)
        return api.getUserByPhone(encryptedRequestBody.encryptKey, encryptedRequestBody.encryptedBody)
    }

    override fun getCurrentUser(): Single<BaseResponse<User>> {
        val phone = authStorage.getAuthPhone().toString()
        val authInfo = authStorage.getAuthInfo()

        val encryptedRequestBody = EncryptedRequestBody(PhoneRequest(phone!!), method = EncryptedRequestBody.Method.ENCRYPT_MAIN,
                session = authInfo?.session!!, password = authInfo.password)
        return api.getUserByPhone(encryptedRequestBody.encryptKey, encryptedRequestBody.encryptedBody)
    }

    override fun modifyCurrentUserInfo(name: String, description: String, email: String): Single<BaseResponse<User>> {
        val authInfo = authStorage.getAuthInfo()
        var currentUserId = -1L
        runCatching {
            currentUserId = authInfo?.id?.toLong()!!
        }

        val modifyUserRequest = ModifyUserRequest(currentUserId, password = "", telephone = "",
                head_image = "", name = name, username = name, email = email, description = description)

        val encryptedRequestBody = EncryptedRequestBody(modifyUserRequest, method = EncryptedRequestBody.Method.ENCRYPT_MAIN,
                session = authInfo?.session!!, password = authInfo.password)
        return api.modifyCurrentUser(encryptedRequestBody.encryptKey, encryptedRequestBody.encryptedBody)
    }

    override fun changePassword(passwordmd5: String): Single<BaseResponse<User>> {
        val authInfo = authStorage.getAuthInfo()
        var currentUserId = -1L
        runCatching {
            currentUserId = authInfo?.id?.toLong()!!
        }
        logd("asdfasfsadfsdf ${authInfo?.password}")

        val modifyUserRequest = ModifyUserRequest(currentUserId, password = passwordmd5, telephone = "",
                head_image = "", name = "", username = "", email = "", description = "")

        val encryptedRequestBody = EncryptedRequestBody(modifyUserRequest, method = EncryptedRequestBody.Method.ENCRYPT_MAIN,
                session = authInfo?.session!!, password = authInfo.password)
        return api.modifyCurrentUser(encryptedRequestBody.encryptKey, encryptedRequestBody.encryptedBody)
                .flatMap {
                    if (it.rest == true.toString()) {
                        val authInfo1 = authStorage.getAuthInfo()
                        authInfo1?.let { it1 -> //todo
                            it1.password = passwordmd5
                            authStorage.saveAuthInfo(it1)
                        }
                    }
                    Single.just(it)
                }
    }
}
