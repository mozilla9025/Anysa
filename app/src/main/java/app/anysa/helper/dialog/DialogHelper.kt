package app.anysa.helper.dialog

import android.content.Context
import android.content.DialogInterface
import androidx.appcompat.app.AlertDialog

class DialogHelper {

    companion object {
        fun showChangeLanguageRestartDialog(from: Context, callback: DialogInteractorCallback) {
            AlertDialog.Builder(from)
                    .setCancelable(false)
                    .setTitle("Change language")
                    .setMessage("Changing language requires restarting of the application")
                    .setPositiveButton("Ok", { dialogInterface: DialogInterface, i: Int ->
                        dialogInterface.dismiss()
                        callback.onPositiveButtonClick()
                    })
                    .create()
                    .show()
        }
    }
}