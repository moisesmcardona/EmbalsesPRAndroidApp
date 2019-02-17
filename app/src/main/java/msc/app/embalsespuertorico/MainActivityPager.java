package msc.app.embalsespuertorico;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by Moises Cardona on 2/8/2017.
 */

public class MainActivityPager extends FragmentStatePagerAdapter {
    private int tabCount;
    public MainActivityPager(FragmentManager fm, int tabCount) {
        super(fm);
        this.tabCount= tabCount;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new MainActivity();
            case 1:
                return new LookInMap();
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
