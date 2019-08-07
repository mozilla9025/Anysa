package app.anysa.domain.pojo

import android.os.Parcelable
import androidx.databinding.BaseObservable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class InfoChangesStamp(
        @SerializedName("myinfo") val myinfo: Int = -1,
        @SerializedName("gflist") val gflist: Int = -1,
        @SerializedName("total") val total: Int = -1,
        @SerializedName("glist") val glist: Int = -1,
        @SerializedName("flist") val flist: Int = -1,
        @SerializedName("ginfo") val ginfo: Int = -1,
        @SerializedName("finfo") val finfo: Int = -1,
        @SerializedName("gfinfo") val gfinfo: Int = -1
) : Parcelable, BaseObservable()