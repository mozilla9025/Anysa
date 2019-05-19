package app.anysa.ui.modules.authorization.register

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
import app.anysa.ui.widget.expandable_layout.ExpandableLayout
import app.anysa.util.extensions.logd
import kotlinx.android.synthetic.main.fragment_register.*
import kotlinx.android.synthetic.main.fragment_start.*

class RegisterFragment : BaseFragment() {

    private val viewModel: RegisterViewModel by lazy {
        ViewModelProviders.of(this).get(RegisterViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_register, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        el_advanced_data.setOnExpansionUpdateListener { expansionFraction, state ->
            when (state) {
                ExpandableLayout.State.COLLAPSED -> {
                    tv_advanced_data.setText(R.string.register_fragment_btn_show_advanced_data)
                }
                ExpandableLayout.State.EXPANDED -> {
                    tv_advanced_data.setText(R.string.register_fragment_btn_hide_advanced_data)
                }
            }
        }
        tv_advanced_data.setOnClickListener({
            el_advanced_data.toggle(false)
        })
    }
}