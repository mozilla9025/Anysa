package app.anysa.domain.storage

import android.annotation.SuppressLint
import android.content.SharedPreferences
import app.anysa.domain.pojo.User
import app.anysa.domain.pojo.response.SignInResponse
import com.google.gson.Gson
import io.reactivex.Completable
import io.reactivex.Single

@SuppressLint("ApplySharedPref")
class PrefStorageImpl(
        private val pref: SharedPreferences,
        private val gson: Gson) : AuthStorage, UserStorage {

    companion object {
        const val STORAGE = "anysa-private-prefs"

        const val AUTH_INFO = "auth_info"
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

    override fun getAuthInfo(): SignInResponse? {
        return if (pref.contains(AUTH_INFO))
            gson.fromJson(pref.getString(AUTH_INFO, ""), SignInResponse::class.java)
        else null
    }
}
