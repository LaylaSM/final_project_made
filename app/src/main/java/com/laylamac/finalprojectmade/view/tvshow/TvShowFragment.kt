package com.laylamac.finalprojectmade.view.tvshow

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.laylamac.finalprojectmade.MainActivity
import com.laylamac.finalprojectmade.R
import com.laylamac.finalprojectmade.api.ApiRepository
import com.laylamac.finalprojectmade.model.TvShowMdl
import com.laylamac.finalprojectmade.view.detail.DetailActivity
import com.laylamac.finalprojectmade.viewModel.TvShowViewModel
import kotlinx.android.synthetic.main.fragment_tv_show.*

class TvShowFragment : Fragment(), TvShowInterface {

    private lateinit var tvShowViewModel: TvShowViewModel
    private lateinit var mPresenter: TvShowPresenter
    private lateinit var mAdapter: TvShowAdapter
    private var tvShows = mutableListOf<TvShowMdl>()


    override fun showLoading() {
        pb_tvShow_fragment.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        pb_tvShow_fragment.visibility = View.GONE
    }

    override fun tvShowData(tvShow: TvShowResponseMdl) {
        tvShowViewModel.setTvShows(tvShow)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_tv_show, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)
        tvShowViewModel = ViewModelProviders.of(this).get(TvShowViewModel::class.java)
        tvShowViewModel.getTvShows().observe(this, getTvShow)

        val api = ApiRepository.create()
        mPresenter = TvShowPresenter(this, api)
        rv_tv_show_fragment.addItemDecoration(
            DividerItemDecoration(
                rv_tv_show_fragment.context,
                DividerItemDecoration.VERTICAL
            )
        )
        rv_tv_show_fragment.layoutManager = LinearLayoutManager(requireContext())
        mAdapter = TvShowAdapter(requireContext(), tvShows) {
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra(MainActivity.EXTRA_DATA, it.id)
            intent.putExtra(MainActivity.TYPE, MainActivity.TV)
            startActivity(intent)
        }
        rv_tv_show_fragment.adapter = mAdapter
        if (savedInstanceState == null) {
            mPresenter.loadTvShow()
        } else {
            hideLoading()
        }
        super.onActivityCreated(savedInstanceState)
    }

    private val getTvShow = Observer<TvShowResponseMdl> {
        if (it != null) {
            mAdapter.setTvShow(it.result)
        }
    }

}
