

package com.basemibrahim.expirydatetracker.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
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

    suspend fun getProducts() : Flow<List<Product>> {
        var products : Flow<List<Product>>
        withContext(Dispatchers.IO)
        {
            products = productDao.getProducts()
        }
        return products
    }




}
