package app.anysa.ui.modules.main.profilenav.profile.settings.password

import android.widget.Toast
import androidx.lifecycle.Observer
import app.anysa.R
import app.anysa.databinding.FragmentChangePasswordBinding
import app.anysa.domain.pojo.ApiResponse
import app.anysa.helper.CheckHelper
import app.anysa.ui.base.abs.AbsFragment
import app.anysa.util.annotation.RequiresView
import app.anysa.util.annotation.RequiresViewModel


@RequiresView(R.layout.fragment_change_password)
@RequiresViewModel(ChangePasswordViewModel::class)
class ChangePasswordFragment : AbsFragment<ChangePasswordViewModel, FragmentChangePasswordBinding>() {


    override fun onBound(binding: FragmentChangePasswordBinding?) {
        super.onBound(binding)

        binding?.btnSave?.setOnClickListener {
            changePassword()
        }
        observe()
    }

    private fun changePassword() {
        binding?.let {
            val checkHelper = CheckHelper(context)


            val oldPasswordValid = checkHelper.checkFieldIsEmpty(it.setCurrentPassword.text, true)
            if (!oldPasswordValid.isValid) {
                Toast.makeText(context, getString(R.string.fragment_change_password_error_enter_current), Toast.LENGTH_LONG).show()
                return
            }

            val newPasswordValid = checkHelper.isPasswordValid(it.setNewPassword.text, getString(R.string.fragment_change_password_error_new_pass_empty))
            if (!newPasswordValid.isValid) {
                Toast.makeText(context, newPasswordValid.errorMessage, Toast.LENGTH_LONG).show()
                return
            }
            val newPasswordConfirmValid = checkHelper.arePasswordsTheSame(newPasswordValid.formattedValue, it.setNewPasswordConfirm.text)
            if (!newPasswordConfirmValid.isValid) {
                Toast.makeText(context, newPasswordConfirmValid.errorMessage, Toast.LENGTH_LONG).show()
                return
            }


            getViewModel()?.updateUserInfo(newPasswordValid.formattedValue)
        }
    }

    private fun observe() {
        getViewModel()?.updatePasswordResponse?.reObserve(this, updateObserver)
    }

    private val updateObserver: Observer<ApiResponse<Any>> = Observer { t ->
        when (t?.status) {
            ApiResponse.Status.SUCCESS -> {
                binding?.btnSave?.isLoading = false
            }
            ApiResponse.Status.LOADING -> {
                binding?.btnSave?.isLoading = true
            }
            ApiResponse.Status.ERROR -> {
                binding?.btnSave?.isLoading = false
                Toast.makeText(context, getString(R.string.error_can_not_connect_to_server), Toast.LENGTH_LONG).show()
            }
        }
    }
}
