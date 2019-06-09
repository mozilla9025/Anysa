package app.anysa.domain.usecase.impl

import app.anysa.domain.pojo.request.SignInRequest
import app.anysa.domain.pojo.request.SignUpRequest
import app.anysa.domain.pojo.response.SignUpResponse
import app.anysa.domain.repo.AuthRepository
import app.anysa.domain.storage.AuthStorage
import app.anysa.domain.usecase.AuthUseCase
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthUseCaseImpl @Inject constructor(private val authRepository: AuthRepository,
                                          private val authStorage: AuthStorage) : AuthUseCase {


    override fun logout(): Completable {
        return authStorage.clear()
    }

    override fun isLoggedIn(): Completable {
        return authStorage.hasAuthInfo()
    }

    override fun signIn(signInRequest: SignInRequest): Completable {
        return authRepository.signIn(signInRequest)
                .subscribeOn(Schedulers.io())
    }

    override fun signUp(signUpRequest: SignUpRequest): Single<SignUpResponse> {
        return authRepository.signUp(signUpRequest)
                .subscribeOn(Schedulers.io())
    }
}