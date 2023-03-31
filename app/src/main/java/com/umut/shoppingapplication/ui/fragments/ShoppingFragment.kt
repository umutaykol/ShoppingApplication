package com.umut.shoppingapplication.ui.fragments

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.umut.shoppingapplication.R
import com.umut.shoppingapplication.databinding.FragmentShoppingBinding
import com.umut.shoppingapplication.ui.viewmodels.ShoppingCartFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ShoppingFragment : Fragment() {

    lateinit var binding: FragmentShoppingBinding
    val shoppingCartFragmentViewModel: ShoppingCartFragmentViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentShoppingBinding.inflate(inflater, container, false)

        setHasOptionsMenu(true)
        (activity as AppCompatActivity).setSupportActionBar(binding.toolbar.root)

        configureListeners()
        configureNavigationDrawer()
        // This should call after Navigation Drawer Configured
        configureNavigationItemSelected()

        return binding.root
    }

    private fun configureNavigationDrawer() {
        val mToggle = ActionBarDrawerToggle(
            activity, binding.shoppingDrawerLayout, binding.toolbar.root, R.string.open, R.string.close
        )
        binding.shoppingDrawerLayout.addDrawerListener(mToggle)
        mToggle.syncState()
    }

    private fun configureNavigationItemSelected() {
        binding.shoppingNavigationView.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.nav_orders -> {
                    Toast.makeText(requireContext(), "CLICKED", Toast.LENGTH_LONG).show()
                    navigateToOrders()
                }
            }

            if (it.itemId != R.id.nav_orders) binding.shoppingDrawerLayout.closeDrawer(GravityCompat.START)

            true
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.action_bar_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
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

    private fun navigateToShoppingCart() {
        Navigation.findNavController(
            requireActivity(), R.id.main_fragment_container_view
        ).navigate(R.id.action_shoppingFragment_to_shoppingCartFragment)
    }

    private fun navigateToOrders() {
        Navigation.findNavController(
            requireActivity(), R.id.main_fragment_container_view
        ).navigate(R.id.action_shoppingFragment_to_ordersFragment)
    }

}