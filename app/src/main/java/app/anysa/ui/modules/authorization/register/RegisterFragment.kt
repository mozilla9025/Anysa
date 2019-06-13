package app.anysa.ui.modules.authorization.register

import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import app.anysa.R
import app.anysa.databinding.FragmentRegisterBinding
import app.anysa.domain.pojo.ApiResponse
import app.anysa.domain.pojo.exception.InvalidAuthDataException
import app.anysa.domain.pojo.exception.PhoneAlreadyRegisteredException
import app.anysa.helper.CheckHelper
import app.anysa.ui.base.abs.AbsFragment
import app.anysa.ui.modules.authorization.AuthSharedViewModel
import app.anysa.ui.widget.expandable_layout.ExpandableLayout
import app.anysa.util.annotation.RequiresView
import app.anysa.util.annotation.RequiresViewModel
import app.anysa.util.app_bar.AppBarStateChangeListener
import app.anysa.util.extensions.logd
import app.anysa.util.extensions.showToast
import app.anysa.util.navigation.NavigationUtils
import com.google.android.material.appbar.AppBarLayout
import kotlinx.android.synthetic.main.fragment_register.*
import kotlinx.android.synthetic.main.view_register_form_input.*

@RequiresViewModel(AuthSharedViewModel::class)
@RequiresView(R.layout.fragment_register)
class RegisterFragment : AbsFragment<AuthSharedViewModel, FragmentRegisterBinding>() {

    override fun onBound(binding: FragmentRegisterBinding?) {
        super.onBound(binding)

        el_advanced_data.setOnExpansionUpdateListener { expansionFraction, state ->
            when (state) {
                ExpandableLayout.State.COLLAPSED -> {
                    collapse()
                }
                ExpandableLayout.State.EXPANDED -> {
                    expand()
                }
            }
        }

        collapse()

        tv_advanced_data.setOnClickListener {
            el_advanced_data.toggle(false)
        }

        app_bar.addOnOffsetChangedListener(object : AppBarStateChangeListener() {
            override fun onStateChanged(appBarLayout: AppBarLayout, state: State) {
                when (state) {
                    State.COLLAPSED -> view_appbar_shadow.visibility = View.VISIBLE
                    else -> view_appbar_shadow.visibility = View.GONE
                }
            }
        })

        tv_sign_in.setOnClickListener {
            NavigationUtils.navigate(view,
                    RegisterFragmentDirections.actionRegisterFragmentToLoginFragment())
        }

        btn_sign_up.setOnClickListener {
            checkFieldsAndSignUp()
        }

        observe()
    }

    private fun observe() {
        getViewModel()?.signUpData?.observe(this, signUpObserver)
    }

    private val signUpObserver: Observer<ApiResponse<Any>> = Observer { t ->
        when (t?.status) {
            ApiResponse.Status.SUCCESS -> {
                NavigationUtils.navigate(view,
                        RegisterFragmentDirections.actionRegisterFragmentToMainFragment())
                btn_sign_up.isEnabled = true
            }
            ApiResponse.Status.LOADING -> {
                btn_sign_up.isEnabled = false
            }
            ApiResponse.Status.ERROR -> {
                logd("onchange:: ${t.exception}")
                if (t.exception is InvalidAuthDataException) {
                    context?.showToast(getString(R.string.registration_error_occured))
                } else if (t.exception is PhoneAlreadyRegisteredException) {
                    rfiv_phone.setError(getString(R.string.registration_fragment_error_phone_registered))
                } else {
                    context?.showToast(R.string.error_can_not_connect_to_server)
                }
                btn_sign_up.isEnabled = true
            }
        }
    }


    private fun collapse() {
        tv_advanced_data.setText(R.string.register_fragment_btn_show_advanced_data)

        app_bar.setExpanded(true, false)
        val p = toolbar_layout.layoutParams as AppBarLayout.LayoutParams
        p.scrollFlags = 0
        toolbar_layout.layoutParams = p

        cl_footer.visibility = View.VISIBLE
    }

    private fun expand() {
        tv_advanced_data.setText(R.string.register_fragment_btn_hide_advanced_data)
        val p = toolbar_layout.layoutParams as AppBarLayout.LayoutParams
        p.scrollFlags = AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL or AppBarLayout.LayoutParams.SCROLL_FLAG_EXIT_UNTIL_COLLAPSED
        toolbar_layout.layoutParams = p

        cl_footer.visibility = View.GONE
    }

    private fun checkFieldsAndSignUp() {
        val checkHelper = CheckHelper(context)

        var isError = false
        var needToExpand = false

        val phoneNumberValid = checkHelper.isPhoneNumberValid(rfiv_phone.phone)
        if (!phoneNumberValid.isValid) {
            rfiv_phone.setError(phoneNumberValid.errorMessage)
            isError = true
        }
        val passwordValid = checkHelper.isPasswordValid(rfiv_password.text)
        if (!passwordValid.isValid) {
            rfiv_password.setError(passwordValid.errorMessage)
            isError = true
        }
        val nameValid = checkHelper.isNameValid(rfiv_full_name.text)
        if (!nameValid.isValid) {
            rfiv_full_name.setError(nameValid.errorMessage)
            isError = true
            needToExpand = true
        }
        val emailValid = checkHelper.checkEmailValid(rfiv_email.text)
        if (!emailValid.isValid) {
            rfiv_email.setError(emailValid.errorMessage)
            isError = true
            needToExpand = true
        }
        val bioValid = checkHelper.isBioValid(rfiv_bio.text)
        if (!bioValid.isValid) {
            rfiv_bio.setError(bioValid.errorMessage)
            isError = true
            needToExpand = true
        }

        if (needToExpand) el_advanced_data.expand(false)
        if (isError) return


        getViewModel()?.signUp(phoneNumberValid.formattedValue, passwordValid.formattedValue,
                emailValid.formattedValue, nameValid.formattedValue, bioValid.formattedValue)
    }
}