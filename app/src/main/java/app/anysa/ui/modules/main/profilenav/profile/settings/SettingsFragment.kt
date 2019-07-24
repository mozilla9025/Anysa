package app.anysa.ui.modules.main.profilenav.profile.settings

import app.anysa.R
import app.anysa.databinding.FragmentSettingsBinding
import app.anysa.ui.base.abs.AbsFragment
import app.anysa.util.annotation.RequiresView
import app.anysa.util.annotation.RequiresViewModel
import app.anysa.util.navigation.NavigationUtils


@RequiresView(R.layout.fragment_settings)
@RequiresViewModel(SettingsViewModel::class)
class SettingsFragment : AbsFragment<SettingsViewModel, FragmentSettingsBinding>() {


    override fun onBound(binding: FragmentSettingsBinding?) {
        super.onBound(binding)

        initListeners()

    }

    private fun initListeners() {
        binding?.apply {
            clPassword.setOnClickListener {
                NavigationUtils.navigate(view, SettingsFragmentDirections.actionSettingsFragmentToChangePasswordFragment())
            }
            clLanguage.setOnClickListener {
            }
            clNotifications.setOnClickListener {
            }
        }
    }
}
