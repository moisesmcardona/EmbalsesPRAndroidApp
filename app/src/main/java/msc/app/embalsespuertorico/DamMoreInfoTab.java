package msc.app.embalsespuertorico;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.res.Configuration;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.design.widget.TabLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Moises Cardona on 2/7/2017.
 */
public class DamMoreInfoTab extends AppCompatActivity implements TabLayout.OnTabSelectedListener {
    private InterstitialAd mInterstitialAd;
    public static boolean hasVideo = false;
    public static String VideoID = "";
    public static String damNameToDisplay = "";
    public static String damvalue = "0.0";
    public static String damlevel = "0.0", damlasthour = "0.0", dam6hours = "0.0", dam12hours = "0.0", dam24hours = "0.0", dam7days = "0.0", dam14days = "0.0", dam30days = "0.0", dam60days = "0.0", dam90days = "0.0";
    public static boolean noDataAvailable = false, noDataAvailableEnglish = false, lastHourAvailable = false, sixHoursAvailable = false, twelveHoursAvailable = false, twentyFourHoursAvailable = false, sevenDaysAvailable = false, days14available = false, days30available = false, days60available = false, days90available = false, historyAvailable = false, historyAvailableEnglish = false;
    public static int imageToUse = 0;
    public static String damdate = "", damdateEnglish = "";
    public static ArrayList<String> datahistory = new ArrayList<>();
    public static ArrayList<String> datahistoryEnglish = new ArrayList<>();
    public static ArrayList<String> datahistorymeters = new ArrayList<>();
    public static ArrayList<String> time = new ArrayList<>();
    public static ArrayList<String> meters = new ArrayList<>();
    private static String FileNameToUse = "", MetersFileToUse = "", DateFileToUse = "", DateFileToUseEnglish = "", LastHourFileToUse = "", SixHoursFileToUse = "", TwelveHoursFileToUse = "", TwentyFourHoursFileToUse = "", SevenDaysFileToUse = "", days14filetouse = "", days30filetouse = "", days60filetouse = "", days90filetouse = "", HistoryFileToUse = "", HistoryFileToUseEnglish = "", HistoryMeterFileToUse = "";
    public static String desborde = "", seguridad = "", observacion = "", ajuste = "", control = "";
    public static int decimalplaces = 0;
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;
    private app_settings as;
    //This is our viewPager
    public ViewPager viewPager;

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
        super.onCreate(savedInstanceState);
        String[] DrawerArrayList;
        TabLayout tabLayout;
        final File root = getDir("data", Context.MODE_PRIVATE);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-1744946688565368/6370827222");
        mInterstitialAd.loadAd(new AdRequest.Builder().addTestDevice("03929FBEA0721F82E4AEE15546DBB5DC").build());

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            damvalue = extras.getString("DamID");
            if (damvalue != null) {
                switch (damvalue) {
                    case "50026140":
                        damNameToDisplay = "Caonillas"; //2 decimal places
                        FileNameToUse = "Caonillas";
                        desborde = "252";
                        seguridad = "248";
                        observacion = "244";
                        ajuste = "242";
                        control = "235";
                        imageToUse = R.drawable.caonillas;
                        decimalplaces = 2;
                        hasVideo = true;
                        VideoID = "WScBOUKcnEg";

                        break;
                    case "50039995":
                        damNameToDisplay = "Carite"; //2 decimal places
                        FileNameToUse = "Carite";
                        desborde = "544";
                        seguridad = "542";
                        observacion = "539";
                        ajuste = "537";
                        control = "536";
                        imageToUse = R.drawable.carite;
                        decimalplaces = 2;
                        hasVideo = false;
                        break;
                    case "50059000":
                        damNameToDisplay = "Carraízo"; //3
                        FileNameToUse = "Carraizo";
                        desborde = "41.14";
                        seguridad = "39.5";
                        observacion = "38.5";
                        ajuste = "36.5";
                        control = "30";
                        imageToUse = R.drawable.carraizo;
                        decimalplaces = 3;
                        hasVideo = true;
                        VideoID = "EvnOmjxCyYY";
                        break;
                    case "50113950":
                        damNameToDisplay = "Cerrillos"; //2
                        FileNameToUse = "Cerrillos";
                        desborde = "173.4";
                        seguridad = "160";
                        observacion = "155.5";
                        ajuste = "149.4";
                        control = "137.2";
                        imageToUse = R.drawable.cerrillos;
                        decimalplaces = 2;
                        hasVideo = false;
                        break;
                    case "50047550":
                        damNameToDisplay = "Cidra"; //2
                        FileNameToUse = "Cidra";
                        desborde = "403.05";
                        seguridad = "401.05";
                        observacion = "400.05";
                        ajuste = "399.05";
                        control = "398.05";
                        imageToUse = R.drawable.cidra;
                        decimalplaces = 2;
                        hasVideo = false;
                        break;
                    case "50071225":
                        damNameToDisplay = "Fajardo"; //2
                        FileNameToUse = "Fajardo";
                        desborde = "52.5";
                        seguridad = "48.3";
                        observacion = "43.4";
                        ajuste = "37.5";
                        control = "26";
                        imageToUse = R.drawable.fajardo;
                        decimalplaces = 2;
                        hasVideo = false;
                        break;
                    case "50010800":
                        damNameToDisplay = "Guajataca"; //2
                        FileNameToUse = "Guajataca";
                        desborde = "196";
                        seguridad = "194";
                        observacion = "190";
                        ajuste = "186";
                        control = "184";
                        imageToUse = R.drawable.guajataca;
                        decimalplaces = 2;
                        hasVideo = false;
                        break;
                    case "50045000":
                        damNameToDisplay = "La Plata"; //3
                        FileNameToUse = "LaPlata";
                        desborde = "51";
                        seguridad = "43";
                        observacion = "39";
                        ajuste = "38";
                        control = "31";
                        imageToUse = R.drawable.laplata;
                        decimalplaces = 3;
                        hasVideo = false;
                        break;
                    case "50093045":
                        damNameToDisplay = "Patillas"; //3
                        FileNameToUse = "Patillas";
                        desborde = "67.07";
                        seguridad = "66.16";
                        observacion = "64.33";
                        ajuste = "60.52";
                        control = "59.45";
                        imageToUse = R.drawable.patillas;
                        decimalplaces = 3;
                        hasVideo = false;
                        break;
                    case "50076800":
                        damNameToDisplay = "Rio Blanco"; //3
                        FileNameToUse = "RioBlanco";
                        desborde = "28.75";
                        seguridad = "26.5";
                        observacion = "24.25";
                        ajuste = "22.5";
                        control = "18";
                        imageToUse = R.drawable.rioblanco;
                        decimalplaces = 3;
                        hasVideo = false;
                        break;
                    case "50111210":
                        damNameToDisplay = "Toa Vaca"; //2
                        FileNameToUse = "ToaVaca";
                        desborde = "161";
                        seguridad = "152";
                        observacion = "145";
                        ajuste = "139";
                        control = "133";
                        imageToUse = R.drawable.toavaca;
                        decimalplaces = 2;
                        hasVideo = false;
                        break;
                }
                MetersFileToUse = root + "/" + FileNameToUse + "Meter.txt";
                DateFileToUse = root + "/" + FileNameToUse + "Date.txt";
                DateFileToUseEnglish = root + "/" + FileNameToUse + "English.txt";
                LastHourFileToUse = root + "/" + FileNameToUse + "LastHour.txt";
                SixHoursFileToUse = root + "/" + FileNameToUse + "6Hours.txt";
                TwelveHoursFileToUse = root + "/" + FileNameToUse + "12Hours.txt";
                TwentyFourHoursFileToUse = root + "/" + FileNameToUse + "24Hours.txt";
                SevenDaysFileToUse = root + "/" + FileNameToUse + "7Days.txt";
                days14filetouse = root + "/" + FileNameToUse + "14Days.txt";
                days30filetouse = root + "/" + FileNameToUse + "30Days.txt";
                days60filetouse = root + "/" + FileNameToUse + "60Days.txt";
                days90filetouse = root + "/" + FileNameToUse + "90Days.txt";
                HistoryFileToUse = root + "/" + FileNameToUse + "History.txt";
                HistoryFileToUseEnglish = root + "/" + FileNameToUse + "HistoryEnglish.txt";
                HistoryMeterFileToUse = root + "/" + FileNameToUse + "MeterHistory.txt";
            }
            setContentView(R.layout.damdatatab);
            mDrawerList = findViewById(R.id.left_drawer);
            //Adding toolbar to the activity
            Toolbar toolbar = findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);

            //Initializing the tablayout
            tabLayout = findViewById(R.id.tabLayout);

            //Adding the tabs using addTab() method
            as = new app_settings(this);
            if (as.getLanguage().equals("Spanish")) {
                tabLayout.addTab(tabLayout.newTab().setText(R.string.datos));
                tabLayout.addTab(tabLayout.newTab().setText(R.string.mas_datos));
                tabLayout.addTab(tabLayout.newTab().setText(R.string.historial));
                tabLayout.addTab(tabLayout.newTab().setText(R.string.graficas));
                tabLayout.addTab(tabLayout.newTab().setText(R.string.mapa));
            } else {
                tabLayout.addTab(tabLayout.newTab().setText(R.string.data));
                tabLayout.addTab(tabLayout.newTab().setText(R.string.more_data));
                tabLayout.addTab(tabLayout.newTab().setText(R.string.history));
                tabLayout.addTab(tabLayout.newTab().setText(R.string.graphs));
                tabLayout.addTab(tabLayout.newTab().setText(R.string.map));
            }
            tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
            viewPager = findViewById(R.id.pager);

            //Creating our pager adapter
            DamMoreInfoPager adapter = new DamMoreInfoPager(getSupportFragmentManager(), tabLayout.getTabCount());

            //Adding adapter to pager
            viewPager.setAdapter(adapter);
            viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

            //Adding onTabSelectedListener to swipe views
            tabLayout.addOnTabSelectedListener(this);

            mDrawerList = findViewById(R.id.left_drawer);
            if (!hasVideo) {
                if (as.getLanguage().equals("Spanish")) {
                    DrawerArrayList = getResources().getStringArray(R.array.drawerDamListNoVideo);

                } else {
                    DrawerArrayList = getResources().getStringArray(R.array.drawerDamListNoVideoEnglish);
                }
            } else {
                if (as.getLanguage().equals("Spanish")) {
                    DrawerArrayList = getResources().getStringArray(R.array.drawerDamListWithVideo);
                } else {
                    DrawerArrayList = getResources().getStringArray(R.array.drawerDamListWithVideoEnglish);
                }
            }

            mDrawerLayout = findViewById(R.id.drawer_layout);
            mDrawerList = findViewById(R.id.left_drawer);
            mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
            // set up the drawer's list view with items and click listener
            mDrawerList.setAdapter(new ArrayAdapter<>(this, R.layout.drawerlistbox, DrawerArrayList));
            mDrawerList.setOnItemClickListener(new DrawerItemClickListener());
            mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, 0, 0) {
                public void onDrawerClosed(View view) {
                    if (getSupportActionBar() != null)
                        getSupportActionBar().setTitle(R.string.app_name);
                    getWindow().getDecorView().findViewById(android.R.id.content).invalidate();
                    //mAdView.bringToFront();
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
                }
            };
            mDrawerLayout.addDrawerListener(mDrawerToggle);
            if (getSupportActionBar() != null) {
                getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_drawer);
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                getSupportActionBar().setHomeButtonEnabled(true);
            }
            mDrawerToggle.setDrawerIndicatorEnabled(true);
            //mDrawerList.setItemChecked(2, true);
            mDrawerToggle.syncState();
            new LoadData(this).execute();
        }
    }

    private class LoadData extends AsyncTask<Void, String, Void> {
        ProgressDialog dialog;

        LoadData(DamMoreInfoTab context) {
            dialog = new ProgressDialog(DamMoreInfoTab.this);
        }

        @Override
        protected void onPreExecute() {
            //set message of the dialog
            if (as.getLanguage().equals("Spanish")) {
                dialog.setMessage("Cargando datos... Paso 1 de 3");
            } else {
                dialog.setMessage("Loading... Step 1 of 3");
            }

            //show dialog
            dialog.show();
            dialog.setCancelable(false);
            dialog.setCanceledOnTouchOutside(false);
            super.onPreExecute();
        }

        protected void onProgressUpdate(String... params) {
            dialog.setMessage(params[0]);
        }

        protected Void doInBackground(Void... params) {

            URL url;
            InputStream is = null;
            BufferedReader br;
            String line;

            String getDate, getDate2;
            datahistory.clear();
            datahistoryEnglish.clear();
            datahistorymeters.clear();
            time.clear();
            meters.clear();
            try {
                url = new URL("https://nwis.waterdata.usgs.gov/pr/nwis/uv/?cb_62616=on&format=rdb&site_no=" + damvalue + "&period=90");
                is = url.openStream();  //must use strict policy to avoid exception
                br = new BufferedReader(new InputStreamReader(is));
                String[] currentline;
                if (as.getLanguage().equals("Spanish")) {
                    publishProgress("Cargando datos... Paso 2 de 3");
                } else {
                    publishProgress("Loading... Step 2 of 3");
                }
                while ((line = br.readLine()) != null) {
                    if (!line.contains("#")) {
                        currentline = line.split("\t");
                        if (currentline.length >= 5) {
                            time.add(currentline[2]);
                            meters.add(currentline[4]);
                        }
                    }
                }
                if (as.getLanguage().equals("Spanish")) {
                    publishProgress("Cargando datos... Paso 3 de 3");
                } else {
                    publishProgress("Loading... Step 3 of 3");
                }
                if (meters.size() > 2) {
                    damlevel = meters.get(meters.size() - 1);
                    OutputStream outputStream1 = new FileOutputStream(MetersFileToUse, false);
                    Writer out1 = new OutputStreamWriter(outputStream1);
                    out1.write(damlevel);
                    out1.close();
                    getDate = time.get(time.size() - 1);
                    DateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.US);
                    Date d = f.parse(getDate);
                    Date oneHourBack = new Date(d.getTime() - (long) 60 * 60 * 1000);
                    Date sixHourBack = new Date(d.getTime() - (long) 60 * 60 * 1000 * 6);
                    Date twelveHourBack = new Date(d.getTime() - (long) 60 * 60 * 1000 * 12);
                    Date twentyfourHourBack = new Date(d.getTime() - (long) 60 * 60 * 1000 * 24);
                    Date sevenDaysBack = new Date(d.getTime() - (long) 60 * 60 * 1000 * 24 * 7);
                    Date Days14Back = new Date(d.getTime() - (long) 60 * 60 * 1000 * 24 * 14);
                    Date Days30Back = new Date(d.getTime() - (long) 60 * 60 * 1000 * 24 * 30);
                    Date Days60Back = new Date(d.getTime() - (long) 60 * 60 * 1000 * 24 * 60);
                    Date Days90Back = new Date(d.getTime() - (long) 60 * 60 * 1000 * 24 * 90);
                    SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
                    SimpleDateFormat dateFormatterEnglish = new SimpleDateFormat("MM/dd/yyyy", Locale.US);
                    SimpleDateFormat timeFormatter = new SimpleDateFormat("hh:mm aa", Locale.US);
                    SimpleDateFormat timeBack = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.US);
                    String dateAsString = dateFormatter.format(d);
                    String dateAsStringEnglish = dateFormatterEnglish.format(d);
                    String timeAsString = timeFormatter.format(d);
                    String oneHourBackAsString = timeBack.format(oneHourBack);
                    String sixHoursBackAsString = timeBack.format(sixHourBack);
                    String twelveHoursBackAsString = timeBack.format(twelveHourBack);
                    String twentyfourHoursBackAsString = timeBack.format(twentyfourHourBack);
                    String sevenDaysBackAsString = timeBack.format(sevenDaysBack);
                    String DaysBack14AsString = timeBack.format(Days14Back);
                    String DaysBack30AsString = timeBack.format(Days30Back);
                    String DaysBack60AsString = timeBack.format(Days60Back);
                    String DaysBack90AsString = timeBack.format(Days90Back);
                    damdate = "Lectura realizada el " + dateAsString + " a las " + timeAsString;
                    damdateEnglish = "Reservoir statistics made at " + timeAsString + " on " + dateAsStringEnglish;
                    OutputStream outputStream2 = new FileOutputStream(DateFileToUse, false);
                    OutputStream outputStream2English = new FileOutputStream(DateFileToUseEnglish, false);
                    Writer out2 = new OutputStreamWriter(outputStream2);
                    Writer out2English = new OutputStreamWriter(outputStream2English);
                    out2.write(damdate);
                    out2English.write(damdateEnglish);
                    out2.close();
                    out2English.close();
                    noDataAvailable = false;
                    for (int i = 0; i < time.size() - 1; i++) {
                        String FileToUse = null;
                        String dataToWrite = null;
                        if (time.get(i).equals(oneHourBackAsString)) {
                            damlasthour = meters.get(i);
                            FileToUse = LastHourFileToUse;
                            dataToWrite = damlasthour;
                            lastHourAvailable = true;
                        } else if (time.get(i).equals(sixHoursBackAsString)) {
                            dam6hours = meters.get(i);
                            FileToUse = SixHoursFileToUse;
                            dataToWrite = dam6hours;
                            sixHoursAvailable = true;
                        } else if (time.get(i).equals(twelveHoursBackAsString)) {
                            dam12hours = meters.get(i);
                            FileToUse = TwelveHoursFileToUse;
                            dataToWrite = dam12hours;
                            twelveHoursAvailable = true;
                        } else if (time.get(i).equals(twentyfourHoursBackAsString)) {
                            dam24hours = meters.get(i);
                            FileToUse = TwentyFourHoursFileToUse;
                            dataToWrite = dam24hours;
                            twentyFourHoursAvailable = true;
                        } else if (time.get(i).equals(sevenDaysBackAsString)) {
                            dam7days = meters.get(i);
                            FileToUse = SevenDaysFileToUse;
                            dataToWrite = dam7days;
                            sevenDaysAvailable = true;
                        } else if (time.get(i).equals(DaysBack14AsString)) {
                            dam14days = meters.get(i);
                            FileToUse = days14filetouse;
                            dataToWrite = dam14days;
                            days14available = true;
                        } else if (time.get(i).equals(DaysBack30AsString)) {
                            dam30days = meters.get(i);
                            FileToUse = days30filetouse;
                            dataToWrite = dam30days;
                            days30available = true;
                        } else if (time.get(i).equals(DaysBack60AsString)) {
                            dam60days = meters.get(i);
                            FileToUse = days60filetouse;
                            dataToWrite = dam60days;
                            days60available = true;
                        } else if (time.get(i).equals(DaysBack90AsString)) {
                            dam90days = meters.get(i);
                            FileToUse = days90filetouse;
                            dataToWrite = dam90days;
                            days90available = true;
                        }
                        if (FileToUse != null && dataToWrite != null) {
                            OutputStream outputStream3 = new FileOutputStream(FileToUse, false);
                            Writer out3 = new OutputStreamWriter(outputStream3);
                            out3.write(dataToWrite);
                            out3.close();
                        }
                    }
                    FileWriter writer = new FileWriter(HistoryFileToUse, false);
                    FileWriter writerEnglish = new FileWriter(HistoryFileToUseEnglish, false);
                    String dataToWrite, dataToWriteEnglish;
                    FileWriter writer2 = new FileWriter(HistoryMeterFileToUse, false);
                    DateFormat f2 = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.US);
                    SimpleDateFormat timeFormatted = new SimpleDateFormat("dd/MM/yyyy hh:mm aa", Locale.US);
                    SimpleDateFormat timeFormattedEnglish = new SimpleDateFormat("MM/dd/yyyy hh:mm aa", Locale.US);
                    String timeFormattedAsString;
                    String timeFormattedAsStringEnglish;
                    for (int i = time.size() - 1; i >= 2; i--) {
                        getDate2 = time.get(i);
                        Date d2 = f2.parse(getDate2);
                        timeFormattedAsString = timeFormatted.format(d2);
                        timeFormattedAsStringEnglish = timeFormattedEnglish.format(d2);
                        dataToWrite = timeFormattedAsString + " - " + meters.get(i) + " metros";
                        dataToWriteEnglish = timeFormattedAsStringEnglish + " - " + meters.get(i) + " meters";
                        datahistory.add(dataToWrite);
                        datahistoryEnglish.add(dataToWriteEnglish);
                        datahistorymeters.add(meters.get(i));
                        historyAvailable = true;
                        writer.write(dataToWrite + System.getProperty("line.separator"));
                        writerEnglish.write(dataToWriteEnglish + System.getProperty("line.separator"));
                        writer2.write(meters.get(i) + System.getProperty("line.separator"));
                    }
                    writer.close();
                    writerEnglish.close();
                    writer2.close();
                } else {
                    noDataAvailable = true;
                }
            } catch (Exception e) {
                Log.e("Error", e.toString());
                File MetersFileLocation = new File(MetersFileToUse);
                File DateFileLocation = new File(DateFileToUse);
                File DateFileLocationEnglish = new File(DateFileToUseEnglish);
                File LastHourFileLocation = new File(LastHourFileToUse);
                File SixHoursFileLocation = new File(SixHoursFileToUse);
                File TwelveHoursFileLocation = new File(TwelveHoursFileToUse);
                File TwentyFourHoursFileLocation = new File(TwentyFourHoursFileToUse);
                File SevenDaysFileLocation = new File(SevenDaysFileToUse);
                File Days14FileLocation = new File(days14filetouse);
                File Days30FileLocation = new File(days30filetouse);
                File Days60FileLocation = new File(days60filetouse);
                File Days90FileLocation = new File(days90filetouse);
                File HistoryFileLocation = new File(HistoryFileToUse);
                File HistoryMetersFileLocation = new File(HistoryMeterFileToUse);
                try {
                    if (MetersFileLocation.exists() && DateFileLocation.exists() && DateFileLocationEnglish.exists()) {
                        noDataAvailable = false;
                        BufferedReader in = new BufferedReader(new FileReader(MetersFileToUse));
                        String str;
                        while ((str = in.readLine()) != null) {
                            damlevel = str;
                        }
                        in.close();
                        BufferedReader in2 = new BufferedReader(new FileReader(DateFileToUse));
                        String str2;
                        while ((str2 = in2.readLine()) != null) {
                            damdate = str2;
                        }
                        in2.close();
                        BufferedReader in2English = new BufferedReader(new FileReader(DateFileToUseEnglish));
                        String str2English;
                        while ((str2English = in2English.readLine()) != null) {
                            damdateEnglish = str2English;
                        }
                        in2English.close();
                        if (LastHourFileLocation.exists()) {
                            BufferedReader in3 = new BufferedReader(new FileReader(LastHourFileToUse));
                            String str3;
                            while ((str3 = in3.readLine()) != null) {
                                damlasthour = str3;
                                lastHourAvailable = true;
                            }
                            if (damlasthour.equals("")) {
                                lastHourAvailable = false;
                            }
                            in3.close();
                        } else {
                            lastHourAvailable = false;
                        }
                        if (SixHoursFileLocation.exists()) {
                            BufferedReader in4 = new BufferedReader(new FileReader(SixHoursFileToUse));
                            String str4;
                            while ((str4 = in4.readLine()) != null) {
                                dam6hours = str4;
                                sixHoursAvailable = true;
                            }
                            if (dam6hours.equals("")) {
                                sixHoursAvailable = false;
                            }
                            in4.close();
                        } else {
                            sixHoursAvailable = false;
                        }
                        if (TwelveHoursFileLocation.exists()) {
                            BufferedReader in5 = new BufferedReader(new FileReader(TwelveHoursFileToUse));
                            String str5;
                            while ((str5 = in5.readLine()) != null) {
                                dam12hours = str5;
                                twelveHoursAvailable = true;
                            }
                            if (dam12hours.equals("")) {
                                twelveHoursAvailable = false;
                            }
                            in5.close();
                        } else {
                            twelveHoursAvailable = false;
                        }
                        if (TwentyFourHoursFileLocation.exists()) {
                            BufferedReader in6 = new BufferedReader(new FileReader(TwentyFourHoursFileToUse));
                            String str6;
                            while ((str6 = in6.readLine()) != null) {
                                dam24hours = str6;
                                twentyFourHoursAvailable = true;
                            }
                            if (dam24hours.equals("")) {
                                twentyFourHoursAvailable = false;
                            }
                            in6.close();
                        } else {
                            twentyFourHoursAvailable = false;
                        }
                        if (SevenDaysFileLocation.exists()) {
                            BufferedReader in7 = new BufferedReader(new FileReader(SevenDaysFileToUse));
                            String str7;
                            while ((str7 = in7.readLine()) != null) {
                                dam7days = str7;
                                sevenDaysAvailable = true;
                            }
                            if (dam7days.equals("")) {
                                sevenDaysAvailable = false;
                            }
                            in7.close();
                        } else
                            sevenDaysAvailable = false;
                        if (Days14FileLocation.exists()) {
                            BufferedReader in7 = new BufferedReader(new FileReader(days14filetouse));
                            String str7;
                            while ((str7 = in7.readLine()) != null) {
                                dam14days = str7;
                                days14available = true;
                            }
                            if (dam14days.equals("")) {
                                days14available = false;
                            }
                            in7.close();
                        } else
                            days14available = false;
                        if (Days30FileLocation.exists()) {
                            BufferedReader in7 = new BufferedReader(new FileReader(days30filetouse));
                            String str7;
                            while ((str7 = in7.readLine()) != null) {
                                dam30days = str7;
                                days30available = true;
                            }
                            if (dam30days.equals("")) {
                                days30available = false;
                            }
                            in7.close();
                        } else
                            days30available = false;
                        if (Days60FileLocation.exists()) {
                            BufferedReader in7 = new BufferedReader(new FileReader(days60filetouse));
                            String str7;
                            while ((str7 = in7.readLine()) != null) {
                                dam60days = str7;
                                days60available = true;
                            }
                            if (dam60days.equals("")) {
                                days60available = false;
                            }
                            in7.close();
                        } else
                            days60available = false;
                        if (Days90FileLocation.exists()) {
                            BufferedReader in7 = new BufferedReader(new FileReader(days90filetouse));
                            String str7;
                            while ((str7 = in7.readLine()) != null) {
                                dam90days = str7;
                                days90available = true;
                            }
                            if (dam90days.equals("")) {
                                days90available = false;
                            }
                            in7.close();
                        } else
                            days90available = false;
                        if (HistoryFileLocation.exists() && HistoryMetersFileLocation.exists()) {
                            BufferedReader in8 = new BufferedReader(new FileReader(HistoryFileToUse));
                            BufferedReader in8English = new BufferedReader(new FileReader(HistoryFileToUseEnglish));
                            BufferedReader in9 = new BufferedReader(new FileReader(HistoryMeterFileToUse));
                            String str8;
                            String str8English;
                            String str9;
                            while ((str8 = in8.readLine()) != null) {
                                datahistory.add(str8);
                                historyAvailable = true;
                            }
                            while ((str8English = in8English.readLine()) != null) {
                                datahistoryEnglish.add(str8English);
                                historyAvailableEnglish = true;
                            }
                            while ((str9 = in9.readLine()) != null) {
                                datahistorymeters.add(str9);
                            }
                            if (datahistory.size() == 0 || datahistorymeters.size() == 0) {
                                historyAvailable = false;
                            }
                            if (datahistoryEnglish.size() == 0 || datahistorymeters.size() == 0) {
                                historyAvailableEnglish = false;
                            }
                            in8.close();
                            in8English.close();
                            in9.close();
                        } else {
                            historyAvailable = false;
                            historyAvailableEnglish = false;
                        }
                    } else {
                        noDataAvailable = true;
                        noDataAvailableEnglish = true;
                        lastHourAvailable = false;
                        sixHoursAvailable = false;
                        twelveHoursAvailable = false;
                        twentyFourHoursAvailable = false;
                        sevenDaysAvailable = false;
                        historyAvailable = false;
                        historyAvailableEnglish = false;
                    }
                } catch (Exception e2) {
                    //nothing to show
                }
            } finally {
                try {
                    if (is != null) is.close();
                } catch (IOException ioe) {
                    // nothing to see here
                }
            }

            return null;
        }

        protected void onPostExecute(Void result) {

            if (dialog != null && dialog.isShowing())
                dialog.dismiss();
            if (viewPager.getAdapter() != null)
                viewPager.getAdapter().notifyDataSetChanged();
            if (mInterstitialAd.isLoaded()) {
                mInterstitialAd.show();
            } else {
                Log.d("TAG", "The interstitial wasn't loaded yet.");
            }
        }
    }

    @Override
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
            di.mainDrawerItems(DamMoreInfoTab.this, DamMoreInfoTab.class, position, 999, hasVideo, VideoID);
            selectItem(position);
        }
    }

    private void selectItem(int position) {
        // update selected item and title, then close the drawer
        mDrawerList.setItemChecked(position, true);
        mDrawerLayout.closeDrawer(mDrawerList);
    }
}
