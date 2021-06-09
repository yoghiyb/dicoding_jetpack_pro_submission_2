package com.yoghi.moviecatalog.data.source

import androidx.lifecycle.LiveData
import com.yoghi.moviecatalog.data.DataModel

interface CatalogDataSource {
    fun getNowPlayingMovies(): LiveData<List<DataModel>>

    fun getMovieDetail(movieId: Int): LiveData<DataModel>

    fun getTvShowOnTheAir(): LiveData<List<DataModel>>

    fun getTvShowDetail(tvShowId: Int): LiveData<DataModel>
}