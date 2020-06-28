package msc.app.embalsespuertorico

import android.graphics.Color
import androidx.fragment.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView

import java.util.Locale

/**
 * Created by Moises Cardona on 2/7/2017.
 */
class DamMoreInfoFragment : Fragment() {
    //Overriden method onCreateView
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        //Returning the layout file after inflating
        val v = inflater.inflate(R.layout.daminfodata, container, false)
        val DamName = v.findViewById<TextView>(R.id.embalse)
        val DamActual = v.findViewById<TextView>(R.id.fecha)
        val DamLast = v.findViewById<TextView>(R.id.textView5)
        val DamSix = v.findViewById<TextView>(R.id.observacion)
        val Dam12 = v.findViewById<TextView>(R.id.control)
        val Dam24 = v.findViewById<TextView>(R.id.lastHour)
        val Dam7 = v.findViewById<TextView>(R.id.textView13)
        val Dam14 = v.findViewById<TextView>(R.id.textView19)
        val Dam30 = v.findViewById<TextView>(R.id.textView21)
        val Dam60 = v.findViewById<TextView>(R.id.textView23)
        val Dam90 = v.findViewById<TextView>(R.id.textView25)
        val LeyendaText = v.findViewById<TextView>(R.id.textView15)
        val GreenText = v.findViewById<TextView>(R.id.textView14)
        val RedText = v.findViewById<TextView>(R.id.textView16)
        val YellowText = v.findViewById<TextView>(R.id.textView17)
        val DamActualText = v.findViewById<TextView>(R.id.textView2)
        val DamLastHourText = v.findViewById<TextView>(R.id.textView4)
        val DamSixHoursText = v.findViewById<TextView>(R.id.textView6)
        val Dam12HoursText = v.findViewById<TextView>(R.id.ajuste)
        val Dam24HoursText = v.findViewById<TextView>(R.id.nivelesdealerta)
        val DamSevenDaysText = v.findViewById<TextView>(R.id.textView12)
        val Dam14DaysText = v.findViewById<TextView>(R.id.textView18)
        val Dam30DaysText = v.findViewById<TextView>(R.id.textView20)
        val Dam60DaysText = v.findViewById<TextView>(R.id.textView22)
        val Dam90DaysText = v.findViewById<TextView>(R.id.textView24)
        val mAdView = v.findViewById<AdView>(R.id.historyad)
        val adRequest = AdRequest.Builder().build()
        mAdView.loadAd(adRequest)
        DamName.text = DamMoreInfoTab.damNameToDisplay
        val metersText: String
        val noDataText: String
        val `as` = app_settings(activity!!)
        if (`as`.language == "Spanish") {
            metersText = "metros"
            noDataText = "No hay datos disponibles"
            LeyendaText.setText(R.string.leyenda)
            GreenText.setText(R.string.leyenda_verde)
            RedText.setText(R.string.leyenda_rojo)
            YellowText.setText(R.string.leyenda_amarillo)
            DamActualText.setText(R.string.lectura_actual)
            DamLastHourText.setText(R.string.pasada_hora)
            DamSixHoursText.setText(R.string.pasadas_6_horas)
            Dam12HoursText.setText(R.string.pasadas_12_horas)
            Dam24HoursText.setText(R.string.pasadas_24_horas)
            DamSevenDaysText.setText(R.string.pasados_7_dias)
            Dam14DaysText.setText(R.string.pasados_14_dias)
            Dam30DaysText.setText(R.string.pasados_30_dias)
            Dam60DaysText.setText(R.string.pasados_60_dias)
            Dam90DaysText.setText(R.string.pasados_90_dias)
        } else {
            metersText = "meters"
            noDataText = "There's no data available"
            LeyendaText.setText(R.string.key)
            GreenText.setText(R.string.key_green)
            RedText.setText(R.string.key_red)
            YellowText.setText(R.string.key_yellow)
            DamActualText.setText(R.string.right_now)
            DamLastHourText.setText(R.string.last_hour)
            DamSixHoursText.setText(R.string.last_6_hours)
            Dam12HoursText.setText(R.string.last_12_hours)
            Dam24HoursText.setText(R.string.last_24_hours)
            DamSevenDaysText.setText(R.string.last_7_days)
            Dam14DaysText.setText(R.string.last_14_days)
            Dam30DaysText.setText(R.string.last_30_days)
            Dam60DaysText.setText(R.string.last_60_days)
            Dam90DaysText.setText(R.string.last_90_days)
        }
        var DamActualDouble = 0.0
        var DamLastDouble = 0.0
        var DamSixDouble = 0.0
        var DamTwelveDouble = 0.0
        var Dam24Double = 0.0
        var DamSevenDaysDouble = 0.0
        var Dam14Double = 0.0
        var Dam30Double = 0.0
        var Dam60Double = 0.0
        var Dam90Double = 0.0
        if (!DamMoreInfoTab.noDataAvailable) {
            DamActualDouble = java.lang.Double.parseDouble(DamMoreInfoTab.damlevel)
            DamActual.setText(String.format(Locale.US, "%." + DamMoreInfoTab.decimalplaces + "f %s", DamActualDouble, metersText))
        } else
            DamActual.text = noDataText
        if (DamMoreInfoTab.lastHourAvailable) {
            DamLastDouble = java.lang.Double.parseDouble(DamMoreInfoTab.damlasthour)
            DamLast.setText(String.format(Locale.US, "%." + DamMoreInfoTab.decimalplaces + "f %s", DamLastDouble, metersText))
        } else
            DamLast.text = noDataText
        if (DamMoreInfoTab.sixHoursAvailable) {
            DamSixDouble = java.lang.Double.parseDouble(DamMoreInfoTab.dam6hours)
            DamSix.setText(String.format(Locale.US, "%." + DamMoreInfoTab.decimalplaces + "f %s", DamSixDouble, metersText))
        } else
            DamSix.text = noDataText
        if (DamMoreInfoTab.twelveHoursAvailable) {
            DamTwelveDouble = java.lang.Double.parseDouble(DamMoreInfoTab.dam12hours)
            Dam12.setText(String.format(Locale.US, "%." + DamMoreInfoTab.decimalplaces + "f %s", DamTwelveDouble, metersText))
        } else
            Dam12.text = noDataText
        if (DamMoreInfoTab.twentyFourHoursAvailable) {
            Dam24Double = java.lang.Double.parseDouble(DamMoreInfoTab.dam24hours)
            Dam24.setText(String.format(Locale.US, "%." + DamMoreInfoTab.decimalplaces + "f %s", Dam24Double, metersText))
        } else
            Dam24.text = noDataText
        if (DamMoreInfoTab.sevenDaysAvailable) {
            DamSevenDaysDouble = java.lang.Double.parseDouble(DamMoreInfoTab.dam7days)
            Dam7.setText(String.format(Locale.US, "%." + DamMoreInfoTab.decimalplaces + "f %s", DamSevenDaysDouble, metersText))
        } else
            Dam7.text = noDataText
        if (DamMoreInfoTab.days14available) {
            Dam14Double = java.lang.Double.parseDouble(DamMoreInfoTab.dam14days)
            Dam14.setText(String.format(Locale.US, "%." + DamMoreInfoTab.decimalplaces + "f %s", Dam14Double, metersText))
        } else
            Dam14.text = noDataText
        if (DamMoreInfoTab.days30available) {
            Dam30Double = java.lang.Double.parseDouble(DamMoreInfoTab.dam30days)
            Dam30.setText(String.format(Locale.US, "%." + DamMoreInfoTab.decimalplaces + "f %s", Dam30Double, metersText))
        } else
            Dam30.text = noDataText
        if (DamMoreInfoTab.days60available) {
            Dam60Double = java.lang.Double.parseDouble(DamMoreInfoTab.dam60days)
            Dam60.setText(String.format(Locale.US, "%." + DamMoreInfoTab.decimalplaces + "f %s", Dam60Double, metersText))
        } else
            Dam60.text = noDataText
        if (DamMoreInfoTab.days90available) {
            Dam90Double = java.lang.Double.parseDouble(DamMoreInfoTab.dam90days)
            Dam90.setText(String.format(Locale.US, "%." + DamMoreInfoTab.decimalplaces + "f %s", Dam90Double, metersText))
        } else
            Dam90.text = noDataText
        if (DamMoreInfoTab.days90available && DamMoreInfoTab.days60available)
            if (Dam90Double > Dam60Double) {
                Dam60.setTextColor(Color.parseColor("#f6000e")) //rojo
                Dam90.setTextColor(Color.parseColor("#13cc00")) //verde
            } else if (Dam90Double < Dam60Double) {
                Dam60.setTextColor(Color.parseColor("#13cc00")) //verde
                Dam90.setTextColor(Color.parseColor("#f6000e")) //rojo
            } else {
                Dam60.setTextColor(Color.parseColor("#d9c900")) //amarillo
                Dam90.setTextColor(Color.parseColor("#d9c900")) //amarillo
            }
        else {
            Dam60.setTextColor(Color.parseColor("#f6000e")) //rojo
            Dam90.setTextColor(Color.parseColor("#f6000e")) //rojo
        }
        if (DamMoreInfoTab.days60available && DamMoreInfoTab.days30available)
            if (Dam60Double > Dam30Double) {
                Dam60.setTextColor(Color.parseColor("#13cc00")) //verde
                Dam30.setTextColor(Color.parseColor("#f6000e")) //rojo
            } else if (Dam60Double < Dam30Double) {
                Dam60.setTextColor(Color.parseColor("#f6000e")) //rojo
                Dam30.setTextColor(Color.parseColor("#13cc00")) //verde
            } else {
                Dam60.setTextColor(Color.parseColor("#d9c900")) //amarillo
                Dam30.setTextColor(Color.parseColor("#d9c900")) //amarillo
            }
        else {
            Dam30.setTextColor(Color.parseColor("#f6000e")) //rojo
        }
        if (DamMoreInfoTab.days30available && DamMoreInfoTab.days14available)
            if (Dam30Double > Dam14Double) {
                Dam30.setTextColor(Color.parseColor("#13cc00")) //verde
                Dam14.setTextColor(Color.parseColor("#f6000e")) //rojo
            } else if (Dam30Double < Dam14Double) {
                Dam30.setTextColor(Color.parseColor("#f6000e")) //rojo
                Dam14.setTextColor(Color.parseColor("#13cc00")) //verde
            } else {
                Dam30.setTextColor(Color.parseColor("#d9c900")) //amarillo
                Dam14.setTextColor(Color.parseColor("#d9c900")) //amarillo
            }
        else {
            Dam14.setTextColor(Color.parseColor("#f6000e")) //rojo
        }
        if (DamMoreInfoTab.days14available && DamMoreInfoTab.sevenDaysAvailable)
            if (Dam14Double > DamSevenDaysDouble) {
                Dam14.setTextColor(Color.parseColor("#13cc00")) //verde
                Dam7.setTextColor(Color.parseColor("#f6000e")) //rojo
            } else if (Dam14Double < DamSevenDaysDouble) {
                Dam14.setTextColor(Color.parseColor("#f6000e")) //rojo
                Dam7.setTextColor(Color.parseColor("#13cc00")) //verde
            } else {
                Dam14.setTextColor(Color.parseColor("#d9c900")) //amarillo
                Dam7.setTextColor(Color.parseColor("#d9c900")) //amarillo
            }
        else {
            Dam7.setTextColor(Color.parseColor("#f6000e")) //rojo
        }
        if (DamMoreInfoTab.sevenDaysAvailable && DamMoreInfoTab.twentyFourHoursAvailable)
            if (DamSevenDaysDouble > Dam24Double) {
                Dam7.setTextColor(Color.parseColor("#13cc00")) //verde
                Dam24.setTextColor(Color.parseColor("#f6000e")) //rojo
            } else if (DamSevenDaysDouble < Dam24Double) {
                Dam7.setTextColor(Color.parseColor("#f6000e")) //rojo
                Dam24.setTextColor(Color.parseColor("#13cc00")) //verde
            } else {
                Dam7.setTextColor(Color.parseColor("#d9c900")) //amarillo
                Dam24.setTextColor(Color.parseColor("#d9c900")) //amarillo
            }
        else {
            Dam24.setTextColor(Color.parseColor("#f6000e")) //rojo
        }
        if (DamMoreInfoTab.twentyFourHoursAvailable && DamMoreInfoTab.twelveHoursAvailable)
            if (Dam24Double > DamTwelveDouble) {
                Dam24.setTextColor(Color.parseColor("#13cc00")) //verde
                Dam12.setTextColor(Color.parseColor("#f6000e")) //rojo
            } else if (Dam24Double < DamTwelveDouble) {
                Dam24.setTextColor(Color.parseColor("#f6000e")) //rojo
                Dam12.setTextColor(Color.parseColor("#13cc00")) //verde
            } else {
                Dam24.setTextColor(Color.parseColor("#d9c900")) //amarillo
                Dam12.setTextColor(Color.parseColor("#d9c900")) //amarillo
            }
        else
            Dam12.setTextColor(Color.parseColor("#f6000e")) //rojo
        if (DamMoreInfoTab.twelveHoursAvailable && DamMoreInfoTab.sixHoursAvailable)
            if (DamTwelveDouble > DamSixDouble) {
                Dam12.setTextColor(Color.parseColor("#13cc00")) //verde
                DamSix.setTextColor(Color.parseColor("#f6000e")) //rojo
            } else if (DamTwelveDouble < DamSixDouble) {
                Dam12.setTextColor(Color.parseColor("#f6000e")) //rojo
                DamSix.setTextColor(Color.parseColor("#13cc00")) //verde
            } else {
                Dam12.setTextColor(Color.parseColor("#d9c900")) //amarillo
                DamSix.setTextColor(Color.parseColor("#d9c900")) //amarillo
            }
        else
            DamSix.setTextColor(Color.parseColor("#f6000e")) //rojo
        if (DamMoreInfoTab.sixHoursAvailable && DamMoreInfoTab.lastHourAvailable)
            if (DamSixDouble > DamLastDouble) {
                DamSix.setTextColor(Color.parseColor("#13cc00")) //verde
                DamLast.setTextColor(Color.parseColor("#f6000e")) //rojo
            } else if (DamSixDouble < DamLastDouble) {
                DamSix.setTextColor(Color.parseColor("#f6000e")) //rojo
                DamLast.setTextColor(Color.parseColor("#13cc00")) //verde
            } else {
                DamSix.setTextColor(Color.parseColor("#d9c900")) //amarillo
                DamLast.setTextColor(Color.parseColor("#d9c900")) //amarillo
            }
        else
            DamLast.setTextColor(Color.parseColor("#f6000e")) //rojo
        if (DamMoreInfoTab.lastHourAvailable && DamMoreInfoTab.noDataAvailable)
            if (DamLastDouble > DamActualDouble) {
                DamLast.setTextColor(Color.parseColor("#13cc00")) //verde
                DamActual.setTextColor(Color.parseColor("#f6000e")) //rojo
            } else if (DamLastDouble < DamActualDouble) {
                DamLast.setTextColor(Color.parseColor("#f6000e")) //rojo
                DamActual.setTextColor(Color.parseColor("#13cc00")) //verde
            } else {
                DamLast.setTextColor(Color.parseColor("#d9c900")) //amarillo
                DamActual.setTextColor(Color.parseColor("#d9c900")) //amarillo
            }
        else
            DamActual.setTextColor(Color.parseColor("#f6000e")) //rojo
        return v
    }
}
