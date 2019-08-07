package app.anysa.domain.pojo

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import app.anysa.domain.db.dao.ContactDao
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = ContactDao.TABLE_NAME)
open class User(@PrimaryKey
                @SerializedName("user_id")
                var id: Int = 0,
                @SerializedName("nick_name")
                var username: String? = "",
                @SerializedName("instruction")
                var description: String? = "",
                @SerializedName("telephone")
                var phone: String? = null,
                @SerializedName("email")
                val email: String? = null,
                @SerializedName("head_image")
                var avatarUrl: String? = ""
) : Parcelable {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as User

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id
    }
}