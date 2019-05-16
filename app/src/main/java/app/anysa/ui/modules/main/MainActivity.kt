package app.anysa.ui.modules.main


import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment

import java.util.ArrayList

import app.anysa.R
import app.anysa.ui.base.BaseActivity
import app.anysa.util.extensions.logd

class MainActivity : BaseActivity() {

    val TAG: String = javaClass.simpleName


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        logd("onCreate")
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
    }

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
