package app.anysa.domain.usecase

import app.anysa.domain.pojo.BaseResponse
import app.anysa.domain.pojo.User
import io.reactivex.Single

interface ContactsUseCase {
    fun getContactByPhone(phone: String): Single<BaseResponse<User>>
    fun getCurrentUser(): Single<BaseResponse<User>>
    fun modifyCurrentUserInfo(name: String, description: String, email: String): Single<BaseResponse<User>>
    fun changePassword(passwordmd5: String): Single<BaseResponse<User>>
}