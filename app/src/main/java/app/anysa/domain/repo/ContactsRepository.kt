package app.anysa.domain.repo

import app.anysa.domain.pojo.BaseResponse
import app.anysa.domain.pojo.User
import app.anysa.domain.pojo.request.PhoneRequest
import io.reactivex.Single

interface ContactsRepository {
    fun getUserByPhone(phone: PhoneRequest): Single<BaseResponse<User>>
    fun getCurrentUser(): Single<BaseResponse<User>>
    fun modifyCurrentUserInfo(name: String, description: String, email: String): Single<BaseResponse<User>>
    fun changePassword(passwordmd5: String): Single<BaseResponse<User>>
}