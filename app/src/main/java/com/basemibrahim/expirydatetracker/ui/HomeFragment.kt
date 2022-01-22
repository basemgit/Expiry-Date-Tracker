package com.basemibrahim.expirydatetracker.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import com.basemibrahim.expirydatetracker.databinding.FragmentHomeBinding
import com.basemibrahim.expirydatetracker.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment: Fragment() {
private lateinit var binding: FragmentHomeBinding
private val mainViewModel : MainViewModel by activityViewModels()
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
        getProducts()
    }

    fun getProducts()
    {
        binding.pbDog.visibility = View.VISIBLE
        mainViewModel.getProductsList()
        binding.pbDog.visibility = View.GONE
    }

}