package msc.app.embalsespuertorico

import androidx.fragment.app.Fragment
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.RequestConfiguration
import java.util.*

/**
 * Created by Moisés Cardona on 9/25/2015.
 */
class GetDamInfoAndShow : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.dampage, container, false)
        val DamPic = v.findViewById<ImageView>(R.id.imageView)
        val DamName = v.findViewById<TextView>(R.id.embalse)
        val DamDesborde = v.findViewById<TextView>(R.id.desborde)
        val DamSeguridad = v.findViewById<TextView>(R.id.seguridad)
        val DamObservacion = v.findViewById<TextView>(R.id.observacion)
        val DamAjuste = v.findViewById<TextView>(R.id.ajuste)
        val DamControl = v.findViewById<TextView>(R.id.control)
        val LeyendaText = v.findViewById<TextView>(R.id.nivelesdealerta)
        val DamLevel = v.findViewById<TextView>(R.id.level)
        val DamDate = v.findViewById<TextView>(R.id.fecha)
        val DamLastHour = v.findViewById<TextView>(R.id.lastHour)
        val DamIncreaseOrDrop = v.findViewById<TextView>(R.id.droporincrease)
        val mAdView = v.findViewById<AdView>(R.id.damad)
        val configuration = RequestConfiguration.Builder().setTestDeviceIds(Arrays.asList(getString(R.string.deviceTestID))).build()
        MobileAds.setRequestConfiguration(configuration)
        val adRequest = AdRequest.Builder().build()
        mAdView.loadAd(adRequest)
        DamName.text = DamMoreInfoTab.damNameToDisplay
        DamPic.setImageResource(DamMoreInfoTab.imageToUse)
        val `as` = app_settings(activity!!)
        if (`as`.language == "Spanish") {
            LeyendaText.setText(R.string.nivelesdealerta)
            DamDesborde.setText(String.format(Locale.US, "Desborde: %s", DamMoreInfoTab.desborde))
            DamSeguridad.setText(String.format(Locale.US, "Seguridad: %s", DamMoreInfoTab.seguridad))
            DamObservacion.setText(String.format(Locale.US, "Observación: %s", DamMoreInfoTab.observacion))
            DamAjuste.setText(String.format(Locale.US, "Ajuste: %s", DamMoreInfoTab.ajuste))
            DamControl.setText(String.format(Locale.US, "Control: %s", DamMoreInfoTab.control))
        } else {
            LeyendaText.setText(R.string.alert_levels)
            DamDesborde.setText(String.format(Locale.US, "Overflow: %s", DamMoreInfoTab.desborde))
            DamSeguridad.setText(String.format(Locale.US, "Security: %s", DamMoreInfoTab.seguridad))
            DamObservacion.setText(String.format(Locale.US, "Observation: %s", DamMoreInfoTab.observacion))
            DamAjuste.setText(String.format(Locale.US, "Adjustment: %s", DamMoreInfoTab.ajuste))
            DamControl.setText(String.format(Locale.US, "Control: %s", DamMoreInfoTab.control))
        }
        if (!DamMoreInfoTab.noDataAvailable) {
            try {
                if (`as`.language == "Spanish") {
                    DamLevel.setText(String.format(Locale.US, "%s metros", DamMoreInfoTab.damlevel))
                    DamDate.text = DamMoreInfoTab.damdate
                } else {
                    DamLevel.setText(String.format(Locale.US, "%s meters", DamMoreInfoTab.damlevel))
                    DamDate.text = DamMoreInfoTab.damdateEnglish
                }

                val DamLevelDouble = java.lang.Double.parseDouble(DamMoreInfoTab.damlevel)
                val DamDesbordeDouble = java.lang.Double.parseDouble(DamMoreInfoTab.desborde)
                val DamSeguridadDouble = java.lang.Double.parseDouble(DamMoreInfoTab.seguridad)
                val DamObservacionDouble = java.lang.Double.parseDouble(DamMoreInfoTab.observacion)
                val DamAjusteDouble = java.lang.Double.parseDouble(DamMoreInfoTab.ajuste)
                val DamControlDouble = java.lang.Double.parseDouble(DamMoreInfoTab.control)
                if (DamMoreInfoTab.lastHourAvailable) {
                    if (`as`.language == "Spanish") {
                        DamLastHour.setText(String.format("Lectura en la pasada hora: %s metros", DamMoreInfoTab.damlasthour))
                    } else {
                        DamLastHour.setText(String.format("Data from last hour: %s meters", DamMoreInfoTab.damlasthour))
                    }
                    val DamLastHourDouble = java.lang.Double.parseDouble(DamMoreInfoTab.damlasthour)
                    if (DamLastHourDouble < DamLevelDouble) {
                        val DamMathDouble = DamLevelDouble - DamLastHourDouble
                        if (`as`.language == "Spanish") {
                            DamIncreaseOrDrop.setText(String.format("El embalse aumentó %." + DamMoreInfoTab.decimalplaces + "f metros.", DamMathDouble))
                        } else {
                            DamIncreaseOrDrop.setText(String.format("The reservoir increased %." + DamMoreInfoTab.decimalplaces + "f meters.", DamMathDouble))
                        }
                        DamIncreaseOrDrop.setTextColor(Color.parseColor("#13cc00")) //verde
                    } else if (DamLastHourDouble > DamLevelDouble) {
                        val DamMathDouble = DamLastHourDouble - DamLevelDouble
                        if (`as`.language == "Spanish") {
                            DamIncreaseOrDrop.setText(String.format("El embalse disminuyó %." + DamMoreInfoTab.decimalplaces + "f metros", DamMathDouble))
                        } else {
                            DamIncreaseOrDrop.setText(String.format("The reservoir decreased %." + DamMoreInfoTab.decimalplaces + "f meters", DamMathDouble))
                        }
                        DamIncreaseOrDrop.setTextColor(Color.parseColor("#f6000e")) // rojo
                    } else if (DamLastHourDouble == DamLevelDouble) {
                        if (`as`.language == "Spanish") {
                            DamIncreaseOrDrop.setText(R.string.same_spanish)
                        } else {
                            DamIncreaseOrDrop.setText(R.string.same_english)
                        }
                        DamIncreaseOrDrop.setTextColor(Color.parseColor("#d9c900")) // amarillo
                    }
                } else {
                    if (`as`.language == "Spanish") {
                        DamLastHour.setText(R.string.no_data_last_hour_spanish)
                    } else {
                        DamLastHour.setText(R.string.no_data_last_hour_english)
                    }
                    DamIncreaseOrDrop.text = ""
                }
                if (DamLevelDouble >= DamDesbordeDouble)
                    DamLevel.setTextColor(Color.parseColor("#c000ff")) //violeta
                else if (DamLevelDouble >= DamSeguridadDouble)
                    DamLevel.setTextColor(Color.parseColor("#13cc00")) //verde
                else if (DamLevelDouble >= DamObservacionDouble)
                    DamLevel.setTextColor(Color.parseColor("#0006ff")) //azul
                else if (DamLevelDouble >= DamAjusteDouble)
                //old yellow code: f1f400
                    DamLevel.setTextColor(Color.parseColor("#d9c900")) //amarillo
                else if (DamLevelDouble >= DamControlDouble)
                    DamLevel.setTextColor(Color.parseColor("#ff7700")) //arananjado
                else
                //fuera de servicio
                    DamLevel.setTextColor(Color.parseColor("#f6000e")) //rojo
            } catch (e: Exception) {
                Log.e("ERROR", "An error occurred parsing doubles")
            }

        } else {
            if (`as`.language == "Spanish") {
                DamLevel.setText(R.string.no_data_right_now_spanish)
                DamDate.setText(R.string.try_later_spanish)
            } else {
                DamLevel.setText(R.string.no_data_right_now_english)
                DamDate.setText(R.string.try_later_english)
            }
            DamLastHour.text = ""
            DamIncreaseOrDrop.text = ""
        }
        DamLevel.visibility = View.VISIBLE
        DamDate.visibility = View.VISIBLE
        DamLastHour.visibility = View.VISIBLE
        DamIncreaseOrDrop.visibility = View.VISIBLE

        return v
    }

}
