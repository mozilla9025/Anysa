package app.anysa.app

import android.content.Context
import android.content.res.Configuration
import app.anysa.di.AppComponent
import app.anysa.di.DaggerAppComponent
import app.anysa.helper.locale.LocaleManager
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class ApplicationLoader : DaggerApplication() {
    private lateinit var androidInjector: AndroidInjector<out DaggerApplication>

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        INSTANCE = this
        androidInjector = DaggerAppComponent.builder()
                .application(this)
                .build()
    }

    override fun onCreate() {
        super.onCreate()

        LocaleManager.setLocale(this)
    }

    companion object {
        private var INSTANCE: ApplicationLoader? = null
        @JvmStatic
        fun get(): ApplicationLoader = INSTANCE!!

        @JvmStatic
        public fun getAppComponent(): AppComponent {
            return ApplicationLoader.get().androidInjector as AppComponent
        }
    }

    public override fun applicationInjector(): AndroidInjector<out DaggerApplication> = androidInjector

    override fun onConfigurationChanged(newConfig: Configuration?) {
        super.onConfigurationChanged(newConfig)
        LocaleManager.setLocale(this)
    }
}