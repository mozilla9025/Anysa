package app.anysa.domain.storage

import android.annotation.SuppressLint
import android.content.SharedPreferences
import app.anysa.domain.pojo.User
import com.google.gson.Gson
import io.reactivex.Completable
import io.reactivex.Single

@SuppressLint("ApplySharedPref")
class PrefStorageImpl(
        private val pref: SharedPreferences,
        private val gson: Gson) : AuthStorage, UserStorage {

    companion object {
        const val STORAGE = "anysa-private-prefs"

        const val SERVER_PUBLIC_KEY = "server_public_key"
        const val TOKEN = "token"
        const val TOKEN_LIFETIME = "token_lifetime"
        const val EMAIL = "email"
        const val PASSWORD = "password"
        const val KEY_USER = "key_user"
    }

    @SuppressLint("CommitPrefEdits")
    private fun SharedPreferences.remove(vararg keys: String): SharedPreferences.Editor {
        val editor = this.edit()
        for (key in keys)
            editor.remove(key)

        return editor
    }

    override fun clear(): Completable {
        return Completable
                .fromAction {
                    pref.remove(TOKEN, KEY_USER).commit()
                }
                .andThen(Completable.fromAction {
                    pref.edit().clear().commit()
                })
    }

    override fun serverPublicKey(): String? = pref.getString(EMAIL, null)

    override fun serverPublicKey(key: String) {
        pref.edit().putString(SERVER_PUBLIC_KEY, key).apply()
    }



    override fun token(token: String, lifetime: Long): Completable {
        return Completable.fromAction {
            pref.edit().apply {

                putString(TOKEN, token)
                putLong(TOKEN_LIFETIME, lifetime)
                apply()
            }
        }
    }


    override fun token(): Single<String> = Single.just(pref.getString(TOKEN, ""))

    override fun justToken(): String = pref.getString(TOKEN, "")

    override fun hasToken(): Completable =
            if (pref.contains(TOKEN) && !pref.getString(TOKEN, "").isNullOrEmpty())
                Completable.complete()
            else
                Completable.error(Throwable("No token"))

    override fun simpleToken(): String = pref.getString(TOKEN, "")


    override fun email(email: String) {
        pref.edit().putString(EMAIL, email).apply()
    }

    override fun email(): String = pref.getString(EMAIL, "")

    override fun password(password: String) {
        pref.edit().putString(PASSWORD, password).apply()
    }

    override fun password(): String = pref.getString(PASSWORD, "")

    override fun tokenLifetime(): Long = pref.getLong(TOKEN_LIFETIME, 0)


    override fun saveUser(user: User): Single<User> {
        return Single.fromCallable {
            pref.edit().putString(KEY_USER, gson.toJson(user)).apply()
            return@fromCallable getUserRaw()
        }
    }

    override fun getUser(): Single<User> {
        return Single.just(getUserRaw())
    }

    override fun getUserRaw(): User {
        return if (pref.contains(KEY_USER))
            gson.fromJson(pref.getString(KEY_USER, ""), User::class.java)
        else User.empty()
    }

}
