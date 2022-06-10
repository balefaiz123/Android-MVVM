package com.example.moviedbproject.fragment.genre

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.common.entity.genre.Genre
import com.example.moviedbproject.databinding.GenreItemBinding
import kotlin.reflect.KFunction1

class GenreAdapter(
    val getSelection : () -> List<Genre>,
    val toggleClick: (Genre) -> Unit
) : RecyclerView.Adapter<GenreViewHolder>() {

    val data = AsyncListDiffer(this, diffUtil)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenreViewHolder {
        return GenreViewHolder(
        GenreItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        )
    }

    override fun onBindViewHolder(holder: GenreViewHolder, position: Int) {
        val data = data.currentList[position]
        holder.binding.genre = data
        holder.binding.isSelected = getSelection.invoke().contains(data)
        holder.binding.root.setOnClickListener{
            toggleClick(data)
        }
    }

    override fun getItemCount(): Int {
        return data.currentList.size
    }

    fun toggleSelection(genre: Genre, changes: () -> Unit) {
        val toggleDiffUtil = object : DiffUtil.Callback() {
            override fun getOldListSize(): Int = data.currentList.size

            override fun getNewListSize(): Int = data.currentList.size

            override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean = true

            override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                return data.currentList[oldItemPosition] != genre
            }
        }
        val differ = DiffUtil.calculateDiff(toggleDiffUtil)
        changes()
        differ.dispatchUpdatesTo(this)
    }

    companion object{
        val diffUtil = object: DiffUtil.ItemCallback<Genre>(){
            override fun areItemsTheSame(oldItem: Genre, newItem: Genre): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Genre, newItem: Genre): Boolean {
                return true
            }
        }
    }
}

class GenreViewHolder(val binding: GenreItemBinding) : RecyclerView.ViewHolder(binding.root)
