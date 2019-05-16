package app.anysa.helper.preferences

import android.text.TextUtils
import androidx.annotation.Nullable
import app.anysa.helper.locale.ApplicationLanguage


object PreferencesManager {

    private val TAG = PreferencesManager

    private val TOKEN = "token"
    private val APPLICATION_LANGUAGE = "application_language"

    var token: String?
        get() = PreferencesHelper.getString(TOKEN)
        set(@Nullable token) = PreferencesHelper.set(TOKEN, token)

    var applicationLanguage: String
        get() {
            val string = PreferencesHelper.getString(APPLICATION_LANGUAGE)
            if (string != null && !TextUtils.isEmpty(string))
                return string
            else return ApplicationLanguage.defaultLanguage.languageCode
        }
        set(@Nullable language) = PreferencesHelper.set(APPLICATION_LANGUAGE, language)

    fun clearAccountData() {
        PreferencesHelper.clearKey(TOKEN)
    }
}