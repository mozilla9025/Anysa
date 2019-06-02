package app.anysa.di

import androidx.room.Room
import app.anysa.app.ApplicationLoader
import app.anysa.domain.db.AnysaDatabase
import app.anysa.domain.db.DBCleaner
import app.anysa.domain.db.dao.ContactDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(app: ApplicationLoader) = Room.databaseBuilder(app,
            AnysaDatabase::class.java,
            AnysaDatabase.DB_FILE_NAME)
            .fallbackToDestructiveMigration()
            .build()

    @Provides
    @Singleton
    fun providesDbCleaner(db: AnysaDatabase): DBCleaner {
        return object : DBCleaner {
            override fun clean() {
                return db.clearAllTables()
            }
        }
    }

    @Provides
    @Singleton
    fun provideContactDao(db: AnysaDatabase): ContactDao = db.contactDao()

}