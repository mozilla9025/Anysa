package app.anysa.domain.usecase.impl

import app.anysa.domain.pojo.BaseResponse
import app.anysa.domain.pojo.User
import app.anysa.domain.pojo.request.PhoneRequest
import app.anysa.domain.pojo.request.SignInRequest
import app.anysa.domain.pojo.request.SignUpRequest
import app.anysa.domain.pojo.response.SignUpResponse
import app.anysa.domain.repo.AuthRepository
import app.anysa.domain.repo.ContactsRepository
import app.anysa.domain.storage.AuthStorage
import app.anysa.domain.usecase.AuthUseCase
import app.anysa.domain.usecase.ContactsUseCase
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ContactsUseCaseImpl @Inject constructor(private val contactsRepository: ContactsRepository,
                                              private val authStorage: AuthStorage) : ContactsUseCase {
    override fun getContactByPhone(phone: String): Single<BaseResponse<User>> {
        return contactsRepository.getUserByPhone(PhoneRequest(phone.toLong()))
                .subscribeOn(Schedulers.io())
    }
}