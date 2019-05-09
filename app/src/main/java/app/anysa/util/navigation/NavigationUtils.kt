package app.anysa.util.navigation


import android.os.Bundle
import android.view.View
import androidx.navigation.NavDirections
import androidx.navigation.Navigation
import app.anysa.util.extensions.logd

object NavigationUtils {

    fun navigate(view: View?, direction: NavDirections) {
        logd("navigate: view: $view direction: $direction");
        try {
            if (view != null) {
                val navController = Navigation.findNavController(view)
                navController.navigate(direction)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    @JvmOverloads
    fun navigate(view: View?, direction: Int, args: Bundle? = null) {
        logd("navigate: view: $view direction: $direction");
        try {
            if (view != null) {
                Navigation.findNavController(view).navigate(direction, args)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
