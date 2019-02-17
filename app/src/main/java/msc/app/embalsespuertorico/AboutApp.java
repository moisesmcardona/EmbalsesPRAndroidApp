package msc.app.embalsespuertorico;

import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

/**
 * Created by Moisés Cardona on 9/25/2015.
 */

public class AboutApp extends AppCompatActivity {
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aboutapp);
        final TextView OpenPage = findViewById(R.id.textView4);
        final TextView textview = findViewById(R.id.textView);
        final AdView mAdView = findViewById(R.id.aboutad);
        mDrawerList = findViewById(R.id.left_drawer);
        app_settings as = new app_settings(this);
        if (as.getLanguage().equals("Spanish")) {
            mDrawerList.setAdapter(new ArrayAdapter<>(this, R.layout.drawerlistbox, getResources().getStringArray(R.array.drawerMainActivityList)));
            textview.setText(R.string.acerca_de);
        } else {
            mDrawerList.setAdapter(new ArrayAdapter<>(this, R.layout.drawerlistbox, getResources().getStringArray(R.array.drawerMainActivityList)));
            textview.setText(R.string.about_app);
        }
        AdRequest adRequest = new AdRequest.Builder().addTestDevice("03929FBEA0721F82E4AEE15546DBB5DC").build();
        mAdView.loadAd(adRequest);
        mDrawerLayout = findViewById(R.id.drawer_layout);
        mDrawerList = findViewById(R.id.left_drawer);
        mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, 0, 0) {
            public void onDrawerClosed(View view) {
                if (getSupportActionBar() != null)
                    getSupportActionBar().setTitle(R.string.app_name);
                getWindow().getDecorView().findViewById(android.R.id.content).invalidate();
                OpenPage.bringToFront();
                mAdView.bringToFront();
            }

            public void onDrawerOpened(View drawerView) {
                if (getSupportActionBar() != null)
                    getSupportActionBar().setTitle(R.string.app_name);
                invalidateOptionsMenu();
                mDrawerLayout.bringToFront();
            }

            public void onDrawerSlide(View drawerView, float offset) {
                if (offset != 0)
                    mDrawerLayout.bringToFront();
                else {
                    OpenPage.bringToFront();
                    mAdView.bringToFront();
                }
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
        mDrawerList.setItemChecked(1, true);
        mDrawerToggle.syncState();

    }

    public void visitPage(View v) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://moisescardona.me"));
        startActivity(browserIntent);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.

        return true;
    }

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
            di.mainDrawerItems(AboutApp.this, AboutApp.class, position, 1);
            selectItem(position);
        }
    }

    private void selectItem(int position) {
        // update selected item and title, then close the drawer
        mDrawerList.setItemChecked(position, true);
        mDrawerLayout.closeDrawer(mDrawerList);
    }
}

