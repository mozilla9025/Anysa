package app.anysa.di

import app.anysa.ui.modules.authorization.login.LoginFragment
import app.anysa.ui.modules.authorization.register.RegisterFragment
import app.anysa.ui.modules.authorization.splash.SplashScreenFragment
import app.anysa.ui.modules.authorization.start.StartFragment
import app.anysa.ui.modules.main.MainFragment
import app.anysa.ui.modules.main.contactsnav.ContactsNavFragment
import app.anysa.ui.modules.main.contactsnav.add_contact.AddContactFragment
import app.anysa.ui.modules.main.contactsnav.contacts.ContactsFragment
import app.anysa.ui.modules.main.profilenav.ProfileNavFragment
import app.anysa.ui.modules.main.profilenav.profile.ProfileFragment
import app.anysa.ui.modules.main.profilenav.profile.settings.SettingsFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule {
    @ContributesAndroidInjector
    abstract fun contributeSplashScreenFragment(): SplashScreenFragment
    @ContributesAndroidInjector
    abstract fun contributeLoginFragment(): LoginFragment
    @ContributesAndroidInjector
    abstract fun contributeStartFragment(): StartFragment
    @ContributesAndroidInjector
    abstract fun contributeRegisterFragment(): RegisterFragment
    @ContributesAndroidInjector
    abstract fun contributeMainFragment(): MainFragment
    @ContributesAndroidInjector
    abstract fun contributeSettingsFragment(): SettingsFragment
    @ContributesAndroidInjector
    abstract fun contributeProfileFragment(): ProfileFragment
    @ContributesAndroidInjector
    abstract fun contributeProfileNavFragment(): ProfileNavFragment
    @ContributesAndroidInjector
    abstract fun contributeContactsNavFragment(): ContactsNavFragment
    @ContributesAndroidInjector
    abstract fun contributeContactsFragment(): ContactsFragment
    @ContributesAndroidInjector
    abstract fun contributeAddContactFragment(): AddContactFragment
}