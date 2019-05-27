package app.wallpaper.di

import app.anysa.ui.modules.authorization.login.LoginFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule {
    @ContributesAndroidInjector
    abstract fun contributeLoginFragment(): LoginFragment
}