package app.anysa.domain.db.converter

import androidx.room.TypeConverter
import java.util.*

class DateConverter {

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time
    }

    @TypeConverter
    fun timestampToDate(timestamp: Long?): Date? {
        return timestamp?.let {
            Date(it)
        }
    }

}