package app.anysa.domain.pojo.request

import android.os.Parcelable
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SignInRequest (
        @SerializedName("loginname")
        var phone: String = "",
        @SerializedName("password")
        var password: String = "",
        @SerializedName("device")
        var device: String = "mobile"): Parcelable, BaseObservable()