package msc.app.embalsespuertorico

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.PagerAdapter

/**
 * Created by Moises Cardona on 2/8/2017.
 */

class MainActivityPager(fm: FragmentManager, private val tabCount: Int) : FragmentStatePagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        when (position) {
            0 -> return MainActivity()
            1 -> return LookInMap()
            else -> return MainActivity()
        }
    }

    override fun getItemPosition(`object`: Any): Int {
        return PagerAdapter.POSITION_NONE
    }

    override fun getCount(): Int {
        return tabCount
    }
}
