package app.anysa.ui.modules.main.profilenav

import app.anysa.R
import app.anysa.databinding.FragmentProfileNavBinding
import app.anysa.ui.base.abs.AbsFragment
import app.anysa.ui.modules.main.MainViewModel
import app.anysa.util.annotation.NavFragment
import app.anysa.util.annotation.RequiresView
import app.anysa.util.annotation.RequiresViewModel

@RequiresView(R.layout.fragment_profile_nav)
@RequiresViewModel(ProfileNavViewModel::class)
@NavFragment
class ProfileNavFragment : AbsFragment<MainViewModel, FragmentProfileNavBinding>() {


}
