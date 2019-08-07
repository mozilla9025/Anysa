package app.anysa.domain.storage

import app.anysa.domain.pojo.CurrentUser
import app.anysa.domain.pojo.InfoChangesStamp
import io.reactivex.Single

interface UserStorage {

    fun getUser(): Single<CurrentUser>
    fun saveUser(user: CurrentUser): Single<CurrentUser>
    fun saveUserRaw(user: CurrentUser)
    fun getUserRaw(): CurrentUser?

    fun getInfoChangesStamp(): Single<InfoChangesStamp>
    fun saveInfoChangesStamp(infoChangesStamp: InfoChangesStamp): Single<InfoChangesStamp>
    fun saveInfoChangesStampRaw(infoChangesStamp: InfoChangesStamp)
    fun getInfoChangesStampRaw(): InfoChangesStamp



}