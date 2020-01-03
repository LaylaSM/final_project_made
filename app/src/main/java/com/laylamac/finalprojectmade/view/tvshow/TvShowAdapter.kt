package com.laylamac.finalprojectmade.view.tvshow

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.laylamac.finalprojectmade.R
import com.laylamac.finalprojectmade.api.ApiRepository
import com.laylamac.finalprojectmade.model.TvShowMdl
import com.squareup.picasso.Picasso
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_list_movie.view.*

class TvShowAdapter(
    private val context: Context,
    private val tvSHow: MutableList<TvShowMdl>,
    private val onClickListener: (TvShowMdl) -> Unit
) : RecyclerView.Adapter<TvShowAdapter.ViewHolder>() {


    class ViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView),
        LayoutContainer {
        fun bind(tvShow: TvShowMdl, onClickListener: (TvShowMdl) -> Unit) {
            itemView.tv_title_movie.text = tvShow.title
            Picasso.get()
                .load(ApiRepository.BASE_IMAGE + tvShow.poster)
                .into(itemView.iv_poster_movie)
            containerView.setOnClickListener { onClickListener(tvShow) }
        }

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        LayoutInflater.from(context).inflate(R.layout.item_list_movie, parent, false)
    )

    override fun getItemCount(): Int = tvSHow.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(tvSHow[position], onClickListener)
    }

    fun setTvShow(listTvsShow: List<TvShowMdl>) {
        tvSHow.clear()
        tvSHow.addAll(listTvsShow)
        notifyDataSetChanged()
    }

}
