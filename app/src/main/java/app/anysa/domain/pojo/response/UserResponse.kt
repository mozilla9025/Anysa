package app.anysa.domain.pojo.response

import android.os.Parcelable
import app.anysa.domain.pojo.User
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UserResponse (var data: User? = null,
                         val status: String): Parcelable