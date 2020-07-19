package msc.app.embalsespuertorico

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class DamMaps : Fragment(), OnMapReadyCallback {
    private var currentDam: String? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.dammaplayout, container, false)
        val adFrame = v.findViewById<FrameLayout>(R.id.admap2)
        val mAdFunctions = AdFunctions()
        mAdFunctions.loadBanner(adFrame, R.string.map1, activity as AppCompatActivity)
        currentDam = DamMoreInfoTab.damNameToDisplay
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = this.childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(this)
        return v
    }


    override fun onMapReady(googleMap: GoogleMap) {
        val mMap: GoogleMap = googleMap
        mMap.clear()
        if (currentDam == "Carraízo") {
            val dam = LatLng(18.32791, -66.01628)
            mMap.addMarker(MarkerOptions().position(dam).title("Carraizo"))
            mMap.moveCamera(CameraUpdateFactory.newCameraPosition(CameraPosition.Builder().target(LatLng(18.32791, -66.01628)).zoom(16f).bearing(0f).build()))
        }
        if (currentDam == "La Plata") {
            val dam = LatLng(18.343, -66.23607)
            mMap.addMarker(MarkerOptions().position(dam).title("La Plata"))
            mMap.moveCamera(CameraUpdateFactory.newCameraPosition(CameraPosition.Builder().target(LatLng(18.343, -66.23607)).zoom(16f).bearing(0f).build()))
        }
        if (currentDam == "Cidra") {
            val dam = LatLng(18.1969, -66.14072)
            mMap.addMarker(MarkerOptions().position(dam).title("Cidra"))
            mMap.moveCamera(CameraUpdateFactory.newCameraPosition(CameraPosition.Builder().target(LatLng(18.1969, -66.14072)).zoom(16f).bearing(0f).build()))
        }
        if (currentDam == "Patillas") {
            val dam = LatLng(18.01774, -66.0185)
            mMap.addMarker(MarkerOptions().position(dam).title("Patillas"))
            mMap.moveCamera(CameraUpdateFactory.newCameraPosition(CameraPosition.Builder().target(LatLng(18.01774, -66.0185)).zoom(16f).bearing(0f).build()))
        }
        if (currentDam == "Toa Vaca") {
            val dam = LatLng(18.10166, -66.48902)
            mMap.addMarker(MarkerOptions().position(dam).title("Toa Vaca"))
            mMap.moveCamera(CameraUpdateFactory.newCameraPosition(CameraPosition.Builder().target(LatLng(18.10166, -66.48902)).zoom(16f).bearing(0f).build()))
        }
        if (currentDam == "Carite") {
            val dam = LatLng(18.07524, -66.10683)
            mMap.addMarker(MarkerOptions().position(dam).title("Carite"))
            mMap.moveCamera(CameraUpdateFactory.newCameraPosition(CameraPosition.Builder().target(LatLng(18.07524, -66.10683)).zoom(16f).bearing(0f).build()))
        }
        if (currentDam == "Rio Blanco") {
            val dam = LatLng(18.22389, -65.78142)
            mMap.addMarker(MarkerOptions().position(dam).title("Rio Blanco"))
            mMap.moveCamera(CameraUpdateFactory.newCameraPosition(CameraPosition.Builder().target(LatLng(18.22389, -65.78142)).zoom(16f).bearing(0f).build()))
        }
        if (currentDam == "Caonillas") {
            val dam = LatLng(18.27654, -66.65642)
            mMap.addMarker(MarkerOptions().position(dam).title("Caonillas"))
            mMap.moveCamera(CameraUpdateFactory.newCameraPosition(CameraPosition.Builder().target(LatLng(18.27654, -66.65642)).zoom(16f).bearing(0f).build()))
        }
        if (currentDam == "Fajardo") {
            val dam = LatLng(18.2969, -65.65858)
            mMap.addMarker(MarkerOptions().position(dam).title("Fajardo"))
            mMap.moveCamera(CameraUpdateFactory.newCameraPosition(CameraPosition.Builder().target(LatLng(18.2969, -65.65858)).zoom(16f).bearing(0f).build()))
        }
        if (currentDam == "Guajataca") {
            val dam = LatLng(18.39836, -66.9227)
            mMap.addMarker(MarkerOptions().position(dam).title("Guajataca"))
            mMap.moveCamera(CameraUpdateFactory.newCameraPosition(CameraPosition.Builder().target(LatLng(18.39836, -66.9227)).zoom(16f).bearing(0f).build()))
        }
        if (currentDam == "Cerrillos") {
            val dam = LatLng(18.07703, -66.57547)
            mMap.addMarker(MarkerOptions().position(dam).title("Cerrillos"))
            mMap.moveCamera(CameraUpdateFactory.newCameraPosition(CameraPosition.Builder().target(LatLng(18.07703, -66.57547)).zoom(16f).bearing(0f).build()))
        }
    }
}
