package com.basemibrahim.expirydatetracker.ui

import android.Manifest
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.basemibrahim.expirydatetracker.R
import com.basemibrahim.expirydatetracker.databinding.FragmentScannerBinding
import com.budiyev.android.codescanner.*


class ScannerFragment : Fragment() {
    private lateinit var binding: FragmentScannerBinding
    private lateinit var codeScanner: CodeScanner
    private var permissionGranted = false
    lateinit var navController:NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val navHostFragment = activity?.supportFragmentManager
            ?.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentScannerBinding.inflate(inflater)
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        codeScanner = CodeScanner(requireContext(), binding.scannerView)

        cameraPermission.launch(Manifest.permission.CAMERA)
    }

    private fun scan() {
        codeScanner.apply {
            camera = CodeScanner.CAMERA_BACK
            formats = CodeScanner.ALL_FORMATS
            autoFocusMode = AutoFocusMode.SAFE
            scanMode = ScanMode.CONTINUOUS
            isAutoFocusEnabled = true
            isFlashEnabled = false
            decodeCallback = DecodeCallback {
                requireActivity().runOnUiThread {
                    Toast.makeText(
                        context,
                        it.text,
                        Toast.LENGTH_SHORT
                    ).show()
                    it.text?.let { barcode ->
                        navigateToDetails(barcode)
                    }
                }

            }
            errorCallback = ErrorCallback {
                requireActivity().runOnUiThread {
                    Toast.makeText(
                        context,
                        "Something went wrong, please try again",
                        Toast.LENGTH_SHORT
                    ).show()
                    Log.e("Scanner", "Camera initialization error : ${it.message}")
                }
            }


        }
        codeScanner.startPreview()
    }

    override fun onPause() {
        codeScanner.releaseResources()
        super.onPause()
    }

    override fun onResume() {
        super.onResume()
        if (permissionGranted)
            codeScanner.startPreview()
    }

    private val cameraPermission =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { granted ->
            with(binding.root) {
                when {
                    granted -> {
                        permissionGranted = true
                        scan()
                    }


                    shouldShowRequestPermissionRationale(Manifest.permission.CAMERA) -> {
                        Toast.makeText(
                            requireContext(), resources.getString(R.string.please_grant_permission),
                            Toast.LENGTH_SHORT
                        ).show()
                        permissionGranted = false
                    }
                    else -> {
                        Toast.makeText(
                            requireContext(), resources.getString(R.string.please_grant_permission),
                            Toast.LENGTH_SHORT
                        ).show()
                        permissionGranted = false
                    }
                }
            }
        }

    private fun navigateToDetails(barcode: String) {
        val aciton = ScannerFragmentDirections.actionScannerFragmentToProductDetailsFragment(barcode = barcode)
        navController.navigate(aciton)
    }

}