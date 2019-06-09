package app.anysa.ui.modules.authorization.login

import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import app.anysa.R
import app.anysa.databinding.FragmentLoginBinding
import app.anysa.domain.pojo.ApiResponse
import app.anysa.domain.pojo.exception.InvalidAuthDataException
import app.anysa.helper.CheckHelper
import app.anysa.ui.base.abs.AbsFragment
import app.anysa.ui.modules.authorization.AuthSharedViewModel
import app.anysa.util.annotation.RequiresView
import app.anysa.util.annotation.RequiresViewModel
import app.anysa.util.extensions.logd
import app.anysa.util.extensions.showToast
import app.anysa.util.navigation.NavigationUtils
import kotlinx.android.synthetic.main.fragment_login.*

@RequiresViewModel(AuthSharedViewModel::class)
@RequiresView(R.layout.fragment_login)
class LoginFragment : AbsFragment<AuthSharedViewModel, FragmentLoginBinding>() {

    override fun onBound(binding: FragmentLoginBinding?) {
        super.onBound(binding)

        tv_sign_up.setOnClickListener({
            NavigationUtils.navigate(view,
                    LoginFragmentDirections.actionLoginFragmentToRegisterFragment())
        })
        btn_sign_in.setOnClickListener { checkFieldsAndSignIn() }

        observe()
    }

    private fun observe() {
        getViewModel()?.signInData?.observe(this, signInObserver)
    }

    val signInObserver: Observer<ApiResponse<Any>> = Observer { t ->
        context?.showToast("onChanged: ${t.exception}");

        when (t?.status) {
            ApiResponse.Status.SUCCESS -> {
                NavigationUtils.navigate(view,
                        LoginFragmentDirections.actionLoginFragmentToRegisterFragment())
                btn_sign_in.isEnabled = true
            }
            ApiResponse.Status.LOADING -> {
                btn_sign_in.isEnabled = false
            }
            ApiResponse.Status.ERROR -> {
                if (t.exception is InvalidAuthDataException) {
                    context?.showToast(R.string.login_fragment_invalid_phone_or_password)
                } else {
                    context?.showToast(R.string.error_can_not_connect_to_server)
                }
                btn_sign_in.isEnabled = true
            }
        }
    }

    private fun checkFieldsAndSignIn() {
        val checkHelper = CheckHelper(context)

        var isError = false

        val phoneNumberValid = checkHelper.isPhoneNumberValid(rfiv_phone.phone)
        if (!phoneNumberValid.isValid) {
            rfiv_phone.setError(phoneNumberValid.errorMessage)
            isError = true
        }
        val passwordValid = checkHelper.checkFieldIsEmpty(rfiv_password.text, true)
        if (!passwordValid.isValid) {
            rfiv_password.setError(passwordValid.errorMessage)
            isError = true
        }

        if (isError) {
            return
        }

        getViewModel()?.signIn(phoneNumberValid.formattedValue, passwordValid.formattedValue)
    }
}