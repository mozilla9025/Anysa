package app.anysa.ui.modules


import android.content.Context
import android.content.Intent
import app.anysa.R
import app.anysa.databinding.ActivityMainBinding
import app.anysa.ui.base.abs.AbsActivity
import app.anysa.util.annotation.RequiresView
import app.anysa.util.annotation.RequiresViewModel

@RequiresView(R.layout.activity_main)
@RequiresViewModel(MainActivityViewModel::class)
class MainActivity : AbsActivity<ActivityMainBinding, MainActivityViewModel>() {

    companion object {
        fun start(from: Context) {
            val intent = Intent(from, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or
                    Intent.FLAG_ACTIVITY_REORDER_TO_FRONT or
                    Intent.FLAG_ACTIVITY_CLEAR_TOP or
                    Intent.FLAG_ACTIVITY_SINGLE_TOP

            if (from is MainActivity)
                from.finish()

            from.startActivity(intent)
        }
    }
}
