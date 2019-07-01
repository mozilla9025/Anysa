package app.anysa.domain.pojo.request

import android.os.Parcelable
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PhoneRequest (
        @SerializedName("telephone")
        var phone: Long): Parcelable, BaseObservable()