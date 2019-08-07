package app.anysa.ui.modules.main.profilenav.profile

import android.view.View
import androidx.lifecycle.Observer
import app.anysa.R
import app.anysa.databinding.FragmentProfileBinding
import app.anysa.domain.pojo.ApiResponse
import app.anysa.domain.pojo.CurrentUser
import app.anysa.ui.base.abs.AbsFragment
import app.anysa.ui.modules.MainActivity
import app.anysa.util.annotation.RequiresView
import app.anysa.util.annotation.RequiresViewModel
import app.anysa.util.navigation.NavigationUtils


@RequiresView(R.layout.fragment_profile)
@RequiresViewModel(ProfileViewModel::class)
class ProfileFragment : AbsFragment<ProfileViewModel, FragmentProfileBinding>() {

    override fun onBound(binding: FragmentProfileBinding?) {
        super.onBound(binding)

        getViewModel()?.getCurrentUser()
        observe()
        initListeners()
    }

    private fun observe() {
        getViewModel()?.currentUserResponse?.reObserve(this, userObserver)

        getViewModel()?.logoutData?.reObserve(this, Observer {
            when (it.status) {
                ApiResponse.Status.SUCCESS -> {
                    context?.let { it1 -> MainActivity.start(it1) }
                }
            }
        })
    }

    private fun initListeners() {
        binding?.apply {
            clEditProfile.setOnClickListener {
                user?.let {
                    NavigationUtils.navigate(view, ProfileFragmentDirections.actionProfileFragmentToEditProfileFragment(it))
                }
            }
            clSettings.setOnClickListener {
                NavigationUtils.navigate(view, ProfileFragmentDirections.actionProfileFragmentToSettingsFragment())
            }
            btnLogout.setOnClickListener {
                getViewModel()?.logout()
            }
            clLogout.setOnClickListener {
                getViewModel()?.logout()
            }
        }
    }

    private val userObserver: Observer<ApiResponse<CurrentUser>> = Observer { t ->
        when (t?.status) {
            ApiResponse.Status.SUCCESS -> {
                binding?.user = t.data
                binding?.executePendingBindings()
                showLoaded()
            }
            ApiResponse.Status.LOADING -> {
                showLoading()
            }
            ApiResponse.Status.ERROR -> {
                showLoadingError()
            }
        }
    }

    private fun showLoading() {
        binding?.pbLoading?.visibility = View.VISIBLE
        binding?.clContent?.visibility = View.GONE
        binding?.llLoadingError?.visibility = View.GONE
    }

    private fun showLoaded() {
        binding?.pbLoading?.visibility = View.GONE
        binding?.clContent?.visibility = View.VISIBLE
        binding?.llLoadingError?.visibility = View.GONE
    }

    private fun showLoadingError() {
        binding?.pbLoading?.visibility = View.GONE
        binding?.clContent?.visibility = View.GONE
        binding?.llLoadingError?.visibility = View.VISIBLE
    }
}
