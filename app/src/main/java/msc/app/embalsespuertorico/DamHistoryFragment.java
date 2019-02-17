package msc.app.embalsespuertorico;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.ArrayList;

/**
 * Created by Moises Cardona on 2/7/2017.
 */
public class DamHistoryFragment extends Fragment {
    public double compare1 = 0.0, compare2 = 0.0;
    //Overriden method onCreateView
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.damhistory, container, false);
        final TextView TextViewHistory = v.findViewById(R.id.textViewHistory);
        ListView lv = v.findViewById(R.id.listView2);
        final AdView mAdView = v.findViewById(R.id.damhistoryad);
        AdRequest adRequest = new AdRequest.Builder().addTestDevice("03929FBEA0721F82E4AEE15546DBB5DC").build();
        mAdView.loadAd(adRequest);
        if(!DamMoreInfoTab.historyAvailable) {
            DamMoreInfoTab.datahistory.add("No hay historial disponible en estos momentos.");
            DamMoreInfoTab.datahistoryEnglish.add("There's no reservoir statistics available at this moment.");
        }
        app_settings as = new app_settings(getActivity());
        ArrayList<String> HistoryArrayList;
        if (as.getLanguage().equals("Spanish"))
            HistoryArrayList = DamMoreInfoTab.datahistory;
        else
            HistoryArrayList = DamMoreInfoTab.datahistoryEnglish;
        TextViewHistory.setText(DamMoreInfoTab.damNameToDisplay);
        if (getActivity() != null) {
            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getActivity(), R.layout.listviewcustomsize, HistoryArrayList) {
                @Override
                public @NonNull View getView(int position, View convertView, @NonNull ViewGroup parent) {
                    TextView textView = (TextView) super.getView(position, convertView, parent);
                    if (position < DamMoreInfoTab.datahistorymeters.size() - 1) {
                        compare1 = Double.parseDouble(DamMoreInfoTab.datahistorymeters.get(position));
                        compare2 = Double.parseDouble(DamMoreInfoTab.datahistorymeters.get(position + 1));
                        if (compare1 > compare2) {
                            textView.setTextColor(Color.parseColor("#13cc00")); //verde
                        }
                        if (compare1 < compare2) {
                            textView.setTextColor(Color.parseColor("#f6000e")); //rojo
                        }
                        if (compare1 == compare2) {
                            textView.setTextColor(Color.parseColor("#d9c900")); //amarillo
                        }
                    }
                    return textView;
                }
            };
            lv.setAdapter(arrayAdapter);
        }
        return v;
    }
}
