package app.anysa.di

import android.app.Application
import android.content.Context
import app.anysa.app.ApplicationLoader
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [
    DatabaseModule::class,
    NetworkModule::class,
    RepositoryModule::class,
    EventModule::class,
    UseCaseModule::class])
internal class AppModule {

    @Singleton
    @Provides
    fun provideGson(): Gson = GsonBuilder().create()


    @Singleton
    @Provides
    fun provideApplication(application: Application): ApplicationLoader = application as ApplicationLoader

    @Singleton
    @Provides
    fun provideApplicationContext(application: Application): Context = application.applicationContext
}