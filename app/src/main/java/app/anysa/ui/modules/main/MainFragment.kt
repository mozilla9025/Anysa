package app.anysa.ui.modules.main

import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import app.anysa.R
import app.anysa.databinding.FragmentMainBinding
import app.anysa.domain.pojo.ApiResponse
import app.anysa.ui.base.abs.AbsFragment
import app.anysa.util.annotation.RequiresView
import app.anysa.util.annotation.RequiresViewModel
import app.anysa.util.navigation.KeepStateNavigator
import app.anysa.util.navigation.NavigationUtils
import kotlinx.android.synthetic.main.fragment_main.*

@RequiresView(R.layout.fragment_main)
@RequiresViewModel(MainViewModel::class)
class MainFragment : AbsFragment<MainViewModel, FragmentMainBinding>() {

    private var navigator: KeepStateNavigator? = null

    override fun onBound(binding: FragmentMainBinding?) {
        super.onBound(binding)
        initNavigation()

        btnLogout.setOnClickListener {
            getViewModel()?.logout()
        }

        observe()
    }

    private fun initNavigation() {
        val navHostFragment = childFragmentManager.findFragmentById(R.id.main_fragment_nav_host)
        val navController = navHostFragment?.view?.let { Navigation.findNavController(it) }

        val mainNavigation = R.navigation.nav_main

        navigator = context?.let {
            navHostFragment?.childFragmentManager?.let { it1 ->
                KeepStateNavigator(it, it1, R.id.main_fragment_nav_host)
            }
        }

        navigator?.let { navController?.navigatorProvider?.addNavigator(it) }
        navController?.setGraph(mainNavigation)

        binding?.viewBottomNavigation?.let {
            navController?.let { it1 ->
                NavigationUtils.setupWithNavController(it, it1)
            }
        }
    }

    private fun observe() {
        getViewModel()?.logoutData?.reObserve(this, Observer {
            when (it.status) {
                ApiResponse.Status.SUCCESS -> {
                    NavigationUtils.navigate(view,
                            MainFragmentDirections.actionMainFragmentToSplashScreenFragment()
                    )
                }
            }
        })
    }
}