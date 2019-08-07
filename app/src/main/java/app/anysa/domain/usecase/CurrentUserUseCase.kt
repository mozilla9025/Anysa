package app.anysa.domain.usecase

import app.anysa.domain.pojo.BaseResponse
import app.anysa.domain.pojo.CurrentUser
import app.anysa.domain.pojo.User
import io.reactivex.Single

interface CurrentUserUseCase {
    fun getContactByPhone(phone: String): Single<BaseResponse<User>>
    fun getCurrentUser(): Single<CurrentUser>
    fun modifyCurrentUserInfo(name: String, description: String, email: String): Single<BaseResponse<User>>
    fun changePassword(passwordmd5: String): Single<BaseResponse<User>>
}