package app.anysa.domain.pojo.request

import android.os.Parcelable
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class LoginRequest (
        var email: String = "",
        var password: String = ""): Parcelable, BaseObservable()