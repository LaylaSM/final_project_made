package com.laylamac.finalprojectmade.view.favorite

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.laylamac.finalprojectmade.MainActivity
import com.laylamac.finalprojectmade.R
import com.laylamac.finalprojectmade.model.MovieMdl
import com.laylamac.finalprojectmade.view.detail.DetailActivity
import com.laylamac.finalprojectmade.view.detail.DetailActivity.Companion.INTENT_RESULT_CODE
import com.laylamac.finalprojectmade.view.favorite.FavoriteFragment.Companion.INTENT_REQUEST_CODE
import com.laylamac.finalprojectmade.view.movie.MovieAdapter
import androidx.lifecycle.Observer
import com.laylamac.finalprojectmade.viewModel.FavoriteMovieViewModel
import kotlinx.android.synthetic.main.fragment_favorite_movie.*

class FavoriteMovieFragment : Fragment(), FavoriteMovieInterface {

    private lateinit var favMovieViewModel: FavoriteMovieViewModel
    private lateinit var mPresenter: FavoriteMoviePresenter
    private lateinit var mAdapter: MovieAdapter
    private var listMovies = mutableListOf<MovieMdl>()


    override fun showLoading() {
        pb_favorite_movie_fragment.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        pb_favorite_movie_fragment.visibility = View.GONE
    }

    override fun favoriteMovie(movie: List<MovieMdl>) {
        favMovieViewModel.setFavoriteMovie(movie)
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_favorite_movie, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        favMovieViewModel = ViewModelProviders.of(this).get(FavoriteMovieViewModel::class.java)
        favMovieViewModel.getFavoriteMovie().observe(this, getFavMovie)
        mPresenter = FavoriteMoviePresenter(this)
        recycler_favorite_movie_fragment.addItemDecoration(
            DividerItemDecoration(
                recycler_favorite_movie_fragment.context,
                DividerItemDecoration.VERTICAL
            )
        )
        recycler_favorite_movie_fragment.layoutManager = LinearLayoutManager(context)
        mAdapter = MovieAdapter(requireContext(), listMovies) {
            val intent = Intent(activity, DetailActivity::class.java)
            intent.putExtra(MainActivity.EXTRA_DATA, it.id)
            intent.putExtra(MainActivity.TYPE, MainActivity.MOVIE)
            startActivityForResult(intent, INTENT_REQUEST_CODE)
        }
        recycler_favorite_movie_fragment.adapter = mAdapter
        if (savedInstanceState == null) {
            mPresenter.getFavMovie(requireContext())
        } else {
            hideLoading()
        }
        super.onActivityCreated(savedInstanceState)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == INTENT_REQUEST_CODE) {
            if (resultCode == INTENT_RESULT_CODE) {
                mPresenter.getFavMovie(requireContext())
            }
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    private val getFavMovie = Observer<List<MovieMdl>> {
        if (it != null) {
            mAdapter.setMovie(it)
        }
    }

}
