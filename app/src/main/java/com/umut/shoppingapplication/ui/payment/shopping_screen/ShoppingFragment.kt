package com.umut.shoppingapplication.ui.payment.shopping_screen

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.umut.shoppingapplication.R
import com.umut.shoppingapplication.adapters.products.NoteItemClickListener
import com.umut.shoppingapplication.adapters.products.ProductsRecyclerViewAdapter
import com.umut.shoppingapplication.databinding.FragmentShoppingBinding
import com.umut.shoppingapplication.models.Product
import com.umut.shoppingapplication.utils.Constants.amount
import com.umut.shoppingapplication.utils.showShortToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ShoppingFragment : Fragment(), NoteItemClickListener {

    private val productsRecyclerViewAdapter = ProductsRecyclerViewAdapter(this)

    lateinit var binding: FragmentShoppingBinding

    val shoppingFragmentViewModel: ShoppingFragmentViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentShoppingBinding.inflate(inflater, container, false)

        configureOptionMenuAndActionBarSupporting()
        configureNavigationDrawer()
        // This should call after Navigation Drawer Configured
        configureNavigationItemSelected()

        configureProductsRecyclerView()
        observeLiveDataChanges()

        shoppingFragmentViewModel.getAllProductsFromDB()

        return binding.root
    }

    private fun observeLiveDataChanges() {
        with(shoppingFragmentViewModel) {
            productsLiveData.observe(viewLifecycleOwner) {
                if (it?.size != 5) {
                    shoppingFragmentViewModel.addAllDummyProductsToDB()
                } else {
                    addProductsToRecyclerView(it)
                }
            }
        }
    }

    private fun addProductsToRecyclerView(products: MutableList<Product>) {
        productsRecyclerViewAdapter.differ.submitList(products)
        productsRecyclerViewAdapter.notifyDataSetChanged()
    }

    private fun configureOptionMenuAndActionBarSupporting() {
        setHasOptionsMenu(true)
        (activity as AppCompatActivity).setSupportActionBar(binding.toolbar.root)

    }

    private fun configureProductsRecyclerView() {
        binding.productsRecyclerView.adapter = productsRecyclerViewAdapter
        binding.productsRecyclerView.layoutManager = LinearLayoutManager(
            requireContext(),
            LinearLayoutManager.VERTICAL,
            false
        )
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
                true
            }
            else -> {
                true
            }
        }
    }

    private fun navigateToShoppingCart() {
        val bundle = bundleOf()
        bundle.putFloat(amount, shoppingFragmentViewModel.getFullAmount())

        Navigation.findNavController(
            requireActivity(), R.id.main_fragment_container_view
        ).navigate(R.id.action_shoppingFragment_to_shoppingCartandPaymentFragment, bundle)
    }

    private fun navigateToOrders() {
        Navigation.findNavController(
            requireActivity(), R.id.main_fragment_container_view
        ).navigate(R.id.action_shoppingFragment_to_ordersFragment)
    }

    override fun addProductClicked(view: View, product: Product, position: Int) {
        productAddedToCart(product, position)
    }

    override fun subProductClicked(view: View, product: Product, position: Int) {
        productSubtractedFromCart(product, position)
    }

    private fun productAddedToCart(product: Product, position: Int) {
        var productCount = productsRecyclerViewAdapter.differ.currentList[position].productCount
        if (productCount in 0..4) {
            productsRecyclerViewAdapter.differ.currentList[position].productCount += 1
            productsRecyclerViewAdapter.notifyItemChanged(position)

            shoppingFragmentViewModel.updateProductToDB(productsRecyclerViewAdapter.differ.currentList[position])
        } else {
            showShortToast(requireContext(), "Aynı üründen sepete 5'ten fazla ekleyemezsiniz.")
        }
    }

    private fun productSubtractedFromCart(product: Product, position: Int) {
        var productCount = productsRecyclerViewAdapter.differ.currentList[position].productCount
        if (productCount in 1..5) {
            productsRecyclerViewAdapter.differ.currentList[position].productCount -= 1
            productsRecyclerViewAdapter.notifyItemChanged(position)

            shoppingFragmentViewModel.updateProductToDB(productsRecyclerViewAdapter.differ.currentList[position])
        } else {
            showShortToast(requireContext(), "Sepetinizde ürün bulunmamaktadır.")
        }

    }

}