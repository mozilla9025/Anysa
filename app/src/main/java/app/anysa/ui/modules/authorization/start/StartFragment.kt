package app.anysa.ui.modules.authorization.start

import android.os.Bundle
import android.text.Html
import android.text.method.LinkMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import app.anysa.R
import app.anysa.app.Constants
import app.anysa.helper.locale.OnLanguageSelectedCallback
import app.anysa.helper.preferences.PreferencesManager
import app.anysa.ui.base.BaseFragment
import app.anysa.ui.modules.main.MainActivity
import app.anysa.util.extensions.logd
import app.anysa.util.navigation.NavigationUtils
import kotlinx.android.synthetic.main.fragment_start.*

class StartFragment : BaseFragment() {

    private val viewModel: StartViewModel by lazy {
        ViewModelProviders.of(this).get(StartViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_start, container, false)
    }

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