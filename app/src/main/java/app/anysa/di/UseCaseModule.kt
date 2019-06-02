package app.anysa.di

import app.anysa.domain.usecase.SignUpUseCase
import app.anysa.domain.usecase.impl.SignUpUseCaseImpl
import dagger.Binds
import dagger.Module

@Module
interface UseCaseModule {

    @Binds
    fun bindAuthUseCase(useCase: SignUpUseCaseImpl): SignUpUseCase

}