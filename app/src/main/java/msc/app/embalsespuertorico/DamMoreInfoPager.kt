package msc.app.embalsespuertorico

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.PagerAdapter

//Extending FragmentStatePagerAdapter
class DamMoreInfoPager(fm: FragmentManager, private val tabCount: Int) : FragmentStatePagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> GetDamInfoAndShow()
            1 -> DamMoreInfoFragment()
            2 -> DamHistoryFragment()
            3 -> DamGraph()
            4 -> DamMaps()
            else -> GetDamInfoAndShow()
        }
    }

    override fun getItemPosition(`object`: Any): Int {
        return PagerAdapter.POSITION_NONE
    }

    override fun getCount(): Int {
        return tabCount
    }
}