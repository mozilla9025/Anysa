package app.anysa.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import app.anysa.ui.modules.MainActivityViewModel
import app.anysa.ui.modules.authorization.AuthSharedViewModel
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
    @ViewModelKey(AuthSharedViewModel::class)
    internal abstract fun bindAuthSharedViewModel(viewModel: AuthSharedViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(app.anysa.ui.modules.main.MainViewModel::class)
    internal abstract fun bindMainViewModel(viewModel: MainViewModel): ViewModel


}