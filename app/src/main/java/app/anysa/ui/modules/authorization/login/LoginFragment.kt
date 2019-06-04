package app.anysa.ui.modules.authorization.login

import android.os.Bundle
import android.widget.Toast
import app.anysa.R
import app.anysa.databinding.FragmentLoginBinding
import app.anysa.helper.CheckHelper
import app.anysa.ui.base.abs.AbsFragment
import app.anysa.ui.modules.authorization.AuthSharedViewModel
import app.anysa.util.annotation.RequiresView
import app.anysa.util.annotation.RequiresViewModel
import app.anysa.util.navigation.NavigationUtils
import kotlinx.android.synthetic.main.fragment_login.*

@RequiresViewModel(AuthSharedViewModel::class)
@RequiresView(R.layout.fragment_login)
class LoginFragment : AbsFragment<AuthSharedViewModel, FragmentLoginBinding>() {

    override fun onBound(binding: FragmentLoginBinding?) {
        super.onBound(binding)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        tv_sign_up.setOnClickListener({
            NavigationUtils.navigate(view,
                    LoginFragmentDirections.actionLoginFragmentToRegisterFragment())
        })
        btn_sign_in.setOnClickListener { checkFieldsAndSignIn() }
    }

    private fun checkFieldsAndSignIn() {
        val checkHelper = CheckHelper(context)

        var isError = false

        val phoneNumberValid = checkHelper.isPhoneNumberValid(rfiv_phone.phone)
        if (!phoneNumberValid.isValid) {
            rfiv_phone.setError(phoneNumberValid.errorMessage)
            isError = true
        }
        val passwordValid = checkHelper.checkFieldIsEmpty(rfiv_password.text)
        if (!passwordValid.isValid) {
            rfiv_password.setError(passwordValid.errorMessage)
            isError = true
        }

        if (isError) {
            return
        }

        getViewModel()?.signIn(phoneNumberValid.formattedValue!!, passwordValid.formattedValue!!)
        Toast.makeText(context, "OK", Toast.LENGTH_LONG).show()
    }
}