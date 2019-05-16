package app.anysa.helper.preferences

import androidx.annotation.Nullable


object PreferencesManager {

    private val TAG = PreferencesManager

    private val TOKEN = "token"
    private val APPLICATION_LANGUAGE = "application_language"

    var token: String?
        get() = PreferencesHelper.getString(TOKEN)
        set(@Nullable token) = PreferencesHelper.set(TOKEN, token)

    var applicationLanguage: String?
        get() = PreferencesHelper.getString(APPLICATION_LANGUAGE)
        set(@Nullable token) = PreferencesHelper.set(APPLICATION_LANGUAGE, token)

    fun clearAccountData() {
        PreferencesHelper.clearKey(TOKEN)
    }
}