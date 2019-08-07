package app.anysa.domain.pojo.request

import android.os.Parcelable
import androidx.databinding.BaseObservable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CheckInfoChangesRequest(
        @SerializedName("stamp")
        val stamp: String,
        @SerializedName("user_id")
        val userId: Long
) : Parcelable, BaseObservable()

