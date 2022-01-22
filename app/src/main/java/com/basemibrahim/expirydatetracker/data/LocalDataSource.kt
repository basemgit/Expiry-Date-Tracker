

package com.basemibrahim.expirydatetracker.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class LocalDataSource @Inject constructor(private val productDao: ProductDao,
private val expiredProductDao: ExpiredProductDao) {

    suspend  fun insertProduct (product: Product) {
       withContext(Dispatchers.IO)
       {
           productDao.insert(product)
       }
    }

    suspend  fun insertExpiredProduct (expiredProduct: ExpiredProduct) {
       withContext(Dispatchers.IO)
       {
           expiredProductDao.insertExpiredProduct(expiredProduct)
       }
    }

    suspend  fun deleteProduct (product: Product) {
        withContext(Dispatchers.IO)
        {
            productDao.delete(product)
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

    suspend fun getExpiredProducts() : Flow<List<ExpiredProduct>> {
        var expiredProducts : Flow<List<ExpiredProduct>>
        withContext(Dispatchers.IO)
        {
            expiredProducts = expiredProductDao.getExpiredProducts()
        }
        return expiredProducts
    }




}
