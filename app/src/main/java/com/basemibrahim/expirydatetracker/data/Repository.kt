package com.basemibrahim.expirydatetracker.data

import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

@ActivityRetainedScoped
class Repository @Inject constructor(
    private val localDataSource: LocalDataSource
) {
    suspend fun insertProduct(product: Product) {
        localDataSource.insertProduct(product)
    }
    suspend fun deleteProduct(product: Product) {
        localDataSource.deleteProduct(product)
    }

    suspend fun getProducts() : Flow<List<Product>>
    {
      return  localDataSource.getProducts()
    }


}