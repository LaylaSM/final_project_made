package com.laylamac.finalprojectmade

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.laylamac.finalprojectmade.base.BaseAppCompatActivity
import com.laylamac.finalprojectmade.view.favorite.FavoriteFragment
import com.laylamac.finalprojectmade.view.movie.MovieFragment
import com.laylamac.finalprojectmade.view.settings.SettingsActivity
import com.laylamac.finalprojectmade.view.tvshow.TvShowFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_DATA = "extra_data"
        const val MOVIE = "movie"
        const val TV = "tv"
        const val TYPE = "type"
        const val INSTANCE = "instance"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        main_bottom_navigation.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.movie_bottom_navigation_menu -> {
                    val fragment = MovieFragment()
                    supportFragmentManager
                        .beginTransaction()
                        .replace(
                            R.id.main_fragment_container,
                            fragment,
                            MovieFragment::class.java.simpleName
                        )
                        .commit()

                    supportActionBar?.setTitle(R.string.movies)
                }
                R.id.tv_bottom_navigation_menu -> {
                    val fragment = TvShowFragment()
                    supportFragmentManager
                        .beginTransaction()
                        .replace(
                            R.id.main_fragment_container,
                            fragment,
                            TvShowFragment::class.java.simpleName
                        )
                        .commit()

                    supportActionBar?.setTitle(R.string.tv_shows)
                }
                R.id.fav_bottom_navigation_menu -> {
                    val fragment = FavoriteFragment()
                    supportFragmentManager
                        .beginTransaction()
                        .replace(
                            R.id.main_fragment_container,
                            fragment,
                            FavoriteFragment::class.java.simpleName
                        )
                        .commit()

                    supportActionBar?.setTitle(R.string.favorite)
                }
            }
            true
        }
        if (savedInstanceState == null) {
            main_bottom_navigation.selectedItemId = R.id.movie_bottom_navigation_menu
        } else {
            when (savedInstanceState.getString(INSTANCE)) {
                MovieFragment::class.java.simpleName -> {
                    main_bottom_navigation.selectedItemId = R.id.movie_bottom_navigation_menu
                }
                TvShowFragment::class.java.simpleName -> {
                    main_bottom_navigation.selectedItemId = R.id.tv_bottom_navigation_menu
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_settings){
            startActivity(Intent(applicationContext, SettingsActivity::class.java))
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        moveTaskToBack(true)
        super.onBackPressed()
    }
}
