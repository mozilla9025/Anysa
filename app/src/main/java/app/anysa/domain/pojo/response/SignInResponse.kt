package app.anysa.domain.pojo.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SignInResponse(@SerializedName("user_type") val userType: String,
                          @SerializedName("s_type") val sType: String,
                          @SerializedName("state") val state: String,
                          @SerializedName("session") val session: String,
                          @SerializedName("user_code") val userCode: String,
                          @SerializedName("password") val password: String,
                          @SerializedName("id") val id: String
) : Parcelable