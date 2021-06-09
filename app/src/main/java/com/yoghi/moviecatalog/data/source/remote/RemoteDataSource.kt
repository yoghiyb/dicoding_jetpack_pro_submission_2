package com.yoghi.moviecatalog.data.source.remote

import com.yoghi.moviecatalog.data.source.remote.api.ApiClient
import com.yoghi.moviecatalog.data.source.remote.response.MovieResponse
import com.yoghi.moviecatalog.data.source.remote.response.TvShowResponse
import com.yoghi.moviecatalog.utils.EspressoIdlingResource
import retrofit2.await

class RemoteDataSource {

    companion object {
        private var instance: RemoteDataSource? = null

        fun getInstance(): RemoteDataSource =
            instance ?: synchronized(this) {
                instance ?: RemoteDataSource()
            }
    }

    suspend fun getNowPlayingMovies(
        callback: GetNowPlayingMoviesCallback
    ) {
        EspressoIdlingResource.increment()
        ApiClient.instance.getNowPlayingMovies().await().result?.let { listMovie ->
            callback.onAllMoviesReceived(
                listMovie
            )
            EspressoIdlingResource.decrement()
        }
    }

    suspend fun getMovieDetail(movieId: Int, callback: GetMovieDetailCallback){
        EspressoIdlingResource.increment()
        ApiClient.instance.getDetailMovie(movieId).await().let { movie ->
            callback.onMovieDetailReceived(
                movie
            )
            EspressoIdlingResource.decrement()
        }
    }

    suspend fun getTvShowOnTheAir(callback: GetOnTheAirTvShowCallback) {
        EspressoIdlingResource.increment()
        ApiClient.instance.getTvShowOnTheAir().await().result?.let { listTvShow ->
            callback.onAllTvShowsReceived(
                listTvShow
            )
            EspressoIdlingResource.decrement()
        }
    }

    suspend fun getTvShowDetail(tvShowId: Int, callback: GetTvShowDetailCallback) {
        EspressoIdlingResource.increment()
        ApiClient.instance.getDetailTvShow(tvShowId).await().let { tvShow ->
            callback.onTvShowDetailReceived(
                tvShow
            )
            EspressoIdlingResource.decrement()
        }
    }

    interface GetNowPlayingMoviesCallback {
        fun onAllMoviesReceived(movieResponse: List<MovieResponse>)
    }

    interface GetMovieDetailCallback {
        fun onMovieDetailReceived(movieResponse: MovieResponse)
    }

    interface GetOnTheAirTvShowCallback {
        fun onAllTvShowsReceived(tvShowResponse: List<TvShowResponse>)
    }

    interface GetTvShowDetailCallback {
        fun onTvShowDetailReceived(tvShowResponse: TvShowResponse)
    }
}