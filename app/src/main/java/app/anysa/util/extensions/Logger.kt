package app.anysa.util.extensions

import android.util.Log

inline fun <reified T> T.logi(message: String) = Log.i(T::class.java.simpleName, message)

inline fun <reified T> T.logd(message: String) = Log.d(T::class.java.simpleName, message)

inline fun <reified T> T.loge(message: String, error: Throwable) = Log.e(T::class.java.simpleName, message, error)