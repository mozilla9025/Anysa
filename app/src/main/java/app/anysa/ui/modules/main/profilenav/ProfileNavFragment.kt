package app.anysa.ui.modules.main.profilenav

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import app.anysa.R
import app.anysa.ui.base.BaseNavFragment

class ProfileNavFragment : BaseNavFragment() {

    override val navHostFragmentId: Int
        get() = R.id.nav_profile

    companion object {
        fun newInstance() = ProfileNavFragment()
    }

    private lateinit var viewModel: ProfileNavViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_profile_nav, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ProfileNavViewModel::class.java)
    }

}
