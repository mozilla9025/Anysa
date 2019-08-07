package app.anysa.util.navigation


import android.os.Bundle
import android.view.View
import androidx.annotation.IdRes
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.NavOptions
import androidx.navigation.Navigation
import app.anysa.R
import app.anysa.util.extensions.logd
import app.anysa.util.extensions.loge
import com.google.android.material.bottomnavigation.BottomNavigationView

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

    private fun onNavDestinationSelected(@IdRes fragmentId: Int, navController: NavController,
                                         arguments: Bundle? = null): Boolean {

        val builder = NavOptions.Builder().setLaunchSingleTop(true)
                .setEnterAnim(R.anim.nav_enter_anim)
                .setExitAnim(R.anim.nav_exit_anim)
                .setPopEnterAnim(R.anim.nav_pop_enter_anim)
                .setPopExitAnim(R.anim.nav_pop_exit_anim)

        val options = builder.build()
        try {
            navController.navigate(fragmentId, arguments, options)
            return true
        } catch (var6: IllegalArgumentException) {
            return false
        }

    }

    fun setupWithNavController(bottomNavigationView: BottomNavigationView,
                               navController: NavController) {
        bottomNavigationView.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.menu_dialogsFragment -> {
                    onNavDestinationSelected(R.id.chatsNavFragment, navController)
                }
                R.id.menu_groupFragment -> {
                    false
                }
                R.id.menu_contactsFragment -> {
                    onNavDestinationSelected(R.id.contactsNavFragment, navController)
                }
                R.id.menu_applicationFragment -> {
                    false
                }
                R.id.menu_profileFragment -> {
                    onNavDestinationSelected(R.id.profileNavFragment, navController)
                }
                else -> {
                    false
                }
            }
        }

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.contactsNavFragment -> {
                    bottomNavigationView.selectedItemId =  R.id.menu_contactsFragment
                }
                R.id.profileNavFragment  -> {
                    bottomNavigationView.selectedItemId =  R.id.menu_profileFragment
                }
                else -> {
                    loge("noDestinationId found")
                }
            }
        }
    }
}
