package msc.app.embalsespuertorico


import androidx.fragment.app.Fragment
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions

class LookInMap : Fragment(), OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.activity_look_in_map, container, false)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = this.childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        val selectDam = v.findViewById<TextView>(R.id.textView19)
        val adFrame = v.findViewById<FrameLayout>(R.id.mainactivityad)
        val mAdFunctions = AdFunctions()
        mAdFunctions.loadBanner(adFrame, R.string.map2, activity as AppCompatActivity)
        val `as` = app_settings(activity!!)
        if (`as`.language == "Spanish")
            selectDam.setText(R.string.select_in_map_spanish)
        else
            selectDam.setText(R.string.select_in_map_english)
        mapFragment?.getMapAsync(this)
        return v
    }

    override fun onMapReady(googleMap: GoogleMap) {
        val mMap: GoogleMap = googleMap
        mMap.addMarker(MarkerOptions().position(LatLng(18.32791, -66.01628)).title("Carraízo"))
        mMap.addMarker(MarkerOptions().position(LatLng(18.343, -66.23607)).title("La Plata"))
        mMap.addMarker(MarkerOptions().position(LatLng(18.1969, -66.14072)).title("Cidra"))
        mMap.addMarker(MarkerOptions().position(LatLng(18.01774, -66.0185)).title("Patillas"))
        mMap.addMarker(MarkerOptions().position(LatLng(18.10166, -66.48902)).title("Toa Vaca"))
        mMap.addMarker(MarkerOptions().position(LatLng(18.07524, -66.10683)).title("Carite"))
        mMap.addMarker(MarkerOptions().position(LatLng(18.22389, -65.78142)).title("Rio Blanco"))
        mMap.addMarker(MarkerOptions().position(LatLng(18.27654, -66.65642)).title("Caonillas"))
        mMap.addMarker(MarkerOptions().position(LatLng(18.2969, -65.65858)).title("Fajardo"))
        mMap.addMarker(MarkerOptions().position(LatLng(18.39836, -66.9227)).title("Guajataca"))
        mMap.addMarker(MarkerOptions().position(LatLng(18.07703, -66.57547)).title("Cerrillos"))
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