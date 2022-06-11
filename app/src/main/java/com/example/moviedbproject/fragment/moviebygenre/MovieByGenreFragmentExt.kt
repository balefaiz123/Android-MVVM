package com.example.moviedbproject.fragment.moviebygenre

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

fun MovieByGenreFragment.observeLiveData(){

    binding.recycler.adapter = adapter.withLoadStateFooter(stateAdapter)
    vm.loadAllMovieByGenre(movieByGenreArgs.genreId)
    vm.dataMovieByGenre.observe(this) {
        CoroutineScope(Dispatchers.Main).launch {
            adapter.submitData(it)
        }
    }
}