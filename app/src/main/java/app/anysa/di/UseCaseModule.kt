package app.anysa.di

import app.anysa.domain.usecase.AuthUseCase
import app.anysa.domain.usecase.impl.AuthUseCaseImpl
import dagger.Binds
import dagger.Module

@Module
interface UseCaseModule {

    @Binds
    fun bindAuthUseCase(useCase: AuthUseCaseImpl): AuthUseCase

}