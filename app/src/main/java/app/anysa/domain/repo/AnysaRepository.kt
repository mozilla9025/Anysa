package app.anysa.domain.repo

import app.anysa.domain.pojo.BaseResponse
import app.anysa.domain.pojo.CurrentUser
import app.anysa.domain.pojo.response.CheckInfoChangesResponse
import io.reactivex.Single

interface AnysaRepository {
    fun checkInfoChanges(): Single<BaseResponse<CheckInfoChangesResponse>>
    fun getCurrentUser(): Single<CurrentUser>
}