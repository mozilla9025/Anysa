package app.anysa.network.api

import app.anysa.domain.pojo.BaseResponse
import app.anysa.domain.pojo.response.CheckInfoChangesResponse
import app.anysa.domain.pojo.response.SignInResponse
import io.reactivex.Single
import okhttp3.ResponseBody
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST


interface AuthApi {
    @GET("http://47.88.231.69:80/MsgServer/publicKey/public.key")
    fun getPubKey(): Single<ResponseBody>

    @POST("/regist/")
    @FormUrlEncoded
    fun register(@Field("p") p: String, @Field("c") c: String): Single<BaseResponse<SignInResponse>>

    @POST("/login/")
    @FormUrlEncoded
    fun login(@Field("p") encryptedKey: String, @Field("c") encryptedBody: String):
            Single<BaseResponse<SignInResponse>>

    @POST("/checkinfo_change/")
    @FormUrlEncoded
    fun checkInfoChanges(@Field("p") encryptedKey: String, @Field("c") encryptedBody: String):
            Single<BaseResponse<CheckInfoChangesResponse>>
}