package com.ltu.m7019e.v23.themoviedb

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.ltu.m7019e.v23.themoviedb.adapter.GenreListAdapter
import com.ltu.m7019e.v23.themoviedb.database.MovieDatabaseDao
import com.ltu.m7019e.v23.themoviedb.databinding.FragmentMovieDetailBinding
import com.ltu.m7019e.v23.themoviedb.model.Movie
import com.ltu.m7019e.v23.themoviedb.network.DataFetchStatus
import com.ltu.m7019e.v23.themoviedb.utils.Constants
import com.ltu.m7019e.v23.themoviedb.viewmodel.MovieDetailViewModel
import com.ltu.m7019e.v23.themoviedb.viewmodel.MovieDetailViewModelFactory

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class MovieDetailFragment : Fragment() {

    private lateinit var viewModel: MovieDetailViewModel
    private lateinit var viewModelFactory: MovieDetailViewModelFactory

    private lateinit var movieDatabaseDao: MovieDatabaseDao

    private var _binding: FragmentMovieDetailBinding? = null
    private val binding get() = _binding!!

    private lateinit var movie : Movie

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentMovieDetailBinding.inflate(inflater)
        binding.lifecycleOwner = this
        movie = MovieDetailFragmentArgs.fromBundle(requireArguments()).movie
        binding.movie = movie

        val genreListAdapter = GenreListAdapter()
        binding.genreListRv.adapter = genreListAdapter
        val appContainer = TheMovieDataBase.getAppContainer(requireContext())
        val movieRepository = appContainer.movieRepository
        val application = requireNotNull(this.activity).application


        //movieDatabaseDao should be movieDataBase
        viewModelFactory = MovieDetailViewModelFactory(movieRepository, application, movie)
        viewModel = ViewModelProvider(this, viewModelFactory)[MovieDetailViewModel::class.java]

        // Give access to the view model
        binding.viewModel = viewModel

        viewModel.movie.observe(viewLifecycleOwner) { _movie ->
            binding.movie = _movie
            movie.imdbId = _movie.imdbId
            movie.homepage = _movie.homepage
        }

        viewModel.dataFetchStatus.observe(viewLifecycleOwner) { status ->
            status?.let {
                when (status) {
                    DataFetchStatus.LOADING -> {
                        binding.visitImdb.visibility = View.GONE
                        binding.visitMovieSite.visibility = View.GONE
                    }
                    DataFetchStatus.ERROR -> {
                        binding.movieDetailTitle.text = getString(R.string.movie_detail_api_error)
                    }
                    DataFetchStatus.DONE -> {
                        if(movie.homepage != ""){
                            binding.visitMovieSite.visibility = View.VISIBLE
                        }
                        if(movie.imdbId != ""){
                            binding.visitImdb.visibility = View.VISIBLE
                        }
                    }
                }
            }
        }

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.visitMovieSite.setOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(binding.movie?.homepage)))
        }

        binding.visitImdb.setOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(Constants.IMDB_BASE_URL + binding.movie?.imdbId)))
        }

        binding.backToMovieList.setOnClickListener {
            findNavController().navigate(MovieDetailFragmentDirections.actionMovieDetailFragmentToMovieListFragment())
        }

        binding.toThirdFragment.setOnClickListener {
            findNavController().navigate(MovieDetailFragmentDirections.actionMovieDetailFragmentToThirdFragment(movie))
        }
    }

}