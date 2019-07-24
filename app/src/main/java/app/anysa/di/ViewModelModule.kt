package app.anysa.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import app.anysa.ui.modules.MainActivityViewModel
import app.anysa.ui.modules.authorization.AuthSharedViewModel
import app.anysa.ui.modules.authorization.start.StartViewModel
import app.anysa.ui.modules.main.MainViewModel
import app.anysa.ui.modules.main.contactsnav.ContactsNavViewModel
import app.anysa.ui.modules.main.contactsnav.add_contact.AddContactViewModel
import app.anysa.ui.modules.main.contactsnav.contacts.ContactsViewModel
import app.anysa.ui.modules.main.profilenav.ProfileNavViewModel
import app.anysa.ui.modules.main.profilenav.profile.ProfileViewModel
import app.anysa.ui.modules.main.profilenav.profile.edit_profile.EditProfileViewModel
import app.anysa.ui.modules.main.profilenav.profile.settings.SettingsViewModel
import app.anysa.ui.modules.main.profilenav.profile.settings.password.ChangePasswordViewModel
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

    @Binds
    @IntoMap
    @ViewModelKey(SettingsViewModel::class)
    internal abstract fun bindSettingsViewModel(viewModel: SettingsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ProfileNavViewModel::class)
    internal abstract fun bindProfileNavViewModel(viewModel: ProfileNavViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ProfileViewModel::class)
    internal abstract fun bindProfileViewModel(viewModel: ProfileViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ContactsNavViewModel::class)
    internal abstract fun bindContactsNavViewModel(viewModel: ContactsNavViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ContactsViewModel::class)
    internal abstract fun bindContactsViewModel(viewModel: ContactsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(AddContactViewModel::class)
    internal abstract fun bindAddContactViewModel(viewModel: AddContactViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(EditProfileViewModel::class)
    internal abstract fun bindEditProfileViewModel(viewModel: EditProfileViewModel): ViewModel
    @Binds
    @IntoMap
    @ViewModelKey(ChangePasswordViewModel::class)
    internal abstract fun bindChangePasswordViewModel(viewModel: ChangePasswordViewModel): ViewModel

}