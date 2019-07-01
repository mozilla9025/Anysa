package app.anysa.domain.repo.impl

import app.anysa.domain.pojo.BaseResponse
import app.anysa.domain.pojo.User
import app.anysa.domain.pojo.exception.InvalidAuthDataException
import app.anysa.domain.pojo.exception.PhoneAlreadyRegisteredException
import app.anysa.domain.pojo.request.PhoneRequest
import app.anysa.domain.pojo.request.SignInRequest
import app.anysa.domain.pojo.request.SignUpRequest
import app.anysa.domain.pojo.response.SignInResponse
import app.anysa.domain.repo.AuthRepository
import app.anysa.domain.repo.ContactsRepository
import app.anysa.domain.storage.AuthStorage
import app.anysa.network.EncryptedRequestBody
import app.anysa.network.api.AuthApi
import app.anysa.network.api.ContactsApi
import io.reactivex.Completable
import io.reactivex.Single
import okhttp3.ResponseBody
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
}
