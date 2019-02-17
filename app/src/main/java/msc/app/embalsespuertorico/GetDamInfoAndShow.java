package msc.app.embalsespuertorico;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.Locale;

/**
 * Created by Moisés Cardona on 9/25/2015.
 */
public class GetDamInfoAndShow extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.dampage, container, false);
        final ImageView DamPic = v.findViewById(R.id.imageView);
        final TextView DamName = v.findViewById(R.id.embalse);
        final TextView DamDesborde = v.findViewById(R.id.desborde);
        final TextView DamSeguridad = v.findViewById(R.id.seguridad);
        final TextView DamObservacion = v.findViewById(R.id.observacion);
        final TextView DamAjuste = v.findViewById(R.id.ajuste);
        final TextView DamControl = v.findViewById(R.id.control);
        final TextView LeyendaText = v.findViewById(R.id.nivelesdealerta);
        final TextView DamLevel = v.findViewById(R.id.level);
        final TextView DamDate = v.findViewById(R.id.fecha);
        final TextView DamLastHour = v.findViewById(R.id.lastHour);
        final TextView DamIncreaseOrDrop = v.findViewById(R.id.droporincrease);
        final AdView mAdView = v.findViewById(R.id.damad);
        AdRequest adRequest = new AdRequest.Builder().addTestDevice("03929FBEA0721F82E4AEE15546DBB5DC").build();
        mAdView.loadAd(adRequest);
        DamName.setText(DamMoreInfoTab.damNameToDisplay);
        DamPic.setImageResource(DamMoreInfoTab.imageToUse);
        app_settings as = new app_settings(getActivity());
        if (as.getLanguage().equals("Spanish")) {
            LeyendaText.setText(R.string.nivelesdealerta);
            DamDesborde.setText(String.format(Locale.US, "Desborde: %s", DamMoreInfoTab.desborde));
            DamSeguridad.setText(String.format(Locale.US, "Seguridad: %s", DamMoreInfoTab.seguridad));
            DamObservacion.setText(String.format(Locale.US, "Observación: %s", DamMoreInfoTab.observacion));
            DamAjuste.setText(String.format(Locale.US, "Ajuste: %s", DamMoreInfoTab.ajuste));
            DamControl.setText(String.format(Locale.US, "Control: %s", DamMoreInfoTab.control));
        } else {
            LeyendaText.setText(R.string.alert_levels);
            DamDesborde.setText(String.format(Locale.US, "Overflow: %s", DamMoreInfoTab.desborde));
            DamSeguridad.setText(String.format(Locale.US, "Security: %s", DamMoreInfoTab.seguridad));
            DamObservacion.setText(String.format(Locale.US, "Observation: %s" , DamMoreInfoTab.observacion));
            DamAjuste.setText(String.format(Locale.US, "Adjustment: %s", DamMoreInfoTab.ajuste));
            DamControl.setText(String.format(Locale.US, "Control: %s", DamMoreInfoTab.control));
        }
        if (!DamMoreInfoTab.noDataAvailable) {
            try {
                if (as.getLanguage().equals("Spanish")) {
                    DamLevel.setText(String.format(Locale.US, "%s metros",DamMoreInfoTab.damlevel));
                    DamDate.setText(DamMoreInfoTab.damdate);
                } else {
                    DamLevel.setText(String.format(Locale.US, "%s meters",DamMoreInfoTab.damlevel));
                    DamDate.setText(DamMoreInfoTab.damdateEnglish);
                }

                double DamLevelDouble = Double.parseDouble(DamMoreInfoTab.damlevel);
                double DamDesbordeDouble = Double.parseDouble(DamMoreInfoTab.desborde);
                double DamSeguridadDouble = Double.parseDouble(DamMoreInfoTab.seguridad);
                double DamObservacionDouble = Double.parseDouble(DamMoreInfoTab.observacion);
                double DamAjusteDouble = Double.parseDouble(DamMoreInfoTab.ajuste);
                double DamControlDouble = Double.parseDouble(DamMoreInfoTab.control);
                if (DamMoreInfoTab.lastHourAvailable) {
                    if (as.getLanguage().equals("Spanish")) {
                        DamLastHour.setText(String.format("Lectura en la pasada hora: %s metros", DamMoreInfoTab.damlasthour));
                    } else {
                        DamLastHour.setText(String.format("Data from last hour: %s meters", DamMoreInfoTab.damlasthour));
                    }
                    double DamLastHourDouble = Double.parseDouble(DamMoreInfoTab.damlasthour);
                    if (DamLastHourDouble < DamLevelDouble) {
                        double DamMathDouble = DamLevelDouble - DamLastHourDouble;
                        if (as.getLanguage().equals("Spanish")) {
                            DamIncreaseOrDrop.setText(String.format("El embalse aumentó %." + DamMoreInfoTab.decimalplaces + "f metros.", DamMathDouble));
                        } else {
                            DamIncreaseOrDrop.setText(String.format("The reservoir increased %." + DamMoreInfoTab.decimalplaces + "f meters.", DamMathDouble));
                        }
                        DamIncreaseOrDrop.setTextColor(Color.parseColor("#13cc00")); //verde
                    } else if (DamLastHourDouble > DamLevelDouble) {
                        double DamMathDouble = DamLastHourDouble - DamLevelDouble;
                        if (as.getLanguage().equals("Spanish")) {
                            DamIncreaseOrDrop.setText(String.format("El embalse disminuyó %." + DamMoreInfoTab.decimalplaces + "f metros", DamMathDouble));
                        } else {
                            DamIncreaseOrDrop.setText(String.format("The reservoir decreased %." + DamMoreInfoTab.decimalplaces + "f meters", DamMathDouble));
                        }
                        DamIncreaseOrDrop.setTextColor(Color.parseColor("#f6000e")); // rojo
                    } else if (DamLastHourDouble == DamLevelDouble) {
                        if (as.getLanguage().equals("Spanish")) {
                            DamIncreaseOrDrop.setText(R.string.same_spanish);
                        } else {
                            DamIncreaseOrDrop.setText(R.string.same_english);
                        }
                        DamIncreaseOrDrop.setTextColor(Color.parseColor("#d9c900")); // amarillo
                    }
                } else {
                    if (as.getLanguage().equals("Spanish")) {
                        DamLastHour.setText(R.string.no_data_last_hour_spanish);
                    } else {
                        DamLastHour.setText(R.string.no_data_last_hour_english);
                    }
                    DamIncreaseOrDrop.setText("");
                }
                if (DamLevelDouble >= DamDesbordeDouble)
                    DamLevel.setTextColor(Color.parseColor("#c000ff")); //violeta
                else if (DamLevelDouble >= DamSeguridadDouble)
                    DamLevel.setTextColor(Color.parseColor("#13cc00")); //verde
                else if (DamLevelDouble >= DamObservacionDouble)
                    DamLevel.setTextColor(Color.parseColor("#0006ff")); //azul
                else if (DamLevelDouble >= DamAjusteDouble)//old yellow code: f1f400
                    DamLevel.setTextColor(Color.parseColor("#d9c900")); //amarillo
                else if (DamLevelDouble >= DamControlDouble)
                    DamLevel.setTextColor(Color.parseColor("#ff7700")); //arananjado
                else //fuera de servicio
                    DamLevel.setTextColor(Color.parseColor("#f6000e")); //rojo
            } catch (Exception e) {
                Log.e("ERROR","An error occurred parsing doubles");
            }
        } else {
            if (as.getLanguage().equals("Spanish")) {
                DamLevel.setText(R.string.no_data_right_now_spanish);
                DamDate.setText(R.string.try_later_spanish);
            } else {
                DamLevel.setText(R.string.no_data_right_now_english);
                DamDate.setText(R.string.try_later_english);
            }
            DamLastHour.setText("");
            DamIncreaseOrDrop.setText("");
        }
        DamLevel.setVisibility(View.VISIBLE);
        DamDate.setVisibility(View.VISIBLE);
        DamLastHour.setVisibility(View.VISIBLE);
        DamIncreaseOrDrop.setVisibility(View.VISIBLE);

        return v;
    }

}
