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
import android.widget.*

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
        val websiteText = findViewById<TextView>(R.id.textView4)
        val textview = findViewById<TextView>(R.id.textView)
        val adFrame = findViewById<FrameLayout>(R.id.aboudAd)
        val mAdFunctions = AdFunctions()
        mAdFunctions.loadBanner(adFrame, R.string.aboutad, this)
        mDrawerList = findViewById(R.id.left_drawer)
        val `as` = app_settings(this)
        if (`as`.language == "Spanish") {
            mDrawerList!!.adapter = ArrayAdapter(this, R.layout.drawerlistbox, resources.getStringArray(R.array.drawerMainActivityList))
            textview.setText(R.string.acerca_de)
        } else {
            mDrawerList!!.adapter = ArrayAdapter(this, R.layout.drawerlistbox, resources.getStringArray(R.array.drawerMainActivityList))
            textview.setText(R.string.about_app)
        }


        mDrawerLayout = findViewById(R.id.drawer_layout)
        mDrawerList = findViewById(R.id.left_drawer)
        mDrawerLayout!!.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START)
        mDrawerList!!.onItemClickListener = DrawerItemClickListener()
        mDrawerToggle = object : ActionBarDrawerToggle(this, mDrawerLayout, 0, 0) {
            override fun onDrawerClosed(view: View) {
                if (supportActionBar != null)
                    supportActionBar!!.setTitle(R.string.app_name)
                window.decorView.findViewById<View>(android.R.id.content).invalidate()
                websiteText.bringToFront()
                adFrame.bringToFront()
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
                    websiteText.bringToFront()
                    adFrame.bringToFront()
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

    fun visitPage(view: View) {
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
            val di = DrawerItems()
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

