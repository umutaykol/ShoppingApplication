package com.umut.shoppingapplication.ui.fragments

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import com.umut.shoppingapplication.R
import com.umut.shoppingapplication.databinding.FragmentShoppingBinding
import com.umut.shoppingapplication.ui.viewmodels.ShoppingCartFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ShoppingFragment : Fragment() {

    private lateinit var appBarConfiguration: AppBarConfiguration

    lateinit var binding: FragmentShoppingBinding
    val shoppingCartFragmentViewModel: ShoppingCartFragmentViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentShoppingBinding.inflate(inflater, container, false)

        setHasOptionsMenu(true)

        (activity as AppCompatActivity).setSupportActionBar(binding.shoppingToolbar)

        configureListeners()
        configureNavigationMenu()



//        (activity as AppCompatActivity?)!!.supportActionBar?.setSubtitle(R.string.subtitle)
//
//        val drawerLayout: DrawerLayout = binding.shoppingDrawerLayout
//        val navView: NavigationView = binding.mainNavigationView
//        val navController = findNavController(this)
//
//        appBarConfiguration = AppBarConfiguration(
//            setOf(
//                R.id.nav_orders
//            ), drawerLayout
//        )
//        setupActionBarWithNavController(activity as AppCompatActivity, navController, appBarConfiguration)
//        navView.setupWithNavController(navController)

        return binding.root
    }

    private fun configureNavigationMenu() {
        val mToggle = ActionBarDrawerToggle(
            activity, binding.shoppingDrawerLayout, binding.shoppingToolbar, R.string.open, R.string.close
        )
        binding.shoppingDrawerLayout.addDrawerListener(mToggle)
        mToggle.syncState()

//        NavigationMenuController.navItemClickListener(activity, binding)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.action_bar_menu, menu)
        super.onCreateOptionsMenu(menu,inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.shopping_cart -> {
                navigateToShoppingCart()
                return true
            }
            else -> {
                return true
            }
        }
    }

    private fun configureListeners() {
        configureClickListeners()
    }

    private fun configureClickListeners() {
//        binding.button.setOnClickListener {
//            navigateToShoppingCart()
//        }
    }

    fun navigateToShoppingCart() {
        Navigation.findNavController(
            requireActivity(), R.id.main_fragment_container_view
        ).navigate(R.id.action_shoppingFragment_to_shoppingCartFragment)

    }


}