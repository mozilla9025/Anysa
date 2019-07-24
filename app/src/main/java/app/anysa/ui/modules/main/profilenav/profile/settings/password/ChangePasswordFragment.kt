package app.anysa.ui.modules.main.profilenav.profile.settings.password

import android.widget.Toast
import app.anysa.R
import app.anysa.databinding.FragmentChangePasswordBinding
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
    }

    private fun changePassword() {
        binding?.let {
            val checkHelper = CheckHelper(context)

            var isError = false
            //todo

            val oldPasswordValid = checkHelper.checkFieldIsEmpty(it.setCurrentPassword.text)
            if (!oldPasswordValid.isValid) {
                Toast.makeText(context, "Please enter your current password", Toast.LENGTH_LONG).show()
                isError = true
            }

            val newPasswordValid = checkHelper.isPasswordValid(it.setNewPassword.text)
            if (!newPasswordValid.isValid) {
                Toast.makeText(context, newPasswordValid.errorMessage, Toast.LENGTH_LONG).show()
                isError = true
            }
            val newPasswordConfirmValid = checkHelper.arePasswordsTheSame(newPasswordValid.formattedValue, it.setNewPasswordConfirm.text)
            if (!newPasswordConfirmValid.isValid) {
                Toast.makeText(context, newPasswordValid.errorMessage, Toast.LENGTH_LONG).show()
                isError = true
            }

            if (isError) return


            getViewModel()?.updateUserInfo(newPasswordValid.formattedValue)
        }
    }
}
