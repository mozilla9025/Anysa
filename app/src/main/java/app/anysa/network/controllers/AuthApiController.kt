package app.anysa.network.controllers

import app.anysa.network.EncryptedRequestBody
import app.anysa.network.api.AuthApi
import io.reactivex.Observable
import okhttp3.ResponseBody
import retrofit2.Response

class AuthApiController(private val api: AuthApi) {

    fun getPubKey() = api.getPubKey()

//    fun register() = api.register(EncryptedRequestBody())
//
//    fun login(@Body body: EncryptedRequestBody): Observable<Response<ResponseBody>>
}