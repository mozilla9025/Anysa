package app.anysa.app

import android.content.res.Configuration
import androidx.multidex.MultiDexApplication
import app.anysa.di.*
import app.anysa.helper.locale.LocaleManager

class ApplicationLoader : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()

        instance = this

        applicationComponent = DaggerApplicationComponent.builder()
                .appModule(AppModule(this))
                .networkModule(NetworkModule())
                .apiModule(ApiModule())
                .apiControllersModule(ApiControllersModule())
                .build()

        LocaleManager.setLocale(this)
    }

    override fun onConfigurationChanged(newConfig: Configuration?) {
        super.onConfigurationChanged(newConfig)
        LocaleManager.setLocale(this)
    }

    companion object {
        lateinit var instance: ApplicationLoader
        lateinit var applicationComponent: ApplicationComponent
    }

}