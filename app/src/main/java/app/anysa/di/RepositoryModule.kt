package app.anysa.di

import app.anysa.domain.repo.AuthRepository
import app.anysa.domain.repo.impl.AuthRepositoryImpl
import dagger.Binds
import dagger.Module

@Module(includes = [StorageModule::class])
interface RepositoryModule {

    @Binds
    fun bindUser(repository: AuthRepositoryImpl): AuthRepository

}