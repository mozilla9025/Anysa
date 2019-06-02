package app.anysa.domain.storage

import app.anysa.domain.pojo.User
import io.reactivex.Single

interface UserStorage {

    fun getUser(): Single<User>
    fun saveUser(user: User): Single<User>
    fun getUserRaw(): User
}