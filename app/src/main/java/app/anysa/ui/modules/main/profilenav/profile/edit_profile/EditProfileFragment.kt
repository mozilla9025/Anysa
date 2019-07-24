package app.anysa.ui.modules.main.profilenav.profile.edit_profile

import android.widget.Toast
import androidx.lifecycle.Observer
import app.anysa.R
import app.anysa.databinding.FragmentEditProfileBinding
import app.anysa.domain.pojo.ApiResponse
import app.anysa.domain.pojo.User
import app.anysa.helper.CheckHelper
import app.anysa.ui.base.abs.AbsFragment
import app.anysa.util.annotation.RequiresView
import app.anysa.util.annotation.RequiresViewModel


@RequiresView(R.layout.fragment_edit_profile)
@RequiresViewModel(EditProfileViewModel::class)
class EditProfileFragment : AbsFragment<EditProfileViewModel, FragmentEditProfileBinding>() {

    override fun onBound(binding: FragmentEditProfileBinding?) {
        super.onBound(binding)

        arguments?.let {
            binding?.user = EditProfileFragmentArgs.fromBundle(it).currentUser
        }

        observe()

        binding?.btnSave?.setOnClickListener {
            checkFieldsAndSignUp()
        }
    }

    private fun checkFieldsAndSignUp() {
        binding?.let {
            val checkHelper = CheckHelper(context)

            var isError = false

            val nameValid = checkHelper.isNameValid(it.setFullname.text)
            if (!nameValid.isValid) {
                Toast.makeText(context, nameValid.errorMessage, Toast.LENGTH_LONG).show()
                isError = true
            }
            val emailValid = checkHelper.checkEmailValid(it.setEmail.text)
            if (!emailValid.isValid) {
                Toast.makeText(context, emailValid.errorMessage, Toast.LENGTH_LONG).show()
                isError = true
            }
            val bioValid = checkHelper.isBioValid(it.setBio.text)
            if (!bioValid.isValid) {
                Toast.makeText(context, bioValid.errorMessage, Toast.LENGTH_LONG).show()
                isError = true
            }

            if (isError) return


            getViewModel()?.updateUserInfo(nameValid.formattedValue, emailValid.formattedValue, bioValid.formattedValue)
        }
    }


    private fun observe() {
        getViewModel()?.updateUserResponse?.reObserve(this, updateObserver)
    }

    private val updateObserver: Observer<ApiResponse<User>> = Observer { t ->
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
