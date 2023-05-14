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
import com.ltu.m7019e.v23.themoviedb.model.Review
import com.ltu.m7019e.v23.themoviedb.model.Video
import com.ltu.m7019e.v23.themoviedb.network.*
import kotlinx.coroutines.launch

class ThirdFragmentViewModel(private val movieRepository: MovieRepository,
                             application: Application,
                             movie: Movie) : AndroidViewModel(application) {



    private val _dataFetchStatus = MutableLiveData<DataFetchStatus>()
    val dataFetchStatus: LiveData<DataFetchStatus>
        get() {
            return _dataFetchStatus
        }

    private val _reviews = MutableLiveData<List<Review>>()
    val reviews: LiveData<List<Review>>
        get() {
            return _reviews
        }

    private val _videos = MutableLiveData<List<Video>>()
    val videos: LiveData<List<Video>>
        get() {
            return _videos
        }

    init {
        getReviews(movie)
        getVideos(movie)
    }

    fun getReviews(movie: Movie){
        _dataFetchStatus.value = DataFetchStatus.LOADING

        viewModelScope.launch {
            try {
                _reviews.value = movieRepository.getMovieReviews(movie)
                _dataFetchStatus.value = DataFetchStatus.DONE
            } catch (e: Exception) {
                _dataFetchStatus.value = DataFetchStatus.ERROR
                _reviews.value = arrayListOf(Review("","Error",e.message.toString(),"Error","Error","Error"))
            }
        }
    }

    fun getVideos(movie: Movie){
        _dataFetchStatus.value = DataFetchStatus.LOADING

        viewModelScope.launch {
            try {
                _videos.value = movieRepository.getMovieVideos(movie)
                _dataFetchStatus.value = DataFetchStatus.DONE
            } catch (e: Exception) {
                _dataFetchStatus.value = DataFetchStatus.ERROR
                _reviews.value = arrayListOf(Review("","Error",e.message.toString(),"Error","Error","Error"))
            }
        }
    }

}