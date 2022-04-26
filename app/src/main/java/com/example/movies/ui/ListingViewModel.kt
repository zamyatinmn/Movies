package com.example.movies.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movies.data.MovieRepository
import com.example.movies.model.Movie
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListingViewModel @Inject constructor(private val movieRepository: MovieRepository) :
    ViewModel() {

    private val tempList = mutableListOf<Movie>()
    private val _movieList = MutableLiveData<List<Movie>>()
    val movieList = _movieList


    init {
        fetchMovies()
    }

    fun fetchMovies() {
        viewModelScope.launch {
            movieRepository.fetchTrendingMovies().collect {
                if (it != null) {
                    it.data?.let { list -> tempList.addAll(list.results) }
                    _movieList.value = tempList
                }
            }
        }
    }
}