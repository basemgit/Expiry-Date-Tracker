package com.basemibrahim.expirydatetracker.viewmodel

import android.app.AlarmManager
import android.app.Application
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.core.app.AlarmManagerCompat
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.basemibrahim.expirydatetracker.data.ExpiredProduct
import com.basemibrahim.expirydatetracker.data.Product
import com.basemibrahim.expirydatetracker.data.Repository
import com.basemibrahim.expirydatetracker.data.toExpiredProduct
import com.basemibrahim.expirydatetracker.receiver.AlarmReceiver
import com.basemibrahim.expirydatetracker.utils.Constants.REQUEST_CODE
import com.basemibrahim.expirydatetracker.utils.Utils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor
    (
    private val repository: Repository,
    application: Application
) : AndroidViewModel(application) {

    private val _products: MutableLiveData<List<Product>> = MutableLiveData()
    val products: LiveData<List<Product>> = _products
    private val _expiredProducts: MutableLiveData<List<ExpiredProduct>> = MutableLiveData()
    val expiredProdcuts: LiveData<List<ExpiredProduct>> = _expiredProducts
    val notifyPendingIntent: PendingIntent
    val alarmManager = application.getSystemService(Context.ALARM_SERVICE) as AlarmManager
    private val notifyIntent = Intent(application, AlarmReceiver::class.java)
    var alarmExpiryTime : Long = 0
    private var prefs =
        application.getSharedPreferences("prefs", Context.MODE_PRIVATE)
    init {

            notifyPendingIntent = PendingIntent.getBroadcast(
            getApplication(),
            REQUEST_CODE,
            notifyIntent,
            PendingIntent.FLAG_UPDATE_CURRENT)
    }


    fun insertItem(product: Product) = viewModelScope.launch {
        repository.insertProduct(product)
    }

    fun insertExpiredProduct(product: Product) = viewModelScope.launch {
        val expiredProduct = product.toExpiredProduct()
        repository.insertExpiredProduct(expiredProduct)
    }

    fun deleteProduct(product: Product) = viewModelScope.launch {
        repository.deleteProduct(product)
    }

    fun isEntryValid(productName: String, productType: String, expiryDate: Date?): Boolean {
        if (productName.isBlank() || productType.isBlank() || expiryDate == null) {
            return false
        }
        return true
    }


    fun getProductsList() = viewModelScope.launch {
        repository.getProducts().collect {
            _products.value = it
        }
    }

    fun getExpiredProductsList() = viewModelScope.launch {
        repository.getExpiredProducts().collect {
            _expiredProducts.value = it
        }
    }

     fun sendNotification(expiryDate: Date) {
         alarmExpiryTime = expiryDate.time
         Utils.saveAlarmData(getApplication(),alarmExpiryTime)
         AlarmManagerCompat.setExactAndAllowWhileIdle(
             alarmManager,
             AlarmManager.RTC_WAKEUP,
             alarmExpiryTime,
             notifyPendingIntent
         )

    }
}