package app.anysa.domain.usecase

import app.anysa.domain.pojo.BaseResponse
import app.anysa.domain.pojo.User
import app.anysa.domain.pojo.request.SignInRequest
import app.anysa.domain.pojo.request.SignUpRequest
import app.anysa.domain.pojo.response.SignUpResponse
import io.reactivex.Completable
import io.reactivex.Single

interface ContactsUseCase {
    fun getContactByPhone(phone: String): Single<BaseResponse<User>>
}