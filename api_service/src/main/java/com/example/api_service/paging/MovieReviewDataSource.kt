package com.example.api_service.paging

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.api_service.service.GetMovieDetailsService
import com.example.common.entity.moviereview.Result

class MovieReviewDataSource (val getMovieDetailsService: GetMovieDetailsService,
                              val  movieId:String
) : PagingSource<Int, Result>() {
    override fun getRefreshKey(state: PagingState<Int, Result>): Int?{
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Result> {
        return try {
            val result = getMovieDetailsService.getMovieReviews(
                movieId = movieId, page = params.key ?: 1
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

object MovieReviewPager{
    fun createPager(
        pageSize:Int,
        getMovieDetailsService: GetMovieDetailsService,
        movieId:String
    ): Pager<Int, Result> = Pager(
        config = PagingConfig(pageSize),
        pagingSourceFactory = {
            MovieReviewDataSource(getMovieDetailsService,movieId)
        }
    )
}