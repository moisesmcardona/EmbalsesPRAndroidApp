package msc.app.embalsespuertorico;

import android.support.annotation.NonNull;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

public class MainActivity extends Fragment {
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_main, container, false);
        final ListView listview = v.findViewById(R.id.listView1);
        final TextView seleccionEmbalse = v.findViewById(R.id.embalse);
        final AdView mAdView = v.findViewById(R.id.mainactivityad);
        AdRequest adRequest = new AdRequest.Builder().addTestDevice("03929FBEA0721F82E4AEE15546DBB5DC").build();
        mAdView.loadAd(adRequest);
        app_settings as = new app_settings(getActivity());
        if (as.getLanguage().equals("Spanish"))
            seleccionEmbalse.setText(R.string.seleccione_embalse);
        else
            seleccionEmbalse.setText(R.string.select_reservoir);
        if (getActivity() != null) {
            ArrayAdapter<String> ad = new ArrayAdapter<>(getActivity(), R.layout.textcenterlistview, getResources().getStringArray(R.array.mainlist));
            listview.setAdapter(ad);
            listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view,
                                        int position, long id) {
                    Intent damdata = new Intent(getActivity(), DamMoreInfoTab.class);
                    switch (position) {
                        case 0: //Caonillas
                            damdata.putExtra("DamID", "50026140");
                            break;
                        case 1: //Carite
                            damdata.putExtra("DamID", "50039995");
                            break;
                        case 2: //Carraizo
                            damdata.putExtra("DamID", "50059000");
                            break;
                        case 3: //Cerrillos
                            damdata.putExtra("DamID", "50113950");
                            break;
                        case 4: //Cidra
                            damdata.putExtra("DamID", "50047550");
                            break;
                        case 5: //Fajardo
                            damdata.putExtra("DamID", "50071225");
                            break;
                        case 6: //Guajataca
                            damdata.putExtra("DamID", "50010800");
                            break;
                        case 7: //La Plata
                            damdata.putExtra("DamID", "50045000");
                            break;
                        case 8: //Patillas
                            damdata.putExtra("DamID", "50093045");
                            break;
                        case 9: //Rio Blanco
                            damdata.putExtra("DamID", "50076800");
                            break;
                        case 10: //Toa Vaca
                            damdata.putExtra("DamID", "50111210");
                            break;
                    }
                    startActivity(damdata);
                }
            });
        }
        return v;
    }


}
