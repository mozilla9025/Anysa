package app.anysa.ui.modules.authorization.register

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import app.anysa.R
import app.anysa.ui.base.BaseFragment
import app.anysa.ui.widget.expandable_layout.ExpandableLayout
import app.anysa.util.app_bar.AppBarStateChangeListener
import app.anysa.util.navigation.NavigationUtils
import com.google.android.material.appbar.AppBarLayout
import kotlinx.android.synthetic.main.fragment_register.*


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
                    collapse()
                }
                ExpandableLayout.State.EXPANDED -> {
                    expand()
                }
            }
        }

        collapse()

        tv_advanced_data.setOnClickListener {
            el_advanced_data.toggle(false)
        }

        app_bar.addOnOffsetChangedListener(object : AppBarStateChangeListener() {
            override fun onStateChanged(appBarLayout: AppBarLayout, state: State) {
                when (state) {
                    State.COLLAPSED -> view_appbar_shadow.visibility = View.VISIBLE
                    else -> view_appbar_shadow.visibility = View.GONE
                }
            }
        })


        tv_sign_in.setOnClickListener({
            NavigationUtils.navigate(view,
                    RegisterFragmentDirections.actionRegisterFragmentToLoginFragment())
        })
    }

    private fun collapse() {
        tv_advanced_data.setText(R.string.register_fragment_btn_show_advanced_data)

        app_bar.setExpanded(true, false)
        val p = toolbar_layout.layoutParams as AppBarLayout.LayoutParams
        p.scrollFlags = 0
        toolbar_layout.layoutParams = p

        cl_footer.visibility = View.VISIBLE
    }

    private fun expand() {
        tv_advanced_data.setText(R.string.register_fragment_btn_hide_advanced_data)
        val p = toolbar_layout.layoutParams as AppBarLayout.LayoutParams
        p.scrollFlags = AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL or AppBarLayout.LayoutParams.SCROLL_FLAG_EXIT_UNTIL_COLLAPSED
        toolbar_layout.layoutParams = p

        cl_footer.visibility = View.GONE
    }

}