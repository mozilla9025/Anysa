package app.anysa.network.api

import app.anysa.domain.pojo.BaseResponse
import app.anysa.domain.pojo.request.SignInRequest
import app.anysa.domain.pojo.response.SignInResponse
import app.anysa.domain.pojo.response.SignUpResponse
import io.reactivex.Observable
import io.reactivex.Single
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.*


interface AuthApi {
    @GET("http://47.88.231.69:80/MsgServer/publicKey/public.key")
    fun getPubKey(): Single<ResponseBody>

    @POST("/login/")
    @FormUrlEncoded
    fun register(@Field("p") p: String, @Field("c") c: String): Single<SignUpResponse>

    @POST("/login/")
    @FormUrlEncoded
    fun register(@Header("Content-Type") type: String, @Field("p") p: String, @Field("c") c: String): Single<SignUpResponse>

    @POST("/login/")
    @FormUrlEncoded
    fun login(@Field("p") encryptedKey: String, @Field("c") encryptedBody: String):
            Single<BaseResponse<SignInResponse>>
}