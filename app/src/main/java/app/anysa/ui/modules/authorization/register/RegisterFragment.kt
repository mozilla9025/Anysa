package app.anysa.ui.modules.authorization.register

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver.OnGlobalLayoutListener
import androidx.core.view.ViewCompat
import androidx.lifecycle.ViewModelProviders
import app.anysa.R
import app.anysa.ui.base.BaseFragment
import app.anysa.ui.widget.expandable_layout.ExpandableLayout
import app.anysa.util.extensions.logd
import com.google.android.material.appbar.AppBarLayout
import kotlinx.android.synthetic.main.fragment_register.*
import kotlin.math.abs

class RegisterFragment : BaseFragment() {

    private val viewModel: RegisterViewModel by lazy {
        ViewModelProviders.of(this).get(RegisterViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_register, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        tv_advanced_data.setOnClickListener {
            el_advanced_data.toggle(false)
        }

        val onOffsetChangedListener = AppBarLayout.OnOffsetChangedListener { appBarLayout, verticalOffset ->
            updateFooterPadding(verticalOffset)
            if (verticalOffset == toolbar.height - appBarLayout.height) {
                ViewCompat.setElevation(appBarLayout, 4f)
            } else {
                ViewCompat.setElevation(appBarLayout, 0f)
            }
        }
        disableAppBar()

        el_advanced_data.setOnExpansionUpdateListener { expansionFraction, state ->
            when (state) {
                ExpandableLayout.State.COLLAPSED -> {
                    tv_advanced_data.setText(R.string.register_fragment_btn_show_advanced_data)
                    disableAppBar()
                    app_bar.removeOnOffsetChangedListener(onOffsetChangedListener)
                }
                ExpandableLayout.State.EXPANDED -> {
                    tv_advanced_data.setText(R.string.register_fragment_btn_hide_advanced_data)
                    enableAppBar()
                    app_bar.addOnOffsetChangedListener(onOffsetChangedListener)
                }
            }
        }
    }


    private fun disableAppBar() {
        app_bar.setExpanded(true, false)
        val p = toolbar_layout.layoutParams as AppBarLayout.LayoutParams
        p.scrollFlags = 0
        toolbar_layout.layoutParams = p
    }

    private fun enableAppBar() {
        val p = toolbar_layout.layoutParams as AppBarLayout.LayoutParams
        p.scrollFlags = AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL or AppBarLayout.LayoutParams.SCROLL_FLAG_EXIT_UNTIL_COLLAPSED
        toolbar_layout.layoutParams = p
    }

    private fun updateFooterPadding(verticalOffset: Int) {
        val tbHeight = toolbar.height
        val maxOffset = app_bar.height - tbHeight
        try {
            val percent = verticalOffset * 100 / maxOffset
            val paddingBottom = tbHeight - abs(tbHeight * percent / 100)
            cl_footer.setPadding(cl_footer.paddingLeft, cl_footer.paddingTop, cl_footer.paddingRight, 200)
            logd("onOffsetChanged: $verticalOffset  ${app_bar.height}  $tbHeight ${cl_footer.paddingBottom}  $paddingBottom");
        } catch (ignored: Exception) {

        }

    }
}