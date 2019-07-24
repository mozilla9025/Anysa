package app.anysa.domain.pojo.request

import android.os.Parcelable
import androidx.databinding.BaseObservable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ModifyUserRequest(
        @SerializedName("user_id")
        var currentUserId: Long,
        @SerializedName("password")
        var password: String = "",
        @SerializedName("telephone")
        var telephone: String = "",
        @SerializedName("head_image")
        var head_image: String = "",
        @SerializedName("user_type")
        var user_type: Int = 1,
        @SerializedName("nickname")
        var name: String? = "",
        @SerializedName("username")
        val username: String = "",
        @SerializedName("email")
        var email: String? = "",
        @SerializedName("instruction")
        var description: String? = "") : Parcelable, BaseObservable()