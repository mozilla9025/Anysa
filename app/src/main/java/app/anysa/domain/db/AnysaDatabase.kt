package app.anysa.domain.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import app.anysa.domain.db.converter.DateConverter
import app.anysa.domain.db.dao.ContactDao
import app.anysa.domain.pojo.User

@Database(entities = [User::class], version = 1, exportSchema = false)
@TypeConverters(DateConverter::class)
abstract class AnysaDatabase : RoomDatabase() {

    abstract fun contactDao(): ContactDao

    companion object {
        const val DB_FILE_NAME = "anysa.db"
    }
}