package msc.app.embalsespuertorico

import android.content.Intent
import android.content.res.Configuration
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView

import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.RequestConfiguration
import java.util.*

/**
 * Created by Moisés Cardona on 9/25/2015.
 */

class AboutApp : AppCompatActivity() {
    private var mDrawerLayout: DrawerLayout? = null
    private var mDrawerList: ListView? = null
    private var mDrawerToggle: ActionBarDrawerToggle? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.aboutapp)
        val OpenPage = findViewById<TextView>(R.id.textView4)
        val textview = findViewById<TextView>(R.id.textView)
        val mAdView = findViewById<AdView>(R.id.aboutad)
        mDrawerList = findViewById(R.id.left_drawer)
        val `as` = app_settings(this)
        if (`as`.language == "Spanish") {
            mDrawerList!!.adapter = ArrayAdapter(this, R.layout.drawerlistbox, resources.getStringArray(R.array.drawerMainActivityList))
            textview.setText(R.string.acerca_de)
        } else {
            mDrawerList!!.adapter = ArrayAdapter(this, R.layout.drawerlistbox, resources.getStringArray(R.array.drawerMainActivityList))
            textview.setText(R.string.about_app)
        }
        val configuration = RequestConfiguration.Builder().setTestDeviceIds(Arrays.asList(getString(R.string.deviceTestID))).build()
        MobileAds.setRequestConfiguration(configuration)
        val adRequest = AdRequest.Builder().build()
        mAdView.loadAd(adRequest)
        mDrawerLayout = findViewById(R.id.drawer_layout)
        mDrawerList = findViewById(R.id.left_drawer)
        mDrawerLayout!!.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START)
        mDrawerList!!.setOnItemClickListener(DrawerItemClickListener())
        mDrawerToggle = object : ActionBarDrawerToggle(this, mDrawerLayout, 0, 0) {
            override fun onDrawerClosed(view: View) {
                if (supportActionBar != null)
                    supportActionBar!!.setTitle(R.string.app_name)
                window.decorView.findViewById<View>(android.R.id.content).invalidate()
                OpenPage.bringToFront()
                mAdView.bringToFront()
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
                else {
                    OpenPage.bringToFront()
                    mAdView.bringToFront()
                }
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
        mDrawerList!!.setItemChecked(1, true)
        mDrawerToggle!!.syncState()

    }

    fun visitPage() {
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://moisescardona.me"))
        startActivity(browserIntent)
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
            val di = drawerItems()
            di.mainDrawerItems(this@AboutApp, AboutApp::class.java, position, 1)
            selectItem(position)
        }
    }

    private fun selectItem(position: Int) {
        // update selected item and title, then close the drawer
        mDrawerList!!.setItemChecked(position, true)
        mDrawerLayout!!.closeDrawer(mDrawerList!!)
    }
}

