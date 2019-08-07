package app.anysa.domain.storage

import android.annotation.SuppressLint
import android.content.SharedPreferences
import app.anysa.domain.pojo.CurrentUser
import app.anysa.domain.pojo.InfoChangesStamp
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
        const val AUTH_PHONE = "auth_phone"
        const val KEY_CURRENT_USER = "current_user"
        const val KEY_INFO_CHANGES_STAMP = "info_changes_stamp"
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

    override fun saveUser(user: CurrentUser): Single<CurrentUser> {
        return Single.fromCallable {
            pref.edit().putString(KEY_CURRENT_USER, gson.toJson(user)).apply()
            return@fromCallable getUserRaw()
        }
    }

    override fun saveUserRaw(user: CurrentUser) {
        pref.edit().putString(KEY_CURRENT_USER, gson.toJson(user)).apply()
    }

    override fun getUser(): Single<CurrentUser> {
        return Single.just(getUserRaw())
    }

    override fun getUserRaw(): CurrentUser? {
        return if (pref.contains(KEY_CURRENT_USER))
            gson.fromJson(pref.getString(KEY_CURRENT_USER, ""), CurrentUser::class.java)
        else null
    }

    override fun getInfoChangesStamp(): Single<InfoChangesStamp> {
        return Single.just(getInfoChangesStampRaw())
    }

    override fun saveInfoChangesStamp(infoChangesStamp: InfoChangesStamp): Single<InfoChangesStamp> {
        return Single.fromCallable {
            pref.edit().putString(KEY_INFO_CHANGES_STAMP, gson.toJson(infoChangesStamp)).apply()
            return@fromCallable getInfoChangesStampRaw()
        }
    }

    override fun saveInfoChangesStampRaw(infoChangesStamp: InfoChangesStamp) {
        pref.edit().putString(KEY_INFO_CHANGES_STAMP, gson.toJson(infoChangesStamp)).apply()
    }

    override fun getInfoChangesStampRaw(): InfoChangesStamp {
        return if (pref.contains(KEY_INFO_CHANGES_STAMP))
            gson.fromJson(pref.getString(KEY_INFO_CHANGES_STAMP, ""), InfoChangesStamp::class.java)
        else InfoChangesStamp()
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
