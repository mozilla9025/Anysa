package app.anysa.domain.pojo

import android.os.Parcelable
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.parcel.Parcelize

@Parcelize
class BaseResponse<T : Parcelable>(val code: Int, val rest: String) : Parcelable {

    fun isSuccessful() = code == 0

    inline fun <reified T> data(): T = Gson().fromJson<T>(rest.replace("\\\"", "\""), object: TypeToken<T>() {}.type)
}