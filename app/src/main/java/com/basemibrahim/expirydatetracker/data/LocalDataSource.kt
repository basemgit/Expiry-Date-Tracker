

package com.basemibrahim.expirydatetracker.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class LocalDataSource @Inject constructor(private val productDao: ProductDao) {

    suspend  fun insertProduct (product: Product) {
       withContext(Dispatchers.IO)
       {
           productDao.insert(product)
       }
    }




}
