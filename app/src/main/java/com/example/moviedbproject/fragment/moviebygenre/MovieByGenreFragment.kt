package com.example.moviedbproject.fragment.moviebygenre


import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.common.base.BaseFragment
import com.example.moviedbproject.R
import com.example.moviedbproject.databinding.MovieByGenreBinding
import com.example.moviedbproject.view_model.MovieByGenreViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MovieByGenreFragment : BaseFragment<MovieByGenreViewModel, MovieByGenreBinding>() {
    override val vm: MovieByGenreViewModel by viewModels()
    override val layoutResourceId: Int = R.layout.movie_by_genre

    val adapter = MovieByGenreAdapter(){
        findNavController().navigate(MovieByGenreFragmentDirections.movieByGenreToMovieDetails(it))
    }

    val stateAdapter = DiscoverMovieStateAdapter()
    val movieByGenreArgs: MovieByGenreFragmentArgs by navArgs()

    override fun initBinding(binding: MovieByGenreBinding) {
        super.initBinding(binding)
        observeLiveData()
    }
}


