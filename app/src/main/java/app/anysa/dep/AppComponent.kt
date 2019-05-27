package app.wallpaper.di

import android.app.Application
import app.anysa.app.ApplicationLoader
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidInjectionModule::class,
    AppModule::class,
    ActivityModule::class,
    ViewModelModule::class])
interface AppComponent : AndroidInjector<ApplicationLoader> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    override fun inject(application: ApplicationLoader)
}