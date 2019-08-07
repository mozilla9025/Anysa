package app.anysa.domain.usecase.impl

import app.anysa.domain.pojo.BaseResponse
import app.anysa.domain.pojo.response.CheckInfoChangesResponse
import app.anysa.domain.repo.AnysaRepository
import app.anysa.domain.storage.AuthStorage
import app.anysa.domain.usecase.CheckInfoChangesUseCase
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CheckInfoChangesUseCaseImpl @Inject constructor(private val anysaRepository: AnysaRepository,
                                                      private val authStorage: AuthStorage) : CheckInfoChangesUseCase {

    override fun checkInfoChanges(): Single<BaseResponse<CheckInfoChangesResponse>> {
        return anysaRepository.checkInfoChanges()
                .subscribeOn(Schedulers.io())
    }
}