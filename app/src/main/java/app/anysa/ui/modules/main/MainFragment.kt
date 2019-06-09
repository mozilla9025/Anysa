package app.anysa.ui.modules.main

import androidx.lifecycle.Observer
import app.anysa.R
import app.anysa.databinding.FragmentMainBinding
import app.anysa.domain.pojo.ApiResponse
import app.anysa.ui.base.abs.AbsFragment
import app.anysa.util.annotation.RequiresView
import app.anysa.util.annotation.RequiresViewModel
import app.anysa.util.navigation.NavigationUtils
import kotlinx.android.synthetic.main.fragment_main.*

@RequiresView(R.layout.fragment_main)
@RequiresViewModel(MainViewModel::class)
class MainFragment : AbsFragment<MainViewModel, FragmentMainBinding>() {

    override fun onBound(binding: FragmentMainBinding?) {
        super.onBound(binding)
        btnLogout.setOnClickListener {
            getViewModel()?.logout()
        }

        observe()
    }

    private fun observe() {
        getViewModel()?.logoutData?.reObserve(this, Observer {
            when (it.status) {
                ApiResponse.Status.SUCCESS -> {
                    NavigationUtils.navigate(view,
                            MainFragmentDirections.actionMainFragmentToSplashScreenFragment())
                }
            }
        })

    }

}