package com.ltu.m7019e.v23.themoviedb.viewmodel

import android.app.Application
import android.net.ConnectivityManager
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ltu.m7019e.v23.themoviedb.data.DefaultAppContainer
import com.ltu.m7019e.v23.themoviedb.data.MovieRepository
import com.ltu.m7019e.v23.themoviedb.database.MovieDatabaseDao
import com.ltu.m7019e.v23.themoviedb.model.Movie
import com.ltu.m7019e.v23.themoviedb.network.DataFetchStatus
import com.ltu.m7019e.v23.themoviedb.network.NetworkCallbackImpl
import kotlinx.coroutines.launch

class MovieListViewModel(
    private val movieRepository: MovieRepository,
    application: Application) : AndroidViewModel(application) {


    private val networkCallback = NetworkCallbackImpl(movieRepository, application)

    fun unregisterNetworkCallback() {
        networkCallback.unregister()
    }



    private val _dataFetchStatus = MutableLiveData<DataFetchStatus>()
    val dataFetchStatus: LiveData<DataFetchStatus>
        get() {
            return _dataFetchStatus
        }

    private val _movieList = MutableLiveData<List<Movie>>()
    val movieList: LiveData<List<Movie>>
        get() {
            return _movieList
        }

    private val _navigateToMovieDetail = MutableLiveData<Movie?>()
    val navigateToMovieDetail: MutableLiveData<Movie?>
        get() {
            return _navigateToMovieDetail
        }

    init {
        getPopularMovies()
        _dataFetchStatus.value = DataFetchStatus.LOADING
    }

    fun onMovieListItemClicked(movie: Movie) {
        _navigateToMovieDetail.value = movie
    }

    fun onMovieDetailNavigated() {
        _navigateToMovieDetail.value = null
    }


    fun getPopularMovies() {
        viewModelScope.launch {
            try {
                _movieList.value = movieRepository.getPopularMovies()
                _dataFetchStatus.value = DataFetchStatus.DONE
            } catch (e: Exception) {
                _dataFetchStatus.value = DataFetchStatus.ERROR
                _movieList.value = arrayListOf()
            }
        }
    }

    fun getTopRatedMovies() {
        viewModelScope.launch {
            try {
                _movieList.value = movieRepository.getTopRatedMovies()
                _dataFetchStatus.value = DataFetchStatus.DONE
            } catch (e: Exception) {
                _dataFetchStatus.value = DataFetchStatus.ERROR
                _movieList.value = arrayListOf()
            }
        }
    }

    fun getSavedMovies() {
        viewModelScope.launch {
            _movieList.value = movieRepository.getSavedMovies()
        }
    }
}