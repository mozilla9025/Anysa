package app.anysa.domain.usecase.impl

import app.anysa.domain.pojo.request.SignUpRequest
import app.anysa.domain.pojo.response.SignUpResponse
import app.anysa.domain.repo.AuthRepository
import app.anysa.domain.usecase.SignUpUseCase
import app.anysa.network.EncryptedRequestBody
import app.anysa.network.api.AuthApi
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SignUpUseCaseImpl @Inject constructor(private val authRepository: AuthRepository,
                                            private val api: AuthApi) : SignUpUseCase {

    override fun execute(signUpRequest: SignUpRequest): Single<SignUpResponse> {
        return api.register(EncryptedRequestBody(signUpRequest, """MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDVt2zuhHAMejV8syGsImaEwiME0hpUpHBWBz0ZGwG11aHollAuOjUEMxpVe85mii5ErGWILgBJ6wFNA5cJrshhrpz7EzoWVXR/FUZAvbQ0Y9GuxsXUNS7ZYKqyGmGwiMYdLSFVGltv6Gu5OoC8OVyWWgiFsb054PmHf0p5P3JBHwIDAQAB"""))
//                }
                .subscribeOn(Schedulers.io())
//        api.getPubKey()
//                .flatMap {

//        return authRepository.signUp(signUpRequest)
//                .subscribeOn(Schedulers.io())
    }
}