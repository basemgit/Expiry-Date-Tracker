package com.basemibrahim.expirydatetracker.ui

import android.app.DatePickerDialog
import android.os.Bundle
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import com.basemibrahim.expirydatetracker.R
import com.basemibrahim.expirydatetracker.data.Product
import com.basemibrahim.expirydatetracker.databinding.FragmentProductDetailsBinding
import com.basemibrahim.expirydatetracker.utils.Constants.BARCODE
import com.basemibrahim.expirydatetracker.utils.Utils
import com.basemibrahim.expirydatetracker.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class ProductDetailsFragment : Fragment() {
    private val mainViewModel: MainViewModel by activityViewModels()
    private lateinit var binding: FragmentProductDetailsBinding
    private lateinit var barcode: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            barcode = it.getString(BARCODE).toString()
        }
        val callback = requireActivity().onBackPressedDispatcher.addCallback(this) {
           navigateToHome()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProductDetailsBinding.inflate(inflater)
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.barcode.text = getString(R.string.product_barcode).plus(" : ").plus(barcode)
        binding.dateET.inputType = InputType.TYPE_NULL
        inputData()
        handleSaveBtn()
    }

    private fun inputData() {
        binding.pickDate.setOnClickListener {
            val cldr: Calendar = Calendar.getInstance()
            val day: Int = cldr.get(Calendar.DAY_OF_MONTH)
            val month: Int = cldr.get(Calendar.MONTH)
            val year: Int = cldr.get(Calendar.YEAR)

            val dpd = DatePickerDialog(
                requireContext(),
                { view, year, monthOfYear, dayOfMonth ->
                    binding.dateET.setText(
                        dayOfMonth.toString().plus("-").plus(monthOfYear + 1).plus("-").plus(year)
                    )

                },
                year,
                month,
                day
            )

            dpd.show()


        }
    }

    fun handleSaveBtn() {
        binding.saveProduct.setOnClickListener {
            if (isEntryValid()) {
                val newProduct =
                    Utils.stringToDate(binding.dateET.text.toString(), "dd-MM-yyyy")?.let { date ->
                        Product(
                            productName = binding.productNameET.text.toString(),
                            productType = binding.productTypeET.text.toString(),
                            expiryDate = date
                        )
                    }
                if (newProduct != null) {
                    mainViewModel.insertItem(newProduct)
                    navigateToHome()
                }
            } else {
                Toast.makeText(requireContext(),getString(R.string.fill_fields), Toast.LENGTH_SHORT).show()
            }

        }
    }

    private fun isEntryValid(): Boolean {
        return mainViewModel.isEntryValid(
            binding.productNameET.text.toString(),
            binding.productTypeET.text.toString(),
            Utils.stringToDate(binding.dateET.text.toString(), "dd-MM-yyyy")
        )
    }

    private fun navigateToHome()
    {
        val aciton = ProductDetailsFragmentDirections.actionProductDetailsFragmentToHomeFragment()
        binding.root.findNavController().navigate(aciton)
    }

}