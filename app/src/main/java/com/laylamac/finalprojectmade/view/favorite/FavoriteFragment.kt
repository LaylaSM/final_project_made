package com.laylamac.finalprojectmade.view.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.laylamac.finalprojectmade.MainActivity.Companion.INSTANCE
import com.laylamac.finalprojectmade.R
import com.laylamac.finalprojectmade.view.TabAdapter

class FavoriteFragment : Fragment() {

    private lateinit var mAdapter : TabAdapter
    private lateinit var mViewPager : ViewPager
    private lateinit var mTabLayout : TabLayout

    companion object{
        const val INTENT_REQUEST_CODE = 101
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_favorite, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        mViewPager = view.findViewById(R.id.favorite_view_pager)
        mTabLayout = view.findViewById(R.id.favorite_tab_layout)
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        mAdapter = TabAdapter(childFragmentManager)
        mAdapter.addFragment(FavoriteMovieFragment())
        mAdapter.addFragment(FavoriteTvShowFragment())
        mViewPager.adapter = mAdapter
        mViewPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(mTabLayout))
        mTabLayout.addOnTabSelectedListener(tabListener)
        super.onActivityCreated(savedInstanceState)
    }

    private val tabListener = object :TabLayout.OnTabSelectedListener{
        override fun onTabReselected(p0: TabLayout.Tab?) {

        }

        override fun onTabUnselected(p0: TabLayout.Tab?) {

        }

        override fun onTabSelected(tl: TabLayout.Tab) {
           mViewPager.currentItem = tl.position
        }

    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putString(INSTANCE, FavoriteFragment::class.java.simpleName)
        super.onSaveInstanceState(outState)
    }



}
