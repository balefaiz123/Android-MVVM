package com.example.moviedbproject.fragment.genre

import android.view.View
import com.example.common.entity.genre.Genre
import com.example.common.ext.toggle

fun GenreFragment.observeLiveData() = with(vm) {

    observeResponseData(
        genreData,
        success = {
            adapter.data.submitList(it.genres)
            binding.loadingBar.visibility = View.GONE
            binding.button.visibility = View.GONE
        },
        error = {
            binding.loadingBar.visibility = View.GONE
            binding.button.visibility = View.VISIBLE
            binding.button.setOnClickListener {
                this.loadAllGenre()
            }
        },
        loading = {
            binding.loadingBar.visibility = View.VISIBLE
        }
    )
    this.selected.observe(this@observeLiveData){
        if (it.isEmpty()){
            binding.nextButton.visibility = View.GONE
        }else{
            binding.nextButton.visibility = View.VISIBLE
        }
    }
}

fun GenreFragment.toggleClick(genre: Genre) {
    adapter.toggleSelection(genre) {
        vm.selected.toggle(genre)
    }
}

fun GenreFragment.getSelected() : List<Genre>{
    return vm.selected.value.orEmpty()
}

