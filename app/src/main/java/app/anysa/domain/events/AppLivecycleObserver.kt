package app.anysa.domain.events

import android.annotation.SuppressLint
import android.content.Context
import android.widget.Toast
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import app.anysa.BuildConfig
import io.reactivex.disposables.CompositeDisposable


class AppLifecycleObserver constructor(
        private val context: Context
) : LifecycleObserver {

    private val tasksToDispose = CompositeDisposable()

    @SuppressLint("CheckResult")
    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onEnterForeground() {
//        tasksToDispose.add(
//                Flowable.combineLatest(
//                        sharedPrefObserver.createObservableForKey(PrefStorageImpl.TOKEN, ""),
//                        sharedPrefObserver.createObservableForKey(PrefStorageImpl.KEY_CURRENT_USER, "").map { userStorage.getUserRaw() },
//                        BiFunction<String, User, Boolean> { t1, t2 -> !t1.isEmpty() && !t2.isEmpty() })
//                        .doOnNext {
//                            Timber.e("Token is like that: $it")
//                        }
//                        .filter { it }
//                        .switchIfEmpty(Flowable.error(Throwable(Keys.LocalErrors.ERR_NO_USER_CREDENTIALS)))
//                        .flatMap { notificationsRepo.getNotificationChannelSubscription() }
//                        .subscribeOn(Schedulers.io())
//                        .observeOn(AndroidSchedulers.mainThread())
//                        .subscribe({
//                            Timber.e("Notification observing\n%s", it.compile())
//                        }, { err ->
//                            Timber.e(err)
//                            tasksToDispose.dispose()
//                        }))

        if (BuildConfig.DEBUG)
            Toast.makeText(context, "App Start", Toast.LENGTH_LONG).show()
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun onApplicationOff() {
        tasksToDispose.clear()
        if (BuildConfig.DEBUG)
            Toast.makeText(context, "App Closed", Toast.LENGTH_LONG).show()
    }

}