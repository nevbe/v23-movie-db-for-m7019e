package com.ltu.m7019e.v23.themoviedb.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ltu.m7019e.v23.themoviedb.data.MovieRepository
import com.ltu.m7019e.v23.themoviedb.model.Movie
import java.lang.IllegalArgumentException

class ThirdFragmentViewModelFactory(private val movieRepository: MovieRepository, private val application: Application, private val movie: Movie): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(ThirdFragmentViewModel::class.java)) {
            return ThirdFragmentViewModel(movieRepository, application, movie) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}