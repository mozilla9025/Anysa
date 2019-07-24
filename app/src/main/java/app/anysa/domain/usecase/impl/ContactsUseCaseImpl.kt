package app.anysa.domain.usecase.impl

import app.anysa.domain.pojo.BaseResponse
import app.anysa.domain.pojo.User
import app.anysa.domain.pojo.request.PhoneRequest
import app.anysa.domain.repo.ContactsRepository
import app.anysa.domain.storage.AuthStorage
import app.anysa.domain.usecase.ContactsUseCase
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ContactsUseCaseImpl @Inject constructor(private val contactsRepository: ContactsRepository,
                                              private val authStorage: AuthStorage) : ContactsUseCase {
    override fun getContactByPhone(phone: String): Single<BaseResponse<User>> {
        return contactsRepository.getUserByPhone(PhoneRequest(phone))
                .subscribeOn(Schedulers.io())
    }

    override fun getCurrentUser(): Single<BaseResponse<User>> {
        return contactsRepository.getCurrentUser()
                .subscribeOn(Schedulers.io())
    }

    override fun modifyCurrentUserInfo(name: String, description: String, email: String): Single<BaseResponse<User>> {
        return contactsRepository.modifyCurrentUserInfo(name, description, email)
                .subscribeOn(Schedulers.io())
    }

    override fun changePassword(passwordmd5: String): Single<BaseResponse<User>> {
        return contactsRepository.changePassword(passwordmd5)
                .subscribeOn(Schedulers.io())
    }
}