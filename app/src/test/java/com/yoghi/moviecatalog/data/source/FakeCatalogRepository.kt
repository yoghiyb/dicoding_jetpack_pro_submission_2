package com.yoghi.moviecatalog.data.source

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.yoghi.moviecatalog.data.DataModel
import com.yoghi.moviecatalog.data.source.remote.RemoteDataSource
import com.yoghi.moviecatalog.data.source.remote.response.MovieResponse
import com.yoghi.moviecatalog.data.source.remote.response.TvShowResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FakeCatalogRepository(private val remoteDataSource: RemoteDataSource) : CatalogDataSource {

    override fun getNowPlayingMovies(): LiveData<List<DataModel>> {
        val listMovieResult = MutableLiveData<List<DataModel>>()
        CoroutineScope(Dispatchers.IO).launch {
            remoteDataSource.getNowPlayingMovies(object : RemoteDataSource.GetNowPlayingMoviesCallback{
                override fun onAllMoviesReceived(movieResponse: List<MovieResponse>) {
                    val movieList = ArrayList<DataModel>()
                    for (response in movieResponse){
                        val movie = DataModel(
                            response.id,
                            response.name,
                            response.desc,
                            response.poster,
                            response.img_preview
                        )

                        movieList.add(movie)
                    }
                    listMovieResult.postValue(movieList)
                }
            })
        }

        return listMovieResult
    }

    override fun getMovieDetail(movieId: Int): LiveData<DataModel> {
        val movieResult = MutableLiveData<DataModel>()
        CoroutineScope(Dispatchers.IO).launch {
            remoteDataSource.getMovieDetail(movieId, object : RemoteDataSource.GetMovieDetailCallback{
                override fun onMovieDetailReceived(movieResponse: MovieResponse) {
                    val movie = DataModel(
                        movieResponse.id,
                        movieResponse.name,
                        movieResponse.desc,
                        movieResponse.poster,
                        movieResponse.img_preview
                    )

                    movieResult.postValue(movie)
                }
            })
        }

        return movieResult
    }

    override fun getTvShowOnTheAir(): LiveData<List<DataModel>> {
        val listTvShowResult = MutableLiveData<List<DataModel>>()
        CoroutineScope(Dispatchers.IO).launch {
            remoteDataSource.getTvShowOnTheAir(object : RemoteDataSource.GetOnTheAirTvShowCallback{
                override fun onAllTvShowsReceived(tvShowResponse: List<TvShowResponse>) {
                    val tvShowList = ArrayList<DataModel>()
                    for (response in tvShowResponse){
                        val tvShow = DataModel(
                            response.id,
                            response.name,
                            response.desc,
                            response.poster,
                            response.img_preview
                        )
                        tvShowList.add(tvShow)
                    }

                    listTvShowResult.postValue(tvShowList)
                }
            })
        }

        return listTvShowResult
    }

    override fun getTvShowDetail(tvShowId: Int): LiveData<DataModel> {
        val tvShowResult = MutableLiveData<DataModel>()
        CoroutineScope(Dispatchers.IO).launch {
            remoteDataSource.getTvShowDetail(tvShowId, object : RemoteDataSource.GetTvShowDetailCallback{
                override fun onTvShowDetailReceived(tvShowResponse: TvShowResponse) {
                    val tvShow = DataModel(
                        tvShowResponse.id,
                        tvShowResponse.name,
                        tvShowResponse.desc,
                        tvShowResponse.poster,
                        tvShowResponse.img_preview
                    )

                    tvShowResult.postValue(tvShow)
                }
            })
        }

        return tvShowResult
    }

}