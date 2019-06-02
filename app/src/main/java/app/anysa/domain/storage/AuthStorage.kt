package app.anysa.domain.storage

import app.anysa.domain.pojo.Token
import io.reactivex.Completable
import io.reactivex.Single

interface AuthStorage {

    fun clear(): Completable

    fun token(token: Token): Completable {
        return if (token.isEmpty())
            Completable.complete()
        else token(token.token, System.currentTimeMillis() + token.tokenValidityInMillis)
    }

    fun serverPublicKey(key: String)
    fun serverPublicKey(): String?


    fun token(token: String, lifetime: Long): Completable
    fun token(): Single<String>
    fun hasToken(): Completable
    fun tokenLifetime(): Long

    fun justToken(): String
    fun simpleToken(): String

    fun email(email: String)
    fun email(): String
    fun password(password: String)
    fun password(): String
}
