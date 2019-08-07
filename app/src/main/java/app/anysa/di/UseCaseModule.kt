package app.anysa.di

import app.anysa.domain.usecase.AuthUseCase
import app.anysa.domain.usecase.CheckInfoChangesUseCase
import app.anysa.domain.usecase.CurrentUserUseCase
import app.anysa.domain.usecase.impl.AuthUseCaseImpl
import app.anysa.domain.usecase.impl.CheckInfoChangesUseCaseImpl
import app.anysa.domain.usecase.impl.CurrentUserUseCaseImpl
import dagger.Binds
import dagger.Module

@Module
interface UseCaseModule {

    @Binds
    fun bindAuthUseCase(useCase: AuthUseCaseImpl): AuthUseCase

    @Binds
    fun bindContactsUseCase(useCase: CurrentUserUseCaseImpl): CurrentUserUseCase

    @Binds
    fun bindCheckInfoChangesUseCase(useCase: CheckInfoChangesUseCaseImpl): CheckInfoChangesUseCase

}