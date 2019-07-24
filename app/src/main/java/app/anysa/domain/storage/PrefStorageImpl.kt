package app.anysa.domain.storage

import android.annotation.SuppressLint
import android.content.SharedPreferences
import app.anysa.domain.pojo.response.SignInResponse
import com.google.gson.Gson
import io.reactivex.Completable

@SuppressLint("ApplySharedPref")
class PrefStorageImpl(
        private val pref: SharedPreferences,
        private val gson: Gson) : AuthStorage, UserStorage {

    companion object {
        const val STORAGE = "anysa-private-prefs"

        const val AUTH_INFO = "auth_info"
        const val AUTH_PHONE = "auth_phone"
    }

    @SuppressLint("CommitPrefEdits")
    private fun SharedPreferences.remove(vararg keys: String): SharedPreferences.Editor {
        val editor = this.edit()
        for (key in keys)
            editor.remove(key)

        return editor
    }

    override fun clear(): Completable {
        return Completable.fromAction {
            pref.edit().clear().commit()
        }
    }

    override fun saveAuthInfo(data: SignInResponse) {
        pref.edit().putString(AUTH_INFO, gson.toJson(data)).apply()
    }

    override fun saveAuthInfo(data: SignInResponse, phone: Long) {
        saveAuthInfo(data)
        pref.edit().putLong(AUTH_PHONE, phone).apply()
    }

    override fun getAuthInfo(): SignInResponse? {
        return if (pref.contains(AUTH_INFO))
            gson.fromJson(pref.getString(AUTH_INFO, ""), SignInResponse::class.java)
        else null
    }

    override fun getAuthPhone(): Long? {
        return pref.getLong(AUTH_PHONE, -1)
    }

    override fun hasAuthInfo(): Completable =
            if (pref.contains(AUTH_INFO))
                Completable.complete()
            else
                Completable.error(Throwable("Not logged in"))
}
