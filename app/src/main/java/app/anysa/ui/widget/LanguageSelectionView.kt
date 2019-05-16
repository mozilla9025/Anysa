package app.anysa.ui.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import app.anysa.R
import app.anysa.helper.dialog.DialogHelper
import app.anysa.helper.dialog.DialogInteractorCallback
import app.anysa.helper.locale.ApplicationLanguage
import app.anysa.helper.locale.LocaleManager
import app.anysa.helper.locale.OnLanguageSelectedCallback
import app.anysa.ui.modules.main.MainActivity
import app.anysa.util.extensions.logd
import kotlinx.android.synthetic.main.view_language_selection.view.*

class LanguageSelectionView : ConstraintLayout {

    var onLanguageSelectedCallback: OnLanguageSelectedCallback? = null

    @JvmOverloads
    constructor(context: Context, attributes: AttributeSet? = null, defStyleAttr: Int = 0)
            : super(context, attributes, defStyleAttr) {

        View.inflate(context, R.layout.view_language_selection, this)


        val callback = object : DialogInteractorCallback {
            override fun onPositiveButtonClick() {
                logd("onPositiveButtonClick: ");
                onLanguageSelectedCallback?.onLanguageChanged()
            }

            override fun onNegativeButtonClick() {
            }
        }


        iv_flag_cn.setOnClickListener {
            if (LocaleManager.setNewLocale(context, ApplicationLanguage.CN.languageCode)) {
                DialogHelper.showChangeLanguageRestartDialog(context, object : DialogInteractorCallback {
                    override fun onPositiveButtonClick() {
                        logd("onPositiveButtonClick: ");
                        MainActivity.start(context)
                    }

                    override fun onNegativeButtonClick() {
                    }
                })
            }
        }
        iv_flag_gb.setOnClickListener({
            if (LocaleManager.setNewLocale(context, ApplicationLanguage.EN.languageCode)) {
                DialogHelper.showChangeLanguageRestartDialog(context, object : DialogInteractorCallback {
                    override fun onPositiveButtonClick() {
                        logd("onPositiveButtonClick: ");
                        MainActivity.start(context)
                    }

                    override fun onNegativeButtonClick() {
                    }
                })
            }
        })
    }
}