package app.anysa.domain.usecase

import app.anysa.domain.pojo.BaseResponse
import app.anysa.domain.pojo.response.CheckInfoChangesResponse
import io.reactivex.Single

interface CheckInfoChangesUseCase {
    fun checkInfoChanges(): Single<BaseResponse<CheckInfoChangesResponse>>
}