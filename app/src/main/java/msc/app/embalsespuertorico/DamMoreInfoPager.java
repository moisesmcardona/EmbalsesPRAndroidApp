package msc.app.embalsespuertorico;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

//Extending FragmentStatePagerAdapter
public class DamMoreInfoPager extends FragmentStatePagerAdapter {

    private int tabCount;
    public DamMoreInfoPager(FragmentManager fm, int tabCount) {
        super(fm);
        this.tabCount= tabCount;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new GetDamInfoAndShow();
            case 1:
                return new DamMoreInfoFragment();
            case 2:
                return new DamHistoryFragment();
            case 3:
                return new DamGraph();
            case 4:
                return new DamMaps();
            default:
                return null;
        }
    }
    @Override
    public int getItemPosition(@NonNull Object object) {
        return POSITION_NONE;
    }
    @Override
    public int getCount() {
        return tabCount;
    }
}