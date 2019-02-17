package msc.app.embalsespuertorico;


import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class LookInMap extends Fragment implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener {
    static final CameraPosition PuertoRico =
            new CameraPosition.Builder().target(new LatLng(18.2218424, -66.4465398))
                    .zoom(8f)
                    .bearing(0)
                    .build();

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_look_in_map, container, false);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        final SupportMapFragment mapFragment = (SupportMapFragment) this.getChildFragmentManager().findFragmentById(R.id.map);
        final TextView SelectDam = v.findViewById(R.id.textView19);
        final AdView mAdView = v.findViewById(R.id.mainactivityad);
        AdRequest adRequest = new AdRequest.Builder().addTestDevice("03929FBEA0721F82E4AEE15546DBB5DC").build();
        mAdView.loadAd(adRequest);
        app_settings as = new app_settings(getActivity());
        if (as.getLanguage().equals("Spanish"))
            SelectDam.setText(R.string.select_in_map_spanish);
        else
            SelectDam.setText(R.string.select_in_map_english);
        if (mapFragment != null)
            mapFragment.getMapAsync(this);
        return v;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        GoogleMap mMap;
        mMap = googleMap;
        LatLng Carraizo = new LatLng(18.32791, -66.01628);
        mMap.addMarker(new MarkerOptions().position(Carraizo).title("Carraízo"));
        LatLng LaPlata = new LatLng(18.343, -66.23607);
        mMap.addMarker(new MarkerOptions().position(LaPlata).title("La Plata"));
        LatLng Cidra = new LatLng(18.1969, -66.14072);
        mMap.addMarker(new MarkerOptions().position(Cidra).title("Cidra"));
        LatLng Patillas = new LatLng(18.01774, -66.0185);
        mMap.addMarker(new MarkerOptions().position(Patillas).title("Patillas"));
        LatLng ToaVaca = new LatLng(18.10166, -66.48902);
        mMap.addMarker(new MarkerOptions().position(ToaVaca).title("Toa Vaca"));
        LatLng Carite = new LatLng(18.07524, -66.10683);
        mMap.addMarker(new MarkerOptions().position(Carite).title("Carite"));
        LatLng RioBlanco = new LatLng(18.22389, -65.78142);
        mMap.addMarker(new MarkerOptions().position(RioBlanco).title("Rio Blanco"));
        LatLng Caonillas = new LatLng(18.27654, -66.65642);
        mMap.addMarker(new MarkerOptions().position(Caonillas).title("Caonillas"));
        LatLng Fajardo = new LatLng(18.2969, -65.65858);
        mMap.addMarker(new MarkerOptions().position(Fajardo).title("Fajardo"));
        LatLng Guajataca = new LatLng(18.39836, -66.9227);
        mMap.addMarker(new MarkerOptions().position(Guajataca).title("Guajataca"));
        LatLng Cerrillos = new LatLng(18.07703, -66.57547);
        mMap.addMarker(new MarkerOptions().position(Cerrillos).title("Cerrillos"));
        mMap.moveCamera(CameraUpdateFactory.newCameraPosition(PuertoRico));
        mMap.setOnMarkerClickListener(this);
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        String selectedMarker = marker.getTitle();
        Intent damdata = new Intent(getActivity(), DamMoreInfoTab.class);
        switch (selectedMarker) {
            case "Caonillas": //Caonillas
                damdata.putExtra("DamID", "50026140");
                break;
            case "Carite":  //Carite
                damdata.putExtra("DamID", "50039995");
                break;
            case "Carraízo":  //Carraizo
                damdata.putExtra("DamID", "50059000");
                break;
            case "Cerrillos":  //Cerrillos
                damdata.putExtra("DamID", "50113950");
                break;
            case "Cidra":  //Cidra
                damdata.putExtra("DamID", "50047550");
                break;
            case "Fajardo":  //Fajardo
                damdata.putExtra("DamID", "50071225");
                break;
            case "Guajataca":  //Guajataca
                damdata.putExtra("DamID", "50010800");
                break;
            case "La Plata":  //La Plata
                damdata.putExtra("DamID", "50045000");
                break;
            case "Patillas":  //Patillas
                damdata.putExtra("DamID", "50093045");
                break;
            case "Rio Blanco":  //Rio Blanco
                damdata.putExtra("DamID", "50076800");
                break;
            case "Toa Vaca":  //Toa Vaca
                damdata.putExtra("DamID", "50111210");
                break;
        }
        startActivity(damdata);
        return true;
        }
    }