package com.ltu.m7019e.v23.themoviedb.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ltu.m7019e.v23.themoviedb.data.DefaultAppContainer
import com.ltu.m7019e.v23.themoviedb.data.MovieRepository
import com.ltu.m7019e.v23.themoviedb.database.MovieDatabaseDao
import com.ltu.m7019e.v23.themoviedb.model.Movie
import com.ltu.m7019e.v23.themoviedb.network.DataFetchStatus
import kotlinx.coroutines.launch

class MovieDetailViewModel(
    private val movieRepository: MovieRepository,
    application: Application,
    movie: Movie
) : AndroidViewModel(application){



    private val _dataFetchStatus = MutableLiveData<DataFetchStatus>()
    val dataFetchStatus: LiveData<DataFetchStatus>
        get() {
            return _dataFetchStatus
        }

    private val _movie = MutableLiveData<Movie>()
    val movie: LiveData<Movie>
        get() {
            return _movie
        }

    private val _isFavorite = MutableLiveData<Boolean>()
    val isFavorite: LiveData<Boolean>
        get() {
            return _isFavorite
        }

    init {
        _dataFetchStatus.value = DataFetchStatus.LOADING
        setIsFavorite(movie)
        getMovieDetails(movie)
    }

    private fun setIsFavorite(movie: Movie) {
        viewModelScope.launch {
            _isFavorite.value = movieRepository.isFavoriteMovie(movie)
        }
    }


    fun onSaveMovieButtonClicked(movie: Movie) {
        viewModelScope.launch {
            movieRepository.saveMovie(movie)
            setIsFavorite(movie)
        }
    }

    fun onRemoveMovieButtonClicked(movie: Movie) {
        viewModelScope.launch {
            movieRepository.deleteMovie(movie)
            setIsFavorite(movie)
        }
    }

    private fun getMovieDetails(movie: Movie) {

            viewModelScope.launch {
                try {
                    _movie.value =movieRepository.getMovieDetails(movie)
                    _dataFetchStatus.value = DataFetchStatus.DONE
                } catch (e: Exception) {
                    _dataFetchStatus.value = DataFetchStatus.ERROR
                    _movie.value = movie
                    _movie.value?.title = e.message.toString()
                }

        }
    }
}