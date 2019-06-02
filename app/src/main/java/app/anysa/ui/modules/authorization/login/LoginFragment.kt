package app.anysa.ui.modules.authorization.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import app.anysa.R
import app.anysa.databinding.FragmentLoginBinding
import app.anysa.ui.base.BaseFragment
import app.anysa.ui.base.abs.AbsFragment
import app.anysa.util.annotation.RequiresView
import app.anysa.util.annotation.RequiresViewModel
import app.anysa.util.navigation.NavigationUtils
import kotlinx.android.synthetic.main.fragment_login.*

@RequiresViewModel(LoginViewModel::class)
@RequiresView(R.layout.fragment_login)
class LoginFragment : AbsFragment<LoginViewModel, FragmentLoginBinding>() {

    override fun onBound(binding: FragmentLoginBinding?) {
        super.onBound(binding)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        tv_sign_up.setOnClickListener({
            NavigationUtils.navigate(view,
                    LoginFragmentDirections.actionLoginFragmentToRegisterFragment())
        })
    }
}