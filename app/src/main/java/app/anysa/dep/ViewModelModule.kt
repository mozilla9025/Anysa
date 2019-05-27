package app.wallpaper.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import app.anysa.ui.modules.authorization.login.LoginViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {

    @Binds
    fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel::class)
    fun bindHomeViewModel(viewModel: LoginViewModel): ViewModel



}