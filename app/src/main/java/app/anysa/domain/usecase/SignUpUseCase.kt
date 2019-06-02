package app.anysa.domain.usecase

import app.anysa.domain.pojo.request.SignUpRequest
import app.anysa.domain.pojo.response.SignUpResponse
import io.reactivex.Single

interface SignUpUseCase {
    fun execute(signUpRequest: SignUpRequest) : Single<SignUpResponse>
}