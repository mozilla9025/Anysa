package app.anysa.helper.preferences

import androidx.annotation.Nullable


object PreferencesManager {

    private val TAG = PreferencesManager

    private val TOKEN = "token"

    var token: String?
        get() = PreferencesHelper.getString(TOKEN)
        set(@Nullable token) = PreferencesHelper.set(TOKEN, token)

    fun clearAccountData() {
        PreferencesHelper.clearKey(TOKEN)
    }
}