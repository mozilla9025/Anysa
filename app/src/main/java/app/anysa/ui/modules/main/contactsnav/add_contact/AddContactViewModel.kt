package app.anysa.ui.modules.main.contactsnav.add_contact

import androidx.lifecycle.MutableLiveData
import app.anysa.domain.pojo.ApiResponse
import app.anysa.domain.pojo.User
import app.anysa.domain.usecase.CurrentUserUseCase
import app.anysa.ui.base.abs.AbsViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject

class AddContactViewModel @Inject constructor(private val currentUserUseCase: CurrentUserUseCase) : AbsViewModel() {

    val findContactLiveData = MutableLiveData<ApiResponse<User>>()

    fun findContact(phone: String) {
        add(currentUserUseCase.getContactByPhone(phone)
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe {
                    findContactLiveData.value = ApiResponse.loading()
                }
                .subscribe({
                    findContactLiveData.value = ApiResponse.success()
                }, {
                    findContactLiveData.value = ApiResponse.error(it)
                    it.printStackTrace()
                }))

    }

}
