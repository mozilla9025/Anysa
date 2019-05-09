package app.anysa.app

import androidx.multidex.MultiDexApplication

class ApplicationLoader : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()

        instance = this

//        applicationComponent = DaggerApplicationComponent.builder()
//                .appModule(AppModule(this))
//                .networkModule(NetworkModule())
//                .apiModule(ApiModule())
//                .apiControllerModule(ApiControllerModule())
//                .build()
    }

    companion object {
        lateinit var instance: ApplicationLoader
//        lateinit var applicationComponent: ApplicationComponent
    }
}