package app.anysa.ui.modules.main.profilenav.profile

import app.anysa.R
import app.anysa.databinding.FragmentProfileBinding
import app.anysa.ui.base.abs.AbsFragment
import app.anysa.util.annotation.RequiresView
import app.anysa.util.annotation.RequiresViewModel


@RequiresView(R.layout.fragment_profile)
@RequiresViewModel(ProfileViewModel::class)
class ProfileFragment : AbsFragment<ProfileViewModel, FragmentProfileBinding>(){


}
