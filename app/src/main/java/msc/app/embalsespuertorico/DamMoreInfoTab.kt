package msc.app.embalsespuertorico

import android.app.ProgressDialog
import android.content.Context
import android.content.res.Configuration
import android.os.AsyncTask
import android.os.Bundle
import android.os.StrictMode
import com.google.android.material.tabs.TabLayout
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.viewpager.widget.ViewPager
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView

import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.InterstitialAd
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.RequestConfiguration

import java.io.BufferedReader
import java.io.File
import java.io.FileOutputStream
import java.io.FileReader
import java.io.FileWriter
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.net.URL
import java.text.SimpleDateFormat
import java.util.*
import java.util.regex.Pattern

/**
 * Created by Moises Cardona on 2/7/2017.
 */
class DamMoreInfoTab : AppCompatActivity(), TabLayout.OnTabSelectedListener {
    private var mInterstitialAd: InterstitialAd? = null
    private var mDrawerLayout: DrawerLayout? = null
    private var mDrawerList: ListView? = null
    private var mDrawerToggle: ActionBarDrawerToggle? = null
    private var `as`: app_settings? = null
    //This is our viewPager
    private var viewPager: ViewPager? = null
    private var extras: Bundle? = null

    override fun onTabSelected(tab: TabLayout.Tab) {
        viewPager!!.currentItem = tab.position
    }

    override fun onTabUnselected(tab: TabLayout.Tab) {

    }

    override fun onTabReselected(tab: TabLayout.Tab) {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val drawerArrayList: Array<String>
        val tabLayout: TabLayout
        val root = getDir("data", Context.MODE_PRIVATE)

        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)

        mInterstitialAd = InterstitialAd(this)
        mInterstitialAd!!.adUnitId = getString(R.string.interstitialAd)
        val configuration = RequestConfiguration.Builder().setTestDeviceIds(listOf(getString(R.string.deviceTestID))).build()
        MobileAds.setRequestConfiguration(configuration)
        mInterstitialAd!!.loadAd(AdRequest.Builder().build())

        extras = intent.extras
        if (extras != null) {
            damvalue = extras!!.getString("DamID")
            if (damvalue != null) {
                when (damvalue) {
                    "50026140" -> {
                        damNameToDisplay = "Caonillas" //2 decimal places
                        FileNameToUse = "Caonillas"
                        desborde = "252"
                        seguridad = "248"
                        observacion = "244"
                        ajuste = "242"
                        control = "235"
                        imageToUse = R.drawable.caonillas
                        decimalplaces = 2
                        hasVideo = true
                        VideoID = "WScBOUKcnEg"
                    }
                    "50039995" -> {
                        damNameToDisplay = "Carite" //2 decimal places
                        FileNameToUse = "Carite"
                        desborde = "544"
                        seguridad = "542"
                        observacion = "539"
                        ajuste = "537"
                        control = "536"
                        imageToUse = R.drawable.carite
                        decimalplaces = 2
                        hasVideo = false
                    }
                    "50059000" -> {
                        damNameToDisplay = "Carraízo" //3
                        FileNameToUse = "Carraizo"
                        desborde = "41.14"
                        seguridad = "39.5"
                        observacion = "38.5"
                        ajuste = "36.5"
                        control = "30"
                        imageToUse = R.drawable.carraizo
                        decimalplaces = 3
                        hasVideo = true
                        VideoID = "EvnOmjxCyYY"
                    }
                    "50113950" -> {
                        damNameToDisplay = "Cerrillos" //2
                        FileNameToUse = "Cerrillos"
                        desborde = "173.4"
                        seguridad = "160"
                        observacion = "155.5"
                        ajuste = "149.4"
                        control = "137.2"
                        imageToUse = R.drawable.cerrillos
                        decimalplaces = 2
                        hasVideo = false
                    }
                    "50047550" -> {
                        damNameToDisplay = "Cidra" //2
                        FileNameToUse = "Cidra"
                        desborde = "403.05"
                        seguridad = "401.05"
                        observacion = "400.05"
                        ajuste = "399.05"
                        control = "398.05"
                        imageToUse = R.drawable.cidra
                        decimalplaces = 2
                        hasVideo = false
                    }
                    "50071225" -> {
                        damNameToDisplay = "Fajardo" //2
                        FileNameToUse = "Fajardo"
                        desborde = "52.5"
                        seguridad = "48.3"
                        observacion = "43.4"
                        ajuste = "37.5"
                        control = "26"
                        imageToUse = R.drawable.fajardo
                        decimalplaces = 2
                        hasVideo = false
                    }
                    "50010800" -> {
                        damNameToDisplay = "Guajataca" //2
                        FileNameToUse = "Guajataca"
                        desborde = "196"
                        seguridad = "194"
                        observacion = "190"
                        ajuste = "186"
                        control = "184"
                        imageToUse = R.drawable.guajataca
                        decimalplaces = 2
                        hasVideo = false
                    }
                    "50045000" -> {
                        damNameToDisplay = "La Plata" //3
                        FileNameToUse = "LaPlata"
                        desborde = "51"
                        seguridad = "43"
                        observacion = "39"
                        ajuste = "38"
                        control = "31"
                        imageToUse = R.drawable.laplata
                        decimalplaces = 3
                        hasVideo = false
                    }
                    "50093045" -> {
                        damNameToDisplay = "Patillas" //3
                        FileNameToUse = "Patillas"
                        desborde = "67.07"
                        seguridad = "66.16"
                        observacion = "64.33"
                        ajuste = "60.52"
                        control = "59.45"
                        imageToUse = R.drawable.patillas
                        decimalplaces = 3
                        hasVideo = false
                    }
                    "50076800" -> {
                        damNameToDisplay = "Rio Blanco" //3
                        FileNameToUse = "RioBlanco"
                        desborde = "28.75"
                        seguridad = "26.5"
                        observacion = "24.25"
                        ajuste = "22.5"
                        control = "18"
                        imageToUse = R.drawable.rioblanco
                        decimalplaces = 3
                        hasVideo = false
                    }
                    "50111210" -> {
                        damNameToDisplay = "Toa Vaca" //2
                        FileNameToUse = "ToaVaca"
                        desborde = "161"
                        seguridad = "152"
                        observacion = "145"
                        ajuste = "139"
                        control = "133"
                        imageToUse = R.drawable.toavaca
                        decimalplaces = 2
                        hasVideo = false
                    }
                }
                MetersFileToUse = root.toString() + "/" + FileNameToUse + "Meter.txt"
                DateFileToUse = root.toString() + "/" + FileNameToUse + "Date.txt"
                DateFileToUseEnglish = root.toString() + "/" + FileNameToUse + "English.txt"
                LastHourFileToUse = root.toString() + "/" + FileNameToUse + "LastHour.txt"
                SixHoursFileToUse = root.toString() + "/" + FileNameToUse + "6Hours.txt"
                TwelveHoursFileToUse = root.toString() + "/" + FileNameToUse + "12Hours.txt"
                TwentyFourHoursFileToUse = root.toString() + "/" + FileNameToUse + "24Hours.txt"
                SevenDaysFileToUse = root.toString() + "/" + FileNameToUse + "7Days.txt"
                days14filetouse = root.toString() + "/" + FileNameToUse + "14Days.txt"
                days30filetouse = root.toString() + "/" + FileNameToUse + "30Days.txt"
                days60filetouse = root.toString() + "/" + FileNameToUse + "60Days.txt"
                days90filetouse = root.toString() + "/" + FileNameToUse + "90Days.txt"
                HistoryFileToUse = root.toString() + "/" + FileNameToUse + "History.txt"
                HistoryFileToUseEnglish = root.toString() + "/" + FileNameToUse + "HistoryEnglish.txt"
                HistoryMeterFileToUse = root.toString() + "/" + FileNameToUse + "MeterHistory.txt"
            }
            setContentView(R.layout.damdatatab)
            mDrawerList = findViewById(R.id.left_drawer)
            //Adding toolbar to the activity
            val toolbar = findViewById<Toolbar>(R.id.toolbar)
            setSupportActionBar(toolbar)

            //Initializing the tablayout
            tabLayout = findViewById(R.id.tabLayout)

            //Adding the tabs using addTab() method
            `as` = app_settings(this)
            if (`as`!!.language == "Spanish") {
                tabLayout.addTab(tabLayout.newTab().setText(R.string.datos))
                tabLayout.addTab(tabLayout.newTab().setText(R.string.mas_datos))
                tabLayout.addTab(tabLayout.newTab().setText(R.string.historial))
                tabLayout.addTab(tabLayout.newTab().setText(R.string.graficas))
                tabLayout.addTab(tabLayout.newTab().setText(R.string.mapa))
            } else {
                tabLayout.addTab(tabLayout.newTab().setText(R.string.data))
                tabLayout.addTab(tabLayout.newTab().setText(R.string.more_data))
                tabLayout.addTab(tabLayout.newTab().setText(R.string.history_tab))
                tabLayout.addTab(tabLayout.newTab().setText(R.string.graphs))
                tabLayout.addTab(tabLayout.newTab().setText(R.string.map))
            }
            tabLayout.tabGravity = TabLayout.GRAVITY_FILL
            viewPager = findViewById(R.id.pager)

            //Creating our pager adapter
            val adapter = DamMoreInfoPager(supportFragmentManager, tabLayout.tabCount)

            //Adding adapter to pager
            viewPager!!.adapter = adapter
            viewPager!!.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))

            //Adding onTabSelectedListener to swipe views
            tabLayout.addOnTabSelectedListener(this)

            mDrawerList = findViewById(R.id.left_drawer)
            drawerArrayList = if (!hasVideo) {
                if (`as`!!.language == "Spanish") {
                    resources.getStringArray(R.array.drawerDamListNoVideo)

                } else {
                    resources.getStringArray(R.array.drawerDamListNoVideoEnglish)
                }
            } else {
                if (`as`!!.language == "Spanish") {
                    resources.getStringArray(R.array.drawerDamListWithVideo)
                } else {
                    resources.getStringArray(R.array.drawerDamListWithVideoEnglish)
                }
            }

            mDrawerLayout = findViewById(R.id.drawer_layout)
            mDrawerList = findViewById(R.id.left_drawer)
            mDrawerLayout!!.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START)
            // set up the drawer's list view with items and click listener
            mDrawerList!!.adapter = ArrayAdapter(this, R.layout.drawerlistbox, drawerArrayList)
            mDrawerList!!.onItemClickListener = DrawerItemClickListener()
            mDrawerToggle = object : ActionBarDrawerToggle(this, mDrawerLayout, 0, 0) {
                override fun onDrawerClosed(drawerView: View) {
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
            //mDrawerList.setItemChecked(2, true);
            mDrawerToggle!!.syncState()
            LoadData(this).execute()
        }
    }

    private inner class LoadData internal constructor(context: DamMoreInfoTab) : AsyncTask<Void, String, Void>() {
        internal var dialog: ProgressDialog? = null

        init {
            dialog = ProgressDialog(this@DamMoreInfoTab)
        }

        override fun onPreExecute() {
            //set message of the dialog
            if (`as`!!.language == "Spanish") {
                dialog!!.setMessage("Cargando datos... Paso 1 de 3")
            } else {
                dialog!!.setMessage("Loading... Step 1 of 3")
            }

            //show dialog
            dialog!!.show()
            dialog!!.setCancelable(false)
            dialog!!.setCanceledOnTouchOutside(false)
            super.onPreExecute()
        }

        override fun onProgressUpdate(vararg params: String) {
            dialog!!.setMessage(params[0])
        }

        override fun doInBackground(vararg params: Void): Void? {

            val url: URL
            var `is`: InputStream? = null
            val br: BufferedReader

            val getDate: String

            var getDate2: String
            datahistory.clear()
            datahistoryEnglish.clear()
            datahistorymeters.clear()
            time.clear()
            meters.clear()
            try {
                url = URL("https://nwis.waterdata.usgs.gov/pr/nwis/uv/?cb_62616=on&format=rdb&site_no=$damvalue&period=90")
                `is` = url.openStream()  //must use strict policy to avoid exception
                br = BufferedReader(InputStreamReader(`is`))
                var currentline: Array<String>
                if (`as`!!.language == "Spanish") {
                    publishProgress("Cargando datos... Paso 2 de 3")
                } else {
                    publishProgress("Loading... Step 2 of 3")
                }
                val double = Pattern.compile("\\d")
                br.lineSequence().forEach { it ->
                    if (!it.contains("#") && it.contains("USGS")) {
                        currentline = it.split("\t".toRegex()).dropLastWhile({ it.isEmpty() }).toTypedArray()
                        if (currentline.size >= 5) {
                            if (double.matcher(currentline[4]).find()) {
                                time.add(currentline[2])
                                meters.add(currentline[4])
                            }
                        }
                    }
                }
                if (`as`!!.language == "Spanish") {
                    publishProgress("Cargando datos... Paso 3 de 3")
                } else {
                    publishProgress("Loading... Step 3 of 3")
                }
                if (meters.size > 2) {
                    damlevel = meters[meters.size - 1]
                    val outputStream1 = FileOutputStream(MetersFileToUse, false)
                    val out1 = OutputStreamWriter(outputStream1)
                    out1.write(damlevel)
                    out1.close()
                    getDate = time[time.size - 1]
                    val f = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.US)
                    val d: Date? = f.parse(getDate)
                    val oneHourBack = Date(d!!.time - 60.toLong() * 60 * 1000)
                    val sixHourBack = Date(d.time - 60.toLong() * 60 * 1000 * 6)
                    val twelveHourBack = Date(d.time - 60.toLong() * 60 * 1000 * 12)
                    val twentyfourHourBack = Date(d.time - 60.toLong() * 60 * 1000 * 24)
                    val sevenDaysBack = Date(d.time - 60.toLong() * 60 * 1000 * 24 * 7)
                    val days14Back = Date(d.time - 60.toLong() * 60 * 1000 * 24 * 14)
                    val days30Back = Date(d.time - 60.toLong() * 60 * 1000 * 24 * 30)
                    val days60Back = Date(d.time - 60.toLong() * 60 * 1000 * 24 * 60)
                    val days90Back = Date(d.time - 60.toLong() * 60 * 1000 * 24 * 90)
                    val dateFormatter = SimpleDateFormat("dd/MM/yyyy", Locale.US)
                    val dateFormatterEnglish = SimpleDateFormat("MM/dd/yyyy", Locale.US)
                    val timeFormatter = SimpleDateFormat("hh:mm aa", Locale.US)
                    val timeBack = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.US)
                    val dateAsString = dateFormatter.format(d)
                    val dateAsStringEnglish = dateFormatterEnglish.format(d)
                    val timeAsString = timeFormatter.format(d)
                    val oneHourBackAsString = timeBack.format(oneHourBack)
                    val sixHoursBackAsString = timeBack.format(sixHourBack)
                    val twelveHoursBackAsString = timeBack.format(twelveHourBack)
                    val twentyfourHoursBackAsString = timeBack.format(twentyfourHourBack)
                    val sevenDaysBackAsString = timeBack.format(sevenDaysBack)
                    val daysBack14AsString = timeBack.format(days14Back)
                    val daysBack30AsString = timeBack.format(days30Back)
                    val daysBack60AsString = timeBack.format(days60Back)
                    val daysBack90AsString = timeBack.format(days90Back)
                    damdate = "Lectura realizada el $dateAsString a las $timeAsString"
                    damdateEnglish = "Reservoir statistics made at $timeAsString on $dateAsStringEnglish"
                    val outputStream2 = FileOutputStream(DateFileToUse, false)
                    val outputStream2English = FileOutputStream(DateFileToUseEnglish, false)
                    val out2 = OutputStreamWriter(outputStream2)
                    val out2English = OutputStreamWriter(outputStream2English)
                    out2.write(damdate)
                    out2English.write(damdateEnglish)
                    out2.close()
                    out2English.close()
                    noDataAvailable = false
                    for (i in 0 until time.size - 1) {
                        var fileToUse: String? = null
                        var dataToWrite: String? = null
                        if (time[i] == oneHourBackAsString) {
                            damlasthour = meters[i]
                            fileToUse = LastHourFileToUse
                            dataToWrite = damlasthour
                            lastHourAvailable = true
                        } else if (time[i] == sixHoursBackAsString) {
                            dam6hours = meters[i]
                            fileToUse = SixHoursFileToUse
                            dataToWrite = dam6hours
                            sixHoursAvailable = true
                        } else if (time[i] == twelveHoursBackAsString) {
                            dam12hours = meters[i]
                            fileToUse = TwelveHoursFileToUse
                            dataToWrite = dam12hours
                            twelveHoursAvailable = true
                        } else if (time[i] == twentyfourHoursBackAsString) {
                            dam24hours = meters[i]
                            fileToUse = TwentyFourHoursFileToUse
                            dataToWrite = dam24hours
                            twentyFourHoursAvailable = true
                        } else if (time[i] == sevenDaysBackAsString) {
                            dam7days = meters[i]
                            fileToUse = SevenDaysFileToUse
                            dataToWrite = dam7days
                            sevenDaysAvailable = true
                        } else if (time[i] == daysBack14AsString) {
                            dam14days = meters[i]
                            fileToUse = days14filetouse
                            dataToWrite = dam14days
                            days14available = true
                        } else if (time[i] == daysBack30AsString) {
                            dam30days = meters[i]
                            fileToUse = days30filetouse
                            dataToWrite = dam30days
                            days30available = true
                        } else if (time[i] == daysBack60AsString) {
                            dam60days = meters[i]
                            fileToUse = days60filetouse
                            dataToWrite = dam60days
                            days60available = true
                        } else if (time[i] == daysBack90AsString) {
                            dam90days = meters[i]
                            fileToUse = days90filetouse
                            dataToWrite = dam90days
                            days90available = true
                        }
                        if (fileToUse != null && dataToWrite != null) {
                            val outputStream3 = FileOutputStream(fileToUse, false)
                            val out3 = OutputStreamWriter(outputStream3)
                            out3.write(dataToWrite)
                            out3.close()
                        }
                    }
                    val writer = FileWriter(HistoryFileToUse, false)
                    val writerEnglish = FileWriter(HistoryFileToUseEnglish, false)
                    var dataToWrite: String
                    var dataToWriteEnglish: String
                    val writer2 = FileWriter(HistoryMeterFileToUse, false)
                    val f2 = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.US)
                    val timeFormatted = SimpleDateFormat("dd/MM/yyyy hh:mm aa", Locale.US)
                    val timeFormattedEnglish = SimpleDateFormat("MM/dd/yyyy hh:mm aa", Locale.US)
                    var timeFormattedAsString: String
                    var timeFormattedAsStringEnglish: String
                    for (i in time.size - 1 downTo 2) {
                        getDate2 = time[i]
                        val d2: Date? = f2.parse(getDate2)
                        timeFormattedAsString = timeFormatted.format(d2!!)
                        timeFormattedAsStringEnglish = timeFormattedEnglish.format(d2)
                        dataToWrite = timeFormattedAsString + " - " + meters[i] + " metros"
                        dataToWriteEnglish = timeFormattedAsStringEnglish + " - " + meters[i] + " meters"
                        datahistory.add(dataToWrite)
                        datahistoryEnglish.add(dataToWriteEnglish)
                        datahistorymeters.add(meters[i])
                        historyAvailable = true
                        writer.write(dataToWrite + System.getProperty("line.separator")!!)
                        writerEnglish.write(dataToWriteEnglish + System.getProperty("line.separator")!!)
                        writer2.write(meters[i] + System.getProperty("line.separator")!!)
                    }
                    writer.close()
                    writerEnglish.close()
                    writer2.close()
                } else {
                    noDataAvailable = true
                }
            } catch (e: Exception) {
                Log.e("Error", e.toString())
                val metersFileLocation = File(MetersFileToUse)
                val dateFileLocation = File(DateFileToUse)
                val dateFileLocationEnglish = File(DateFileToUseEnglish)
                val lastHourFileLocation = File(LastHourFileToUse)
                val sixHoursFileLocation = File(SixHoursFileToUse)
                val twelveHoursFileLocation = File(TwelveHoursFileToUse)
                val twentyFourHoursFileLocation = File(TwentyFourHoursFileToUse)
                val sevenDaysFileLocation = File(SevenDaysFileToUse)
                val days14FileLocation = File(days14filetouse)
                val days30FileLocation = File(days30filetouse)
                val days60FileLocation = File(days60filetouse)
                val days90FileLocation = File(days90filetouse)
                val historyFileLocation = File(HistoryFileToUse)
                val historyMetersFileLocation = File(HistoryMeterFileToUse)
                try {
                    if (metersFileLocation.exists() && dateFileLocation.exists() && dateFileLocationEnglish.exists()) {
                        noDataAvailable = false
                        val in1 = BufferedReader(FileReader(MetersFileToUse))
                        in1.lineSequence().forEach {
                            damlevel = it
                        }
                        in1.close()
                        val in2 = BufferedReader(FileReader(DateFileToUse))
                        in2.lineSequence().forEach {
                            damdate = it
                        }
                        in2.close()
                        val in2English = BufferedReader(FileReader(DateFileToUseEnglish))
                        in2English.lineSequence().forEach {
                            damdateEnglish = it
                        }
                        in2English.close()
                        if (lastHourFileLocation.exists()) {
                            val in3 = BufferedReader(FileReader(LastHourFileToUse))
                            in3.lineSequence().forEach {
                                damlasthour = it
                                lastHourAvailable = true
                            }
                            if (damlasthour == "") {
                                lastHourAvailable = false
                            }
                            in3.close()
                        } else {
                            lastHourAvailable = false
                        }
                        if (sixHoursFileLocation.exists()) {
                            val in4 = BufferedReader(FileReader(SixHoursFileToUse))
                            in4.lineSequence().forEach {
                                dam6hours = it
                                sixHoursAvailable = true
                            }
                            if (dam6hours == "") {
                                sixHoursAvailable = false
                            }
                            in4.close()
                        } else {
                            sixHoursAvailable = false
                        }
                        if (twelveHoursFileLocation.exists()) {
                            val in5 = BufferedReader(FileReader(TwelveHoursFileToUse))
                            in5.lineSequence().forEach {
                                dam12hours = it
                                twelveHoursAvailable = true
                            }
                            if (dam12hours == "") {
                                twelveHoursAvailable = false
                            }
                            in5.close()
                        } else {
                            twelveHoursAvailable = false
                        }
                        if (twentyFourHoursFileLocation.exists()) {
                            val in6 = BufferedReader(FileReader(TwentyFourHoursFileToUse))
                            in6.lineSequence().forEach {
                                dam24hours = it
                                twentyFourHoursAvailable = true
                            }
                            if (dam24hours == "") {
                                twentyFourHoursAvailable = false
                            }
                            in6.close()
                        } else {
                            twentyFourHoursAvailable = false
                        }
                        if (sevenDaysFileLocation.exists()) {
                            val in7 = BufferedReader(FileReader(SevenDaysFileToUse))
                            in7.lineSequence().forEach {
                                dam7days = it
                                sevenDaysAvailable = true
                            }
                            if (dam7days == "") {
                                sevenDaysAvailable = false
                            }
                            in7.close()
                        } else
                            sevenDaysAvailable = false
                        if (days14FileLocation.exists()) {
                            val in8 = BufferedReader(FileReader(days14filetouse))
                            in8.lineSequence().forEach {
                                dam14days = it
                                days14available = true
                            }
                            if (dam14days == "") {
                                days14available = false
                            }
                            in8.close()
                        } else
                            days14available = false
                        if (days30FileLocation.exists()) {
                            val in9 = BufferedReader(FileReader(days30filetouse))
                            in9.lineSequence().forEach {
                                dam30days = it
                                days30available = true
                            }
                            if (dam30days == "") {
                                days30available = false
                            }
                            in9.close()
                        } else
                            days30available = false
                        if (days60FileLocation.exists()) {
                            val in10 = BufferedReader(FileReader(days60filetouse))
                            in10.lineSequence().forEach {
                                dam60days = it
                                days60available = true
                            }
                            if (dam60days == "") {
                                days60available = false
                            }
                            in10.close()
                        } else
                            days60available = false
                        if (days90FileLocation.exists()) {
                            val in11 = BufferedReader(FileReader(days90filetouse))
                            in11.lineSequence().forEach {
                                dam90days = it
                                days90available = true
                            }
                            if (dam90days == "") {
                                days90available = false
                            }
                            in11.close()
                        } else
                            days90available = false
                        if (historyFileLocation.exists() && historyMetersFileLocation.exists()) {
                            val in12 = BufferedReader(FileReader(HistoryFileToUse))
                            val in12English = BufferedReader(FileReader(HistoryFileToUseEnglish))
                            val in13 = BufferedReader(FileReader(HistoryMeterFileToUse))
                            in12.lineSequence().forEach {
                                datahistory.add(it)
                                historyAvailable = true
                            }
                            in12English.lineSequence().forEach {
                                datahistoryEnglish.add(it)
                                historyAvailableEnglish = true
                            }
                            in13.lineSequence().forEach {
                                datahistorymeters.add(it)
                            }
                            if (datahistory.size == 0 || datahistorymeters.size == 0) {
                                historyAvailable = false
                            }
                            if (datahistoryEnglish.size == 0 || datahistorymeters.size == 0) {
                                historyAvailableEnglish = false
                            }
                            in12.close()
                            in12English.close()
                            in13.close()
                        } else {
                            historyAvailable = false
                            historyAvailableEnglish = false
                        }
                    } else {
                        noDataAvailable = true
                        noDataAvailableEnglish = true
                        lastHourAvailable = false
                        sixHoursAvailable = false
                        twelveHoursAvailable = false
                        twentyFourHoursAvailable = false
                        sevenDaysAvailable = false
                        historyAvailable = false
                        historyAvailableEnglish = false
                    }
                } catch (e2: Exception) {
                    //nothing to show
                }

            } finally {
                try {
                    `is`?.close()
                } catch (ioe: IOException) {
                    // nothing to see here
                }
            }
            return null
        }

        override fun onPostExecute(result: Void?) {

            if (dialog != null && dialog!!.isShowing)
                dialog!!.dismiss()
            if (viewPager!!.adapter != null)
                viewPager!!.adapter!!.notifyDataSetChanged()
            if (mInterstitialAd!!.isLoaded) {
                mInterstitialAd!!.show()
            } else {
                Log.d("TAG", "The interstitial wasn't loaded yet.")
            }
        }
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
        if (mDrawerToggle != null)
            mDrawerToggle!!.syncState()
        else {
            val intent = MainActivity.damdata
            startActivity(intent)
            finish()
        }
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        // Pass any configuration change to the drawer toggls
        mDrawerToggle!!.onConfigurationChanged(newConfig)
    }

    private inner class DrawerItemClickListener : AdapterView.OnItemClickListener {
        override fun onItemClick(parent: AdapterView<*>, view: View, position: Int, id: Long) {
            val di = DrawerItems()
            di.mainDrawerItems(this@DamMoreInfoTab, DamMoreInfoTab::class.java, position, 999, hasVideo, VideoID)
            selectItem(position)
        }
    }

    private fun selectItem(position: Int) {
        // update selected item and title, then close the drawer
        mDrawerList!!.setItemChecked(position, true)
        mDrawerLayout!!.closeDrawer(mDrawerList!!)
    }

    companion object {
        var hasVideo = false
        var VideoID = ""
        var damNameToDisplay = ""
        var damvalue: String? = "0.0"
        var damlevel = "0.0"
        var damlasthour = "0.0"
        var dam6hours = "0.0"
        var dam12hours = "0.0"
        var dam24hours = "0.0"
        var dam7days = "0.0"
        var dam14days = "0.0"
        var dam30days = "0.0"
        var dam60days = "0.0"
        var dam90days = "0.0"
        var noDataAvailable = false
        var noDataAvailableEnglish = false
        var lastHourAvailable = false
        var sixHoursAvailable = false
        var twelveHoursAvailable = false
        var twentyFourHoursAvailable = false
        var sevenDaysAvailable = false
        var days14available = false
        var days30available = false
        var days60available = false
        var days90available = false
        var historyAvailable = false
        var historyAvailableEnglish = false
        var imageToUse = 0
        var damdate = ""
        var damdateEnglish = ""
        var datahistory = ArrayList<String>()
        var datahistoryEnglish = ArrayList<String>()
        var datahistorymeters = ArrayList<String>()
        var time = ArrayList<String>()
        var meters = ArrayList<String>()
        private var FileNameToUse = ""
        private var MetersFileToUse = ""
        private var DateFileToUse = ""
        private var DateFileToUseEnglish = ""
        private var LastHourFileToUse = ""
        private var SixHoursFileToUse = ""
        private var TwelveHoursFileToUse = ""
        private var TwentyFourHoursFileToUse = ""
        private var SevenDaysFileToUse = ""
        private var days14filetouse = ""
        private var days30filetouse = ""
        private var days60filetouse = ""
        private var days90filetouse = ""
        private var HistoryFileToUse = ""
        private var HistoryFileToUseEnglish = ""
        private var HistoryMeterFileToUse = ""
        var desborde = ""
        var seguridad = ""
        var observacion = ""
        var ajuste = ""
        var control = ""
        var decimalplaces = 0
    }
}
