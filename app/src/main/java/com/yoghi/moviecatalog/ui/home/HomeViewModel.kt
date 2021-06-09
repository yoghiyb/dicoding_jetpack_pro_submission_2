package com.yoghi.moviecatalog.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.yoghi.moviecatalog.data.DataModel
import com.yoghi.moviecatalog.data.source.CatalogRepository

class HomeViewModel(private val catalogRepository: CatalogRepository): ViewModel() {
    fun getListNowPlayingMovies(): LiveData<List<DataModel>> = catalogRepository.getNowPlayingMovies()

    fun getListOnTheAirTvShows(): LiveData<List<DataModel>> = catalogRepository.getTvShowOnTheAir()
}