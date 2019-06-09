package app.anysa.domain.pojo

import android.os.Parcelable
import app.anysa.util.extensions.fromJson
import app.anysa.util.extensions.genericType
import com.google.gson.Gson
import kotlinx.android.parcel.Parcelize


@Parcelize
class BaseResponse<T : Parcelable>(val code: Int, val rest: String, val data: T) : Parcelable {

    init {
        val gson = Gson()
        gson.fromJson<T::class.java>(rest.replace("\\", ""))
    }


    fun isSuccessful() = code == 0
}