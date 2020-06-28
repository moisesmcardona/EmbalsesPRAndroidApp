package msc.app.embalsespuertorico

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.PagerAdapter

//Extending FragmentStatePagerAdapter
class DamMoreInfoPager(fm: FragmentManager, private val tabCount: Int) : FragmentStatePagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        when (position) {
            0 -> return GetDamInfoAndShow()
            1 -> return DamMoreInfoFragment()
            2 -> return DamHistoryFragment()
            3 -> return DamGraph()
            4 -> return DamMaps()
            else -> return GetDamInfoAndShow()
        }
    }

    override fun getItemPosition(`object`: Any): Int {
        return PagerAdapter.POSITION_NONE
    }

    override fun getCount(): Int {
        return tabCount
    }
}