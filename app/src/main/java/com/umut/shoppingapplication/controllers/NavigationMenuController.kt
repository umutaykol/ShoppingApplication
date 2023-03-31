package com.umut.shoppingapplication.controllers

import android.content.Context
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.GravityCompat
import com.google.android.material.switchmaterial.SwitchMaterial
import com.umut.shoppingapplication.R
import com.umut.shoppingapplication.databinding.FragmentShoppingBinding

class NavigationMenuController {

    companion object {

        // Function for Handling Nav Items Clicks
        fun navItemClickListener(
            context: Context, binding: FragmentShoppingBinding
        ) {
//            val switchMaterial: SwitchMaterial = setDarkThemeMenuItem(
//                context, binding.mainNavigationView.menu.findItem(R.id.action_theme)
//            )

            binding.shoppingNavigationView.setNavigationItemSelectedListener {

                when (it.itemId) {
                    R.id.nav_orders -> {
                        Toast.makeText(context, "CLICKED", Toast.LENGTH_LONG).show()
//                        switchMaterial.isChecked = !switchMaterial.isChecked
                    }
                }

                if (it.itemId != R.id.nav_orders) binding.shoppingDrawerLayout.closeDrawer(GravityCompat.START)

                true
            }
        }

        // Function for Handling Dark Theme Menu Item
//        private fun setDarkThemeMenuItem(context: Context?, menuItem: MenuItem): SwitchMaterial {
//            menuItem.actionView = SwitchMaterial(context!!)
//            val switchMaterial = menuItem.actionView as SwitchMaterial
//
//            // Last saved state of dark theme
//            val isDarkEnabled = Preferences.getThemeState(context)
//
//            // Apply changes
//            switchMaterial.isChecked = isDarkEnabled
//            AppCompatDelegate.setDefaultNightMode(
//                if (isDarkEnabled) AppCompatDelegate.MODE_NIGHT_YES else AppCompatDelegate.MODE_NIGHT_NO
//            )
//
//            // Dark Theme Switch Listener
//            switchMaterial.setOnCheckedChangeListener { _, b: Boolean ->
//                AppCompatDelegate.setDefaultNightMode(
//                    if (b) AppCompatDelegate.MODE_NIGHT_YES else AppCompatDelegate.MODE_NIGHT_NO
//                )
//
//                // Saving theme state
//                Preferences.saveThemeState(context, b)
//            }
//            return switchMaterial
//        }

    }

}