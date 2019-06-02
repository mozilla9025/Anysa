package app.anysa.di

import android.content.Context
import android.content.SharedPreferences
import app.anysa.domain.storage.AuthStorage
import app.anysa.domain.storage.PrefStorageImpl
import app.anysa.domain.storage.UserStorage
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class StorageModule {

    @Singleton
    @Provides
    internal fun provideSharePrefInstance(context: Context): SharedPreferences {
        return context.getSharedPreferences(PrefStorageImpl.STORAGE, Context.MODE_PRIVATE)
    }

    @Singleton
    @Provides
    internal fun provideAuthStorage(sharedPreferences: SharedPreferences, gson: Gson): AuthStorage {
        return PrefStorageImpl(sharedPreferences, gson)
    }

    @Provides
    @Singleton
    internal fun provideUserStorage(sharedPreferences: SharedPreferences, gson: Gson): UserStorage {
        return PrefStorageImpl(sharedPreferences, gson)
    }
}
