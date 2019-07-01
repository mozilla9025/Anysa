package app.anysa.domain.repo

import app.anysa.domain.pojo.BaseResponse
import app.anysa.domain.pojo.User
import app.anysa.domain.pojo.request.PhoneRequest
import app.anysa.domain.pojo.request.SignInRequest
import app.anysa.domain.pojo.request.SignUpRequest
import app.anysa.domain.pojo.response.SignInResponse
import app.anysa.domain.pojo.response.SignUpResponse
import io.reactivex.Completable
import io.reactivex.Single
import okhttp3.ResponseBody

interface ContactsRepository {
    fun getUserByPhone(phone: PhoneRequest): Single<BaseResponse<User>>
}