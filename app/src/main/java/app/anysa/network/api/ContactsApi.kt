package app.anysa.network.api

import app.anysa.domain.pojo.BaseResponse
import app.anysa.domain.pojo.User
import io.reactivex.Single
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST


interface ContactsApi {

    @POST("/user_info/")
    @FormUrlEncoded
    fun getUserByPhone(@Field("p") encryptedKey: String, @Field("c") encryptedBody: String):
            Single<BaseResponse<User>>


    @POST("/modify_user_info/")
    @FormUrlEncoded
    fun modifyCurrentUser(@Field("p") encryptedKey: String, @Field("c") encryptedBody: String):
            Single<BaseResponse<User>>

}