package msc.app.embalsespuertorico


import androidx.fragment.app.Fragment
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.RequestConfiguration
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import java.util.*

class LookInMap : Fragment(), OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.activity_look_in_map, container, false)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = this.childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        val SelectDam = v.findViewById<TextView>(R.id.textView19)
        val mAdView = v.findViewById<AdView>(R.id.mainactivityad)
        val configuration = RequestConfiguration.Builder().setTestDeviceIds(Arrays.asList(getString(R.string.deviceTestID))).build()
        MobileAds.setRequestConfiguration(configuration)
        val adRequest = AdRequest.Builder().build()
        mAdView.loadAd(adRequest)
        val `as` = app_settings(activity!!)
        if (`as`.language == "Spanish")
            SelectDam.setText(R.string.select_in_map_spanish)
        else
            SelectDam.setText(R.string.select_in_map_english)
        mapFragment?.getMapAsync(this)
        return v
    }

    override fun onMapReady(googleMap: GoogleMap) {
        val mMap: GoogleMap
        mMap = googleMap
        val Carraizo = LatLng(18.32791, -66.01628)
        mMap.addMarker(MarkerOptions().position(Carraizo).title("Carraízo"))
        val LaPlata = LatLng(18.343, -66.23607)
        mMap.addMarker(MarkerOptions().position(LaPlata).title("La Plata"))
        val Cidra = LatLng(18.1969, -66.14072)
        mMap.addMarker(MarkerOptions().position(Cidra).title("Cidra"))
        val Patillas = LatLng(18.01774, -66.0185)
        mMap.addMarker(MarkerOptions().position(Patillas).title("Patillas"))
        val ToaVaca = LatLng(18.10166, -66.48902)
        mMap.addMarker(MarkerOptions().position(ToaVaca).title("Toa Vaca"))
        val Carite = LatLng(18.07524, -66.10683)
        mMap.addMarker(MarkerOptions().position(Carite).title("Carite"))
        val RioBlanco = LatLng(18.22389, -65.78142)
        mMap.addMarker(MarkerOptions().position(RioBlanco).title("Rio Blanco"))
        val Caonillas = LatLng(18.27654, -66.65642)
        mMap.addMarker(MarkerOptions().position(Caonillas).title("Caonillas"))
        val Fajardo = LatLng(18.2969, -65.65858)
        mMap.addMarker(MarkerOptions().position(Fajardo).title("Fajardo"))
        val Guajataca = LatLng(18.39836, -66.9227)
        mMap.addMarker(MarkerOptions().position(Guajataca).title("Guajataca"))
        val Cerrillos = LatLng(18.07703, -66.57547)
        mMap.addMarker(MarkerOptions().position(Cerrillos).title("Cerrillos"))
        mMap.moveCamera(CameraUpdateFactory.newCameraPosition(PuertoRico))
        mMap.setOnMarkerClickListener(this)
    }

    override fun onMarkerClick(marker: Marker): Boolean {
        val selectedMarker = marker.title
        val damdata = Intent(activity, DamMoreInfoTab::class.java)
        when (selectedMarker) {
            "Caonillas" //Caonillas
            -> damdata.putExtra("DamID", "50026140")
            "Carite"  //Carite
            -> damdata.putExtra("DamID", "50039995")
            "Carraízo"  //Carraizo
            -> damdata.putExtra("DamID", "50059000")
            "Cerrillos"  //Cerrillos
            -> damdata.putExtra("DamID", "50113950")
            "Cidra"  //Cidra
            -> damdata.putExtra("DamID", "50047550")
            "Fajardo"  //Fajardo
            -> damdata.putExtra("DamID", "50071225")
            "Guajataca"  //Guajataca
            -> damdata.putExtra("DamID", "50010800")
            "La Plata"  //La Plata
            -> damdata.putExtra("DamID", "50045000")
            "Patillas"  //Patillas
            -> damdata.putExtra("DamID", "50093045")
            "Rio Blanco"  //Rio Blanco
            -> damdata.putExtra("DamID", "50076800")
            "Toa Vaca"  //Toa Vaca
            -> damdata.putExtra("DamID", "50111210")
        }
        startActivity(damdata)
        return true
    }

    companion object {
        internal val PuertoRico = CameraPosition.Builder().target(LatLng(18.2218424, -66.4465398))
                .zoom(8f)
                .bearing(0f)
                .build()
    }
}