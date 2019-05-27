package app.wallpaper.di

import app.wallpaper.domain.usecase.GetUnsplashCollectionsUseCase
import app.wallpaper.domain.usecase.impl.GetUnsplashCollectionsUseCaseImpl
import dagger.Binds
import dagger.Module

@Module
interface UseCaseModule {

    @Binds
    fun bindAuthUseCase(useCase: GetUnsplashCollectionsUseCaseImpl): GetUnsplashCollectionsUseCase

}