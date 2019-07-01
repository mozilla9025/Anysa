package app.anysa.ui.modules.main.contactsnav.add_contact

import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import app.anysa.R
import app.anysa.databinding.FragmentAddContactBinding
import app.anysa.databinding.FragmentContactsBinding
import app.anysa.domain.pojo.ApiResponse
import app.anysa.domain.pojo.User
import app.anysa.domain.pojo.exception.InvalidAuthDataException
import app.anysa.helper.CheckHelper
import app.anysa.ui.base.abs.AbsFragment
import app.anysa.ui.modules.authorization.login.LoginFragmentDirections
import app.anysa.util.annotation.RequiresView
import app.anysa.util.annotation.RequiresViewModel
import app.anysa.util.app_bar.AppBarStateChangeListener
import app.anysa.util.extensions.logd
import app.anysa.util.extensions.showToast
import app.anysa.util.navigation.NavigationUtils
import com.google.android.material.appbar.AppBarLayout
import kotlinx.android.synthetic.main.fragment_add_contact.*
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.fragment_register.*


@RequiresView(R.layout.fragment_add_contact)
@RequiresViewModel(AddContactViewModel::class)
class AddContactFragment : AbsFragment<AddContactViewModel, FragmentAddContactBinding>() {

    override fun onBound(binding: FragmentAddContactBinding?) {
        super.onBound(binding)


        binding?.apply {
            btnSearchContact.setOnClickListener {
                checkPhoneAndDoSearch()
            }
        }

        observe()
    }

    private fun observe() {
        getViewModel()?.findContactLiveData?.reObserve(this, findContactObserver)
    }

    private val findContactObserver: Observer<ApiResponse<User>> = Observer { t ->
        when (t?.status) {
            ApiResponse.Status.SUCCESS -> {
                binding?.btnSearchContact?.isEnabled = true
            }
            ApiResponse.Status.LOADING -> {
                binding?.btnSearchContact?.isEnabled = false
            }
            ApiResponse.Status.ERROR -> {
                binding?.btnSearchContact?.isEnabled = true
                context?.showToast(R.string.error_can_not_connect_to_server)
            }
        }
    }

    private fun checkPhoneAndDoSearch() {
        val checkHelper = CheckHelper(context)

        var isError = false

        val phoneNumberValid = checkHelper.isPhoneNumberValid(binding?.searchView?.searchQuery!!)
        if (!phoneNumberValid.isValid) {
            context?.showToast(R.string.error_invalid_phone_number)
            isError = true
        }

        if (isError) {
            return
        }

        getViewModel()?.findContact(phoneNumberValid.formattedValue)
    }

}
