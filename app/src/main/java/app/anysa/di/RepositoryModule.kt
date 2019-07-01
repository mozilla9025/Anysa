package app.anysa.di

import app.anysa.domain.repo.AuthRepository
import app.anysa.domain.repo.ContactsRepository
import app.anysa.domain.repo.impl.AuthRepositoryImpl
import app.anysa.domain.repo.impl.ContactsRepositoryImpl
import dagger.Binds
import dagger.Module

@Module(includes = [StorageModule::class])
interface RepositoryModule {

    @Binds
    fun bindAuthRepository(repository: AuthRepositoryImpl): AuthRepository

    @Binds
    fun bindContactsRepository(repository: ContactsRepositoryImpl): ContactsRepository

}