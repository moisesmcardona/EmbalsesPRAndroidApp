package msc.app.embalsespuertorico

import android.content.res.Configuration
import android.os.Bundle
import com.google.android.material.tabs.TabLayout
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.viewpager.widget.ViewPager
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView

class MainActivityTab : AppCompatActivity(), TabLayout.OnTabSelectedListener {
    private var mDrawerLayout: DrawerLayout? = null
    private var mDrawerList: ListView? = null
    private var mDrawerToggle: ActionBarDrawerToggle? = null
    //This is our tablayout

    //This is our viewPager
    private var viewPager: ViewPager? = null

    override fun onTabSelected(tab: TabLayout.Tab) {
        viewPager!!.currentItem = tab.position
    }

    override fun onTabUnselected(tab: TabLayout.Tab) {

    }

    override fun onTabReselected(tab: TabLayout.Tab) {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.mainactivitytablayout)
        //Adding toolbar to the activity
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        val settings = app_settings(this)
        settings.initialize(this@MainActivityTab, MainActivityTab::class.java)
        //Initializing the tablayout
        val tabLayout: TabLayout = findViewById(R.id.tabLayout)

        //Adding the tabs using addTab() method
        if (settings.language == "Spanish") {
            tabLayout.addTab(tabLayout.newTab().setText(R.string.lista))
            tabLayout.addTab(tabLayout.newTab().setText(R.string.mapa))
        } else {
            tabLayout.addTab(tabLayout.newTab().setText(R.string.list))
            tabLayout.addTab(tabLayout.newTab().setText(R.string.map))
        }
        tabLayout.tabGravity = TabLayout.GRAVITY_FILL
        viewPager = findViewById(R.id.pager)

        //Creating our pager adapter
        val adapter = MainActivityPager(supportFragmentManager, tabLayout.tabCount)

        //Adding adapter to pager
        viewPager!!.adapter = adapter
        viewPager!!.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))

        //Adding onTabSelectedListener to swipe views
        tabLayout.addOnTabSelectedListener(this)
        viewPager!!.currentItem = settings.defaultView
        val drawerArrayList: Array<String> = resources.getStringArray(R.array.drawerMainActivityList)
        mDrawerLayout = findViewById(R.id.drawer_layout)
        mDrawerList = findViewById(R.id.left_drawer)
        mDrawerLayout!!.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START)
        // set up the drawer's list view with items and click listener
        if (settings.language == "Spanish")
            mDrawerList!!.adapter = ArrayAdapter(this, R.layout.drawerlistbox, drawerArrayList)
        else
            mDrawerList!!.adapter = ArrayAdapter(this, R.layout.drawerlistbox, resources.getStringArray(R.array.drawerMainActivityListEnglish))
        mDrawerList!!.onItemClickListener = DrawerItemClickListener()
        mDrawerToggle = object : ActionBarDrawerToggle(this, mDrawerLayout, 0, 0) {
            override fun onDrawerClosed(view: View) {
                if (supportActionBar != null)
                    supportActionBar!!.setTitle(R.string.app_name)
                window.decorView.findViewById<View>(android.R.id.content).invalidate()
            }

            override fun onDrawerOpened(drawerView: View) {
                if (supportActionBar != null)
                    supportActionBar!!.setTitle(R.string.app_name)
                invalidateOptionsMenu()
                mDrawerLayout!!.bringToFront()
            }

            override fun onDrawerSlide(drawerView: View, offset: Float) {
                if (offset != 0f)
                    mDrawerLayout!!.bringToFront()
            }
        }
        mDrawerLayout!!.addDrawerListener(mDrawerToggle!!)
        if (supportActionBar != null) {
            supportActionBar!!.setHomeAsUpIndicator(R.drawable.ic_drawer)
            supportActionBar!!.setDisplayHomeAsUpEnabled(true)
            supportActionBar!!.setHomeButtonEnabled(true)
        }
        mDrawerToggle!!.isDrawerIndicatorEnabled = true
        mDrawerLayout!!.addDrawerListener(mDrawerToggle!!)
        mDrawerList!!.setItemChecked(0, true)
        mDrawerToggle!!.syncState()
        val rmm = RateMeMaybe(this)
        rmm.setPromptMinimums(3, 2, 3, 2)
        //rmm.setPromptMinimums(0, 0, 0, 0);
        rmm.run()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.


        return if (mDrawerToggle!!.onOptionsItemSelected(item)) {
            true
        } else super.onOptionsItemSelected(item)
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle!!.syncState()
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        // Pass any configuration change to the drawer toggls
        mDrawerToggle!!.onConfigurationChanged(newConfig)
    }

    private inner class DrawerItemClickListener : AdapterView.OnItemClickListener {
        override fun onItemClick(parent: AdapterView<*>, view: View, position: Int, id: Long) {
            val di = DrawerItems()
            di.mainDrawerItems(this@MainActivityTab, MainActivityTab::class.java, position, 0)
            selectItem(position)
        }
    }

    private fun selectItem(position: Int) {
        // update selected item and title, then close the drawer
        mDrawerList!!.setItemChecked(position, true)
        mDrawerLayout!!.closeDrawer(mDrawerList!!)
    }
}
