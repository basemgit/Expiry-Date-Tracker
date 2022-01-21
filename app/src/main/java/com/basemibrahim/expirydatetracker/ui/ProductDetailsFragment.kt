package com.basemibrahim.expirydatetracker.ui

import android.app.DatePickerDialog
import android.os.Bundle
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import androidx.fragment.app.Fragment
import com.basemibrahim.expirydatetracker.R
import com.basemibrahim.expirydatetracker.databinding.FragmentProductDetailsBinding
import com.basemibrahim.expirydatetracker.utils.Constants.BARCODE
import java.util.*

class ProductDetailsFragment : Fragment() {
    private lateinit var binding: FragmentProductDetailsBinding
    private lateinit var barcode: String
    private lateinit var picker: DatePicker

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            barcode = it.getString(BARCODE).toString()

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
        handleInput()
    }

    private fun handleInput() {
        binding.dateET.setOnClickListener {
            val cldr: Calendar = Calendar.getInstance()
            val day: Int = cldr.get(Calendar.DAY_OF_MONTH)
            val month: Int = cldr.get(Calendar.MONTH)
            val year: Int = cldr.get(Calendar.YEAR)

            val dpd = DatePickerDialog(
                requireContext(),
                DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                    binding.dateET.setText(
                        dayOfMonth.toString().plus("/").plus(monthOfYear + 1).plus("/").plus(year)
                    )

                },
                year,
                month,
                day
            )

            dpd.show()


        }
    }
}