package app.anysa.ui.modules.main.profilenav.profile

import androidx.lifecycle.MutableLiveData
import app.anysa.domain.pojo.ApiResponse
import app.anysa.domain.pojo.User
import app.anysa.domain.usecase.AuthUseCase
import app.anysa.domain.usecase.ContactsUseCase
import app.anysa.ui.base.abs.AbsViewModel
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject

class ProfileViewModel @Inject constructor(
        private val contactsUseCase: ContactsUseCase,
        private val authUseCase: AuthUseCase) : AbsViewModel() {

    val currentUserResponse = MutableLiveData<ApiResponse<User>>()
    val logoutData = MutableLiveData<ApiResponse<Completable>>()

    fun getCurrentUser() {
        add(contactsUseCase.getCurrentUser()
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe {
                    currentUserResponse.value = ApiResponse.loading()
                }
                .subscribe({
                    val array = it.data<List<User>>()
                    if (array.isNotEmpty())
                        currentUserResponse.value = ApiResponse.success(array[0])
                    else
                        currentUserResponse.value = ApiResponse.error()

                }, {
                    currentUserResponse.value = ApiResponse.error(it)
                    it.printStackTrace()
                }))
    }

    fun logout() {
        add(authUseCase.logout()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    logoutData.value = ApiResponse.success()
                }, {
                    logoutData.value = ApiResponse.error(it)
                }))
    }
}