package app.anysa.domain.pojo

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import app.anysa.domain.db.dao.ContactDao
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
@Entity(tableName = ContactDao.TABLE_NAME)
open class User(@PrimaryKey
                var id: Int = 0,
                var userName: String? = "",
                var statusDescription: String? = "",
                var avatarURL: String? = "",
                val lastModifiedDate: Long? = Date().time,
                val updateAvatarDate: Long? = Date().time) : Parcelable {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as User

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id
        result = 31 * result + (userName?.hashCode() ?: 0)
        result = 31 * result + (statusDescription?.hashCode() ?: 0)
        result = 31 * result + (avatarURL?.hashCode() ?: 0)
        return result
    }

    companion object {
        fun empty(): User {
            return User(EMPTY_USER_ID)
        }

        const val EMPTY_USER_ID = -1
    }
}