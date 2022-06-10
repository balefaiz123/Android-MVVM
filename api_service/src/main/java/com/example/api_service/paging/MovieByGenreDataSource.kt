package com.example.api_service.paging

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.api_service.service.GetMovieByGenreService
import com.example.common.entity.movie_discover.Result

class MovieByGenreDataSource(
    val getMovieByGenreService: GetMovieByGenreService,
    val genres: String
) : PagingSource<Int, Result>() {
    override fun getRefreshKey(state: PagingState<Int, Result>): Int? {
        return null
    }


    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Result> {
        return try {
            val result = getMovieByGenreService.getMovieDiscoverByGenre(
                genres = genres, page = params.key ?: 1
            )
            result.body()?.let {
                val totalPage = it.total_pages
                LoadResult.Page(data = it.results, if (it.page == 1) null else it.page - 1,
                    if(it.page<totalPage)it.page + 1 else null)
            } ?: LoadResult.Error(Exception("invalid data"))
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}

object DiscoverMoviesPager {
    fun createPager(
        pageSize: Int,
        getMovieByGenreService: GetMovieByGenreService,
        genres: String
    ): Pager<Int, Result> = Pager(
        config = PagingConfig(pageSize),
        pagingSourceFactory = {
            MovieByGenreDataSource(getMovieByGenreService, genres)
        }
    )
}