package com.yoghi.moviecatalog.di

import com.yoghi.moviecatalog.data.source.CatalogRepository
import com.yoghi.moviecatalog.data.source.remote.RemoteDataSource

object Injection {
    fun provideCatalogRepository(): CatalogRepository {
        val remoteDataSource = RemoteDataSource.getInstance()
        return CatalogRepository.getInstance(remoteDataSource)
    }
}