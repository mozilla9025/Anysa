package app.anysa.network.api

import app.anysa.domain.pojo.request.LoginRequest
import app.anysa.domain.pojo.response.SignUpResponse
import app.anysa.network.EncryptedRequestBody
import io.reactivex.Observable
import io.reactivex.Single
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST


interface AuthApi {
    @GET("http://47.88.231.69:80/MsgServer/publicKey/public.key")
    fun getPubKey(): Single<String>

    @POST("/regist/")
    fun register(@Body body: EncryptedRequestBody): Single<SignUpResponse>

    @POST("/login/")
    fun login(@Body body: LoginRequest): Observable<Response<ResponseBody>>
}