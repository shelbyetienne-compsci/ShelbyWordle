package com.example.wordlegame

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

private val TAB_TITLES = arrayOf(
    R.string.tab_text_1,
    R.string.tab_text_2,
    R.string.tab_text_3
)

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
class SectionsPagerAdapter(
    private val context: Context,
    fm: FragmentManager
) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        var fragment: Fragment? = null
        when (position) {
            0 -> fragment = FourLetters()
            1 -> fragment = FiveLetters()
            2 -> fragment = SixLetters()
        }
        return fragment!!
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 -> "FourLetters"
            1 -> "FiveLetters"
            2 -> "SixLetters"
            else -> null
        }
    }

    override fun getCount(): Int {
        return 3
    }
}