package app.anysa.di

import app.anysa.network.api.AuthApi
import app.anysa.network.controllers.AuthApiController
import dagger.Module
import dagger.Provides

@Module
class ApiControllersModule {
    @Provides
    fun provideAuthController(api: AuthApi): AuthApiController = AuthApiController(api)
}