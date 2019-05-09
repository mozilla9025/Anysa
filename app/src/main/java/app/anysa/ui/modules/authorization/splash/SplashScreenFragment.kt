package app.anysa.ui.modules.authorization.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import app.anysa.R
import app.anysa.helper.preferences.PreferencesManager
import app.anysa.ui.base.BaseFragment
import app.anysa.util.extensions.logd
import app.anysa.util.navigation.NavigationUtils

class SplashScreenFragment : BaseFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_splash_screen, container, false)
    }

    override fun onResume() {
        super.onResume()
        logd("onresume")
        if (PreferencesManager.token == null) {
            NavigationUtils.navigate(view, R.id.action_splashScreenFragment_to_startFragment)
        } else {
            NavigationUtils.navigate(view, R.id.action_splashScreenFragment_to_mainFragment)
        }
    }
}