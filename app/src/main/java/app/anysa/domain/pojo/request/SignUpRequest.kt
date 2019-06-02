package app.anysa.domain.pojo.request

import android.os.Parcelable
import androidx.databinding.BaseObservable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SignUpRequest(
        @SerializedName("telephone")
        val phone: String,
        @SerializedName("password")
        val password: String,
        @SerializedName("email")
        val email: String = "",
        @SerializedName("username")
        val name: String = "",
        @SerializedName("nickname")
        val nickname: String = "",
        @SerializedName("instruction")
        val bio: String = "",
        @SerializedName("usertype")
        val userType: Int = 0
) : Parcelable, BaseObservable()
