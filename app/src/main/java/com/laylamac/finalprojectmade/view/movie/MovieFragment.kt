package com.laylamac.finalprojectmade.view.movie

import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.laylamac.finalprojectmade.MainActivity
import com.laylamac.finalprojectmade.R
import com.laylamac.finalprojectmade.api.ApiRepository
import com.laylamac.finalprojectmade.model.MovieMdl
import com.laylamac.finalprojectmade.view.detail.DetailActivity
import com.laylamac.finalprojectmade.viewModel.MovieViewModel
import kotlinx.android.synthetic.main.fragment_movie.*

class MovieFragment : Fragment(), MovieInterface {

    private lateinit var movieViewModel: MovieViewModel
    private lateinit var recyclerMovie: RecyclerView
    private lateinit var mAdapter: MovieAdapter
    private lateinit var mPresenter: MoviePresenter
    private var movie = mutableListOf<MovieMdl>()

    override fun showLoading() {
        pb_movie_fragment.visibility = View.VISIBLE

    }

    override fun hideLoading() {
        pb_movie_fragment.visibility = View.GONE

    }

    override fun moviesData(movie: MovieResponseMdl) {
        movieViewModel.setMovies(movie)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movie, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recyclerMovie = view.findViewById(R.id.movie_rv)
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)
        movieViewModel = ViewModelProviders.of(this).get(MovieViewModel::class.java)
        movieViewModel.getMovies().observe(this, getMovie)

        val api = ApiRepository.create()
        mPresenter = MoviePresenter(this, api)
        recyclerMovie.addItemDecoration(
            DividerItemDecoration(
                recyclerMovie.context,
                DividerItemDecoration.VERTICAL
            )
        )
        recyclerMovie.layoutManager = LinearLayoutManager(requireContext())
        mAdapter = MovieAdapter(requireContext(), movie) {
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra(MainActivity.EXTRA_DATA,it.id)
            intent.putExtra(MainActivity.TYPE, MainActivity.MOVIE)
            startActivity(intent)
        }
        recyclerMovie.adapter = mAdapter
        if (savedInstanceState == null) {
            mPresenter.loadMovie()
        } else {
            hideLoading()
        }
        super.onActivityCreated(savedInstanceState)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putString(MainActivity.INSTANCE, MovieFragment::class.java.simpleName)
        super.onSaveInstanceState(outState)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        val menuItem = menu.findItem(R.id.action_search_main)
        if (menuItem!=null){
            val searchView : SearchView = menuItem.actionView as SearchView
            searchView.setOnQueryTextListener(object  : SearchView.OnQueryTextListener{
                override fun onQueryTextSubmit(query: String): Boolean {
                    mPresenter.searchMovie(query)
                    return false
                }

                override fun onQueryTextChange(newText: String): Boolean {
                    return false
                }
            })
        }
        super.onCreateOptionsMenu(menu, inflater)
    }

    private val getMovie = Observer<MovieResponseMdl>{
        if (it != null){
            mAdapter.setMovie(it.result)
        }
    }

}


