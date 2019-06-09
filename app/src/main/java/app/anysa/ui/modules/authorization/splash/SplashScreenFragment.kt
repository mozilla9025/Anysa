package app.anysa.ui.modules.authorization.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import app.anysa.R
import app.anysa.databinding.FragmentSplashScreenBinding
import app.anysa.domain.pojo.ApiResponse
import app.anysa.ui.base.abs.AbsFragment
import app.anysa.ui.modules.authorization.AuthSharedViewModel
import app.anysa.util.annotation.RequiresView
import app.anysa.util.annotation.RequiresViewModel
import app.anysa.util.navigation.NavigationUtils


@RequiresViewModel(AuthSharedViewModel::class)
@RequiresView(R.layout.fragment_splash_screen)
class SplashScreenFragment : AbsFragment<AuthSharedViewModel, FragmentSplashScreenBinding>() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_splash_screen, container, false)
    }

    override fun onBound(binding: FragmentSplashScreenBinding?) {
        super.onBound(binding)
        getViewModel()?.isLoggedInData?.reObserve(this, Observer {
            when (it.status) {
                ApiResponse.Status.SUCCESS ->
                    NavigationUtils.navigate(view, SplashScreenFragmentDirections.actionSplashScreenFragmentToMainFragment())
                else ->
                    NavigationUtils.navigate(view, SplashScreenFragmentDirections.actionSplashScreenFragmentToStartFragment())

            }
        })
        getViewModel()?.isLoggedIn()
    }
}