package app.anysa.di

import app.anysa.domain.usecase.AuthUseCase
import app.anysa.domain.usecase.ContactsUseCase
import app.anysa.domain.usecase.impl.AuthUseCaseImpl
import app.anysa.domain.usecase.impl.ContactsUseCaseImpl
import dagger.Binds
import dagger.Module

@Module
interface UseCaseModule {

    @Binds
    fun bindAuthUseCase(useCase: AuthUseCaseImpl): AuthUseCase

    @Binds
    fun bindContactsUseCase(useCase: ContactsUseCaseImpl): ContactsUseCase

}