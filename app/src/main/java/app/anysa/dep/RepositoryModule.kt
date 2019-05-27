package app.wallpaper.di

import app.wallpaper.domain.repo.UserRepository
import app.wallpaper.domain.repo.impl.UserRepositoryImpl
import dagger.Binds
import dagger.Module

@Module
interface RepositoryModule {

    @Binds
    fun bindUser(repository: UserRepositoryImpl): UserRepository

}