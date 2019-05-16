package app.anysa.ui.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import app.anysa.R
import app.anysa.helper.locale.ApplicationLanguage
import app.anysa.helper.locale.LocaleManager
import app.anysa.helper.locale.OnLanguageSelectedCallback
import kotlinx.android.synthetic.main.view_language_selection.view.*

class LanguageSelectionView : ConstraintLayout {

    var onLanguageSelectedCallback: OnLanguageSelectedCallback? = null

    @JvmOverloads
    constructor(context: Context, attributes: AttributeSet? = null, defStyleAttr: Int = 0)
            : super(context, attributes, defStyleAttr) {

        View.inflate(context, R.layout.view_language_selection, this)

        iv_flag_cn.setOnClickListener({
            LocaleManager.setNewLocale(context, ApplicationLanguage.CN.languageCode)
            onLanguageSelectedCallback?.onLanguageChanged()
        })
        iv_flag_gb.setOnClickListener({
            LocaleManager.setNewLocale(context, ApplicationLanguage.EN.languageCode)
            onLanguageSelectedCallback?.onLanguageChanged()
        })
    }
}