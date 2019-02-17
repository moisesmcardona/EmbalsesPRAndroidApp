package msc.app.embalsespuertorico;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.android.gms.ads.MobileAds;

public class MainActivityTab extends AppCompatActivity implements TabLayout.OnTabSelectedListener {
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;
    //This is our tablayout

    //This is our viewPager
    private ViewPager viewPager;
    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        viewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        TabLayout tabLayout;
        String[] DrawerArrayList;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainactivitytablayout);
        MobileAds.initialize(this, "ca-app-pub-1744946688565368~1848798483");
        //Adding toolbar to the activity
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        app_settings settings = new app_settings(this);
        settings.initialize(MainActivityTab.this, MainActivityTab.class);
        //Initializing the tablayout
        tabLayout = findViewById(R.id.tabLayout);

        //Adding the tabs using addTab() method
        if (settings.getLanguage().equals("Spanish")) {
            tabLayout.addTab(tabLayout.newTab().setText(R.string.lista));
            tabLayout.addTab(tabLayout.newTab().setText(R.string.mapa));
        }
        else{
            tabLayout.addTab(tabLayout.newTab().setText(R.string.list));
            tabLayout.addTab(tabLayout.newTab().setText(R.string.map));
        }
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        viewPager = findViewById(R.id.pager);

        //Creating our pager adapter
        MainActivityPager adapter = new MainActivityPager(getSupportFragmentManager(), tabLayout.getTabCount());

        //Adding adapter to pager
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        //Adding onTabSelectedListener to swipe views
        tabLayout.addOnTabSelectedListener(this);
        viewPager.setCurrentItem(settings.getDefaultView());
        DrawerArrayList = getResources().getStringArray(R.array.drawerMainActivityList);
        mDrawerLayout = findViewById(R.id.drawer_layout);
        mDrawerList = findViewById(R.id.left_drawer);
        mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
        // set up the drawer's list view with items and click listener
        if (settings.getLanguage().equals("Spanish"))
            mDrawerList.setAdapter(new ArrayAdapter<>(this, R.layout.drawerlistbox, DrawerArrayList));
        else
            mDrawerList.setAdapter(new ArrayAdapter<>(this, R.layout.drawerlistbox, getResources().getStringArray(R.array.drawerMainActivityListEnglish)));
        mDrawerList.setOnItemClickListener(new MainActivityTab.DrawerItemClickListener());
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, 0, 0)
        {
            public void onDrawerClosed(View view)
            {
                if (getSupportActionBar() != null)
                    getSupportActionBar().setTitle(R.string.app_name);
                getWindow().getDecorView().findViewById(android.R.id.content).invalidate();
            }

            public void onDrawerOpened(View drawerView)
            {
                if (getSupportActionBar() != null)
                    getSupportActionBar().setTitle(R.string.app_name);
                invalidateOptionsMenu();
                mDrawerLayout.bringToFront();
            }
            public void onDrawerSlide(View drawerView, float offset)
            {
                if (offset != 0)
                    mDrawerLayout.bringToFront();
            }
        };
        mDrawerLayout.addDrawerListener(mDrawerToggle);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_drawer);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
        }
        mDrawerToggle.setDrawerIndicatorEnabled(true);
        mDrawerLayout.addDrawerListener(mDrawerToggle);
        mDrawerList.setItemChecked(0, true);
        mDrawerToggle.syncState();
        RateMeMaybe rmm = new RateMeMaybe(this);
        rmm.setPromptMinimums(3, 2, 3, 2);
        //rmm.setPromptMinimums(0, 0, 0, 0);
        rmm.run();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        //noinspection SimplifiableIfStatement
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggls
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            drawerItems di = new drawerItems();
            di.mainDrawerItems(MainActivityTab.this, MainActivityTab.class, position, 0);
            selectItem(position);
        }
    }

    private void selectItem(int position) {
        // update selected item and title, then close the drawer
        mDrawerList.setItemChecked(position, true);
        mDrawerLayout.closeDrawer(mDrawerList);
    }
}
