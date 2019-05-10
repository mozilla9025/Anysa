package app.anysa.di

import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, NetworkModule::class, ApiModule::class, ApiControllersModule::class])
interface ApplicationComponent {
}