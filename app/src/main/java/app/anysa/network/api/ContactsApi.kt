package app.anysa.network.api

import app.anysa.domain.pojo.BaseResponse
import app.anysa.domain.pojo.User
import app.anysa.domain.pojo.request.SignInRequest
import app.anysa.domain.pojo.response.SignInResponse
import app.anysa.domain.pojo.response.SignUpResponse
import io.reactivex.Observable
import io.reactivex.Single
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.*


interface ContactsApi {

    @POST("/user_info/")
    @FormUrlEncoded
    fun getUserByPhone(@Field("p") encryptedKey: String, @Field("c") encryptedBody: String):
            Single<BaseResponse<User>>
}