package app.anysa.network.api

import app.anysa.network.EncryptedRequestBody
import io.reactivex.Observable
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST


interface AuthApi {
    @GET("/MsgServer/publicKey/public.key")
    fun getPubKey(): Observable<Response<ResponseBody>>

    @POST("/regist/")
    fun register(@Body body: EncryptedRequestBody): Observable<Response<ResponseBody>>

    @POST("/login/")
    fun login(@Body body: EncryptedRequestBody): Observable<Response<ResponseBody>>
}