package app.anysa.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import app.anysa.ui.modules.MainActivityViewModel
import app.anysa.ui.modules.authorization.login.LoginViewModel
import app.anysa.ui.modules.authorization.register.RegisterViewModel
import app.anysa.ui.modules.authorization.start.StartViewModel
import app.anysa.ui.modules.main.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {


    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelProviderFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(MainActivityViewModel::class)
    internal abstract fun bindMainActivityViewModel(activityViewModel: MainActivityViewModel): ViewModel
    @Binds
    @IntoMap
    @ViewModelKey(StartViewModel::class)
    internal abstract fun bindMainStartViewModel(activityViewModel: StartViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(RegisterViewModel::class)
    internal abstract fun bindRegisterViewModel(viewModel: RegisterViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel::class)
    internal abstract fun bindLoginViewModel(viewModel: LoginViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(app.anysa.ui.modules.main.MainViewModel::class)
    internal abstract fun bindMainViewModel(viewModel: MainViewModel): ViewModel


}