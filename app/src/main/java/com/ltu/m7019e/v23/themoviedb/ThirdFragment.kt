package com.ltu.m7019e.v23.themoviedb

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.ltu.m7019e.v23.themoviedb.adapter.ReviewListAdapter
import com.ltu.m7019e.v23.themoviedb.adapter.VideoListAdapter
import com.ltu.m7019e.v23.themoviedb.database.MovieDatabase
import com.ltu.m7019e.v23.themoviedb.database.MovieDatabaseDao
import com.ltu.m7019e.v23.themoviedb.databinding.FragmentThirdBinding
import com.ltu.m7019e.v23.themoviedb.model.Movie
import com.ltu.m7019e.v23.themoviedb.viewmodel.*

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class ThirdFragment : Fragment() {

    private lateinit var viewModel: ThirdFragmentViewModel
    private lateinit var viewModelFactory: ThirdFragmentViewModelFactory

    private lateinit var movieDatabaseDao: MovieDatabaseDao

    private var _binding: FragmentThirdBinding? = null
    private val binding get() = _binding!!

    private lateinit var movie : Movie

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val callback = requireActivity().onBackPressedDispatcher.addCallback(this) {
            findNavController().navigate(ThirdFragmentDirections.actionThirdFragmentToMovieListFragment())
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentThirdBinding.inflate(inflater)
        binding.lifecycleOwner = this
        movie = ThirdFragmentArgs.fromBundle(requireArguments()).movie
        binding.movie = movie

        val reviewListAdapter = ReviewListAdapter()
        binding.reviewListRv.adapter = reviewListAdapter

        val videoListAdapter = VideoListAdapter()
        binding.movieVidRv.adapter = videoListAdapter
        val appContainer = TheMovieDataBase.getAppContainer(requireContext())
        val movieRepository = appContainer.movieRepository
        val application = requireNotNull(this.activity).application
        movieDatabaseDao = MovieDatabase.getInstance(application).movieDatabaseDao

        viewModelFactory = ThirdFragmentViewModelFactory(movieRepository, application, movie)
        viewModel = ViewModelProvider(this, viewModelFactory)[ThirdFragmentViewModel::class.java]

        viewModel.reviews.observe(viewLifecycleOwner) {
            reviewListAdapter.submitList(it)
        }

        viewModel.videos.observe(viewLifecycleOwner) {
            videoListAdapter.submitList(it)
        }



        return binding.root
    }

    @SuppressLint("SetJavaScriptEnabled")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.toMovieDetails.setOnClickListener{
            findNavController().navigate(ThirdFragmentDirections.actionThirdFragmentToMovieDetailFragment(movie))
        }

    }
}
