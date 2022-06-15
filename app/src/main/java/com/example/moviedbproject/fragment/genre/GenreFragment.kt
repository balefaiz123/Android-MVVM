package com.example.moviedbproject.fragment.genre

import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.common.base.BaseFragment
import com.example.common.entity.ResponseApp
import com.example.common.entity.ResponseSuccess
import com.example.common.entity.genre.Genre
import com.example.common.ext.isEmpty
import com.example.common.ext.toggle
import com.example.moviedbproject.R
import com.example.moviedbproject.databinding.GenreBinding
import com.example.moviedbproject.view_model.GenreViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GenreFragment : BaseFragment<GenreViewModel, GenreBinding>() {
    override val vm: GenreViewModel by viewModels()

    override val layoutResourceId: Int = R.layout.genre

    val adapter = GenreAdapter(::getSelected, ::toggleClick)


    override fun initBinding(binding: GenreBinding) {
        super.initBinding(binding)
        binding.recycler.adapter = adapter
        vm.loadAllGenre()
        observeLiveData()
        binding.nextButton.setOnClickListener {
                vm.navigate(GenreFragmentDirections.genreToMovieByGenre(
                    vm.selected.value.orEmpty().map { it.id }.joinToString(",")
                ))
        }
    }


}


