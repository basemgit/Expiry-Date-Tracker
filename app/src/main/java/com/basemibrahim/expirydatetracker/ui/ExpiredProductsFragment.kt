package com.basemibrahim.expirydatetracker.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import com.basemibrahim.expirydatetracker.databinding.FragmentExpiredProductsBinding
import com.basemibrahim.expirydatetracker.databinding.FragmentHomeBinding
import com.basemibrahim.expirydatetracker.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class ExpiredProductsFragment: Fragment() {
private lateinit var binding: FragmentExpiredProductsBinding
private val mainViewModel : MainViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentExpiredProductsBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.viewModel = mainViewModel
        binding.list.adapter = ExpiredProductsAdapter()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getProducts()
    }

    private fun getProducts()
    {
        binding.pbDog.visibility = View.VISIBLE
        mainViewModel.getExpiredProductsList()
        binding.pbDog.visibility = View.GONE
    }


}