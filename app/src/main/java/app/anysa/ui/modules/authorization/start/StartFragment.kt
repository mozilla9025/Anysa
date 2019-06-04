package app.anysa.ui.modules.authorization.start

import android.os.Bundle
import android.text.Html
import android.text.method.LinkMovementMethod
import app.anysa.R
import app.anysa.app.Constants
import app.anysa.databinding.FragmentStartBinding
import app.anysa.ui.base.abs.AbsFragment
import app.anysa.util.annotation.RequiresView
import app.anysa.util.annotation.RequiresViewModel
import app.anysa.util.navigation.NavigationUtils
import kotlinx.android.synthetic.main.fragment_start.*

@RequiresViewModel(StartViewModel::class)
@RequiresView(R.layout.fragment_start)
class StartFragment : AbsFragment<StartViewModel, FragmentStartBinding>() {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val text = Html.fromHtml("<u><a href=${Constants.PRIVACY_POLICY_URL}>${getString(R.string.start_fragment_privacy_policy)}</a></u>")
        tv_privacy_policy.text = text
        tv_privacy_policy.movementMethod = LinkMovementMethod.getInstance()

        btn_sign_in.setOnClickListener {
            NavigationUtils.navigate(view, R.id.action_startFragment_to_loginFragment)
        }
        btn_sign_up.setOnClickListener {
            NavigationUtils.navigate(view, R.id.action_startFragment_to_registerFragment)
        }
    }
}