package app.wallpaper.di

import app.anysa.network.api.AuthApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class ApiModule {

    @Provides
    fun providePhotosApi(retrofit: Retrofit): AuthApi {
        return retrofit.create(AuthApi::class.java)
    }

}