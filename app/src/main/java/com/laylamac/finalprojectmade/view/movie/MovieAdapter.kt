package com.laylamac.finalprojectmade.view.movie

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.laylamac.finalprojectmade.R
import com.laylamac.finalprojectmade.api.ApiRepository
import com.laylamac.finalprojectmade.model.MovieMdl
import com.squareup.picasso.Picasso
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_list_movie.view.*

class MovieAdapter(
    private val context: Context,
    private val movies: MutableList<MovieMdl>,
    private val onClickListener: (MovieMdl) -> Unit
) : RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    fun setMovie(movie: List<MovieMdl>) {
        movies.clear()
        movies.addAll(movie)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        LayoutInflater.from(context).inflate(R.layout.item_list_movie, parent, false)
    )

    override fun getItemCount(): Int = movies.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(context, movies[position], onClickListener)
    }

    class ViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView),
        LayoutContainer {
        fun bind(context: Context, movie: MovieMdl, onClickListener: (MovieMdl) -> Unit) {
            itemView.tv_title_movie.text = movie.title
            itemView.tv_release_date_movie.text= movie.release_date
            itemView.tv_desc_movie.text = movie.overview
            Picasso.get()
                .load(ApiRepository.BASE_IMAGE + movie.poster_path)
                .into(itemView.iv_poster_movie)
            containerView.setOnClickListener { onClickListener(movie) }
        }

    }

}
