package com.basemibrahim.expirydatetracker.data

import dagger.hilt.android.scopes.ActivityRetainedScoped
import javax.inject.Inject

@ActivityRetainedScoped
class Repository @Inject constructor(
    private val localDataSource: LocalDataSource
) {
    suspend fun insertProduct(product: Product) {
        localDataSource.insertProduct(product)
    }


}