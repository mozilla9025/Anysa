package app.anysa.di

import android.content.Context
import app.anysa.domain.events.AppLifecycleObserver
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class EventModule {

    @Provides
    @Singleton
    fun provideAppLifecycleObserver(
            context: Context): AppLifecycleObserver
            = AppLifecycleObserver(context)

}