package msc.app.embalsespuertorico;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class DamMaps extends Fragment implements OnMapReadyCallback {
    private String currentDam;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.dammaplayout, container, false);
        final AdView mAdView = v.findViewById(R.id.admap2);
        AdRequest adRequest = new AdRequest.Builder().addTestDevice("03929FBEA0721F82E4AEE15546DBB5DC").build();
        mAdView.loadAd(adRequest);
        currentDam = DamMoreInfoTab.damNameToDisplay;
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        final SupportMapFragment mapFragment = (SupportMapFragment) this.getChildFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null)
            mapFragment.getMapAsync(this);
        return v;
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        GoogleMap mMap;
        mMap = googleMap;
        mMap.clear();
        if (currentDam.equals("Carraízo")) {
            LatLng dam = new LatLng(18.32791, -66.01628);
            mMap.addMarker(new MarkerOptions().position(dam).title("Carraizo"));
            mMap.moveCamera(CameraUpdateFactory.newCameraPosition(new CameraPosition.Builder().target(new LatLng(18.32791, -66.01628)).zoom(16f).bearing(0).build()));
        }
        if (currentDam.equals("La Plata")) {
            LatLng dam = new LatLng(18.343, -66.23607);
            mMap.addMarker(new MarkerOptions().position(dam).title("La Plata"));
            mMap.moveCamera(CameraUpdateFactory.newCameraPosition(new CameraPosition.Builder().target(new LatLng(18.343, -66.23607)).zoom(16f).bearing(0).build()));
        }
        if (currentDam.equals("Cidra")) {
            LatLng dam = new LatLng(18.1969, -66.14072);
            mMap.addMarker(new MarkerOptions().position(dam).title("Cidra"));
            mMap.moveCamera(CameraUpdateFactory.newCameraPosition(new CameraPosition.Builder().target(new LatLng(18.1969, -66.14072)).zoom(16f).bearing(0).build()));
        }
        if (currentDam.equals("Patillas")) {
            LatLng dam = new LatLng(18.01774, -66.0185);
            mMap.addMarker(new MarkerOptions().position(dam).title("Patillas"));
            mMap.moveCamera(CameraUpdateFactory.newCameraPosition(new CameraPosition.Builder().target(new LatLng(18.01774, -66.0185)).zoom(16f).bearing(0).build()));
        }
        if (currentDam.equals("Toa Vaca")) {
            LatLng dam = new LatLng(18.10166, -66.48902);
            mMap.addMarker(new MarkerOptions().position(dam).title("Toa Vaca"));
            mMap.moveCamera(CameraUpdateFactory.newCameraPosition(new CameraPosition.Builder().target(new LatLng(18.10166, -66.48902)).zoom(16f).bearing(0).build()));
        }
        if (currentDam.equals("Carite")) {
            LatLng dam = new LatLng(18.07524, -66.10683);
            mMap.addMarker(new MarkerOptions().position(dam).title("Carite"));
            mMap.moveCamera(CameraUpdateFactory.newCameraPosition(new CameraPosition.Builder().target(new LatLng(18.07524, -66.10683)).zoom(16f).bearing(0).build()));
        }
        if (currentDam.equals("Rio Blanco")) {
            LatLng dam = new LatLng(18.22389, -65.78142);
            mMap.addMarker(new MarkerOptions().position(dam).title("Rio Blanco"));
            mMap.moveCamera(CameraUpdateFactory.newCameraPosition(new CameraPosition.Builder().target(new LatLng(18.22389, -65.78142)).zoom(16f).bearing(0).build()));
        }
        if (currentDam.equals("Caonillas")) {
            LatLng dam = new LatLng(18.27654, -66.65642);
            mMap.addMarker(new MarkerOptions().position(dam).title("Caonillas"));
            mMap.moveCamera(CameraUpdateFactory.newCameraPosition(new CameraPosition.Builder().target(new LatLng(18.27654, -66.65642)).zoom(16f).bearing(0).build()));
        }
        if (currentDam.equals("Fajardo")) {
            LatLng dam = new LatLng(18.2969, -65.65858);
            mMap.addMarker(new MarkerOptions().position(dam).title("Fajardo"));
            mMap.moveCamera(CameraUpdateFactory.newCameraPosition(new CameraPosition.Builder().target(new LatLng(18.2969, -65.65858)).zoom(16f).bearing(0).build()));
        }
        if (currentDam.equals("Guajataca")) {
            LatLng dam = new LatLng(18.39836, -66.9227);
            mMap.addMarker(new MarkerOptions().position(dam).title("Guajataca"));
            mMap.moveCamera(CameraUpdateFactory.newCameraPosition(new CameraPosition.Builder().target(new LatLng(18.39836, -66.9227)).zoom(16f).bearing(0).build()));
        }
        if (currentDam.equals("Cerrillos")) {
            LatLng dam = new LatLng(18.07703, -66.57547);
            mMap.addMarker(new MarkerOptions().position(dam).title("Cerrillos"));
            mMap.moveCamera(CameraUpdateFactory.newCameraPosition(new CameraPosition.Builder().target(new LatLng(18.07703, -66.57547)).zoom(16f).bearing(0).build()));
        }
    }
}
