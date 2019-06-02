package app.anysa.di

import app.anysa.ui.modules.authorization.login.LoginFragment
import app.anysa.ui.modules.authorization.register.RegisterFragment
import app.anysa.ui.modules.authorization.start.StartFragment
import app.anysa.ui.modules.main.MainFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule {
    @ContributesAndroidInjector
    abstract fun contributeLoginFragment(): LoginFragment
    @ContributesAndroidInjector
    abstract fun contributeStartFragment(): StartFragment
    @ContributesAndroidInjector
    abstract fun contributeRegisterFragment(): RegisterFragment
    @ContributesAndroidInjector
    abstract fun contributeMainFragment(): MainFragment
}