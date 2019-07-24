package app.anysa.ui.modules.main.profilenav.profile.edit_profile

import androidx.lifecycle.MutableLiveData
import app.anysa.domain.pojo.ApiResponse
import app.anysa.domain.pojo.User
import app.anysa.domain.usecase.ContactsUseCase
import app.anysa.ui.base.abs.AbsViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject

class EditProfileViewModel @Inject constructor(
        private val contactsUseCase: ContactsUseCase) : AbsViewModel() {


    val updateUserResponse = MutableLiveData<ApiResponse<User>>()

    fun updateUserInfo(name: String, description: String, email: String) {
        add(contactsUseCase.modifyCurrentUserInfo(name, description, email)
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe {
                    updateUserResponse.value = ApiResponse.loading()
                }
                .subscribe({
                    if (it.rest == true.toString()) {
                        updateUserResponse.value = ApiResponse.success()
                    } else {
                        updateUserResponse.value = ApiResponse.error()
                    }
                }, {
                    updateUserResponse.value = ApiResponse.error(it)
                    it.printStackTrace()
                }))
    }
}
