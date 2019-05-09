package app.anysa.ui.modules.authorization.start

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import app.anysa.R
import app.anysa.helper.preferences.PreferencesManager
import app.anysa.ui.base.BaseFragment

class StartFragment : BaseFragment() {

    private val viewModel: StartViewModel by lazy {
        ViewModelProviders.of(this).get(StartViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_start, container, false)
    }
}