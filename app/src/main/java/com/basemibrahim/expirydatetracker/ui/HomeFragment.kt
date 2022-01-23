package com.basemibrahim.expirydatetracker.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import androidx.work.Data
import com.basemibrahim.expirydatetracker.databinding.FragmentHomeBinding
import com.basemibrahim.expirydatetracker.utils.Constants.NOTIFICATION_ID
import com.basemibrahim.expirydatetracker.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class HomeFragment: Fragment() {
private lateinit var binding: FragmentHomeBinding
private val mainViewModel : MainViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val callback = requireActivity().onBackPressedDispatcher.addCallback(this) {
            requireActivity().finish()
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.viewModel = mainViewModel
        binding.list.adapter = ProductsAdapter()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.fab.setOnClickListener {
            val aciton = HomeFragmentDirections.actionHomeFragmentToScannerFragment()
            binding.root.findNavController().navigate(aciton)
        }
        binding.checkExpiredItems.setOnClickListener {
            val aciton = HomeFragmentDirections.actionHomeFragmentToExpiredProductsFragment()
            binding.root.findNavController().navigate(aciton)
        }
    }

    override fun onResume() {
        super.onResume()
        getProducts()

    }
    private fun getProducts()
    {
        binding.pbDog.visibility = View.VISIBLE
        mainViewModel.getProductsList()
        checkExpiration()
        binding.pbDog.visibility = View.GONE
    }
    private fun checkExpiration()
    {
        mainViewModel.products.observe(viewLifecycleOwner){
            for(product in it)
            {
                if(Date().after(product.expiryDate))
                {
                    mainViewModel.deleteProduct(product)
                    mainViewModel.insertExpiredProduct(product)
                }
                setupNotification(product.expiryDate)
            }
        }

    }

    private fun setupNotification(expiryDate: Date)
    {
        mainViewModel.sendNotification(expiryDate)
    }

}