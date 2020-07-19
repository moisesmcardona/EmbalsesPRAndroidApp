package msc.app.embalsespuertorico

import android.graphics.Color
import androidx.fragment.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

import java.util.Locale

/**
 * Created by Moises Cardona on 2/7/2017.
 */
class DamMoreInfoFragment : Fragment() {
    //Overriden method onCreateView
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        //Returning the layout file after inflating
        val v = inflater.inflate(R.layout.daminfodata, container, false)
        val damName = v.findViewById<TextView>(R.id.embalse)
        val damActual = v.findViewById<TextView>(R.id.fecha)
        val damLast = v.findViewById<TextView>(R.id.textView5)
        val dam6 = v.findViewById<TextView>(R.id.observacion)
        val dam12 = v.findViewById<TextView>(R.id.control)
        val dam24 = v.findViewById<TextView>(R.id.lastHour)
        val dam7 = v.findViewById<TextView>(R.id.textView13)
        val dam14 = v.findViewById<TextView>(R.id.textView19)
        val dam30 = v.findViewById<TextView>(R.id.textView21)
        val dam60 = v.findViewById<TextView>(R.id.textView23)
        val dam90 = v.findViewById<TextView>(R.id.textView25)
        val legendText = v.findViewById<TextView>(R.id.textView15)
        val geenText = v.findViewById<TextView>(R.id.textView14)
        val redText = v.findViewById<TextView>(R.id.textView16)
        val yellowText = v.findViewById<TextView>(R.id.textView17)
        val damActualText = v.findViewById<TextView>(R.id.textView2)
        val damLastHourText = v.findViewById<TextView>(R.id.textView4)
        val dam6HoursText = v.findViewById<TextView>(R.id.textView6)
        val dam12HoursText = v.findViewById<TextView>(R.id.ajuste)
        val dam24HoursText = v.findViewById<TextView>(R.id.nivelesdealerta)
        val dam7daysText = v.findViewById<TextView>(R.id.textView12)
        val dam14daysText = v.findViewById<TextView>(R.id.textView18)
        val dam30daysText = v.findViewById<TextView>(R.id.textView20)
        val dam60daysText = v.findViewById<TextView>(R.id.textView22)
        val dam90daysText = v.findViewById<TextView>(R.id.textView24)
        val adFrame = v.findViewById<FrameLayout>(R.id.historyad)
        val mAdFunctions = AdFunctions()
        mAdFunctions.loadBanner(adFrame, R.string.damdataad, activity as AppCompatActivity)
        damName.text = DamMoreInfoTab.damNameToDisplay
        val metersText: String
        val noDataText: String
        val `as` = app_settings(activity!!)
        if (`as`.language == "Spanish") {
            metersText = "metros"
            noDataText = "No hay datos disponibles"
            legendText.setText(R.string.leyenda)
            geenText.setText(R.string.leyenda_verde)
            redText.setText(R.string.leyenda_rojo)
            yellowText.setText(R.string.leyenda_amarillo)
            damActualText.setText(R.string.lectura_actual)
            damLastHourText.setText(R.string.pasada_hora)
            dam6HoursText.setText(R.string.pasadas_6_horas)
            dam12HoursText.setText(R.string.pasadas_12_horas)
            dam24HoursText.setText(R.string.pasadas_24_horas)
            dam7daysText.setText(R.string.pasados_7_dias)
            dam14daysText.setText(R.string.pasados_14_dias)
            dam30daysText.setText(R.string.pasados_30_dias)
            dam60daysText.setText(R.string.pasados_60_dias)
            dam90daysText.setText(R.string.pasados_90_dias)
        } else {
            metersText = "meters"
            noDataText = "There's no data available"
            legendText.setText(R.string.key)
            geenText.setText(R.string.key_green)
            redText.setText(R.string.key_red)
            yellowText.setText(R.string.key_yellow)
            damActualText.setText(R.string.right_now)
            damLastHourText.setText(R.string.last_hour)
            dam6HoursText.setText(R.string.last_6_hours)
            dam12HoursText.setText(R.string.last_12_hours)
            dam24HoursText.setText(R.string.last_24_hours)
            dam7daysText.setText(R.string.last_7_days)
            dam14daysText.setText(R.string.last_14_days)
            dam30daysText.setText(R.string.last_30_days)
            dam60daysText.setText(R.string.last_60_days)
            dam90daysText.setText(R.string.last_90_days)
        }
        var damActualDouble = 0.0
        var damLastDouble = 0.0
        var dam6Double = 0.0
        var dam12Double = 0.0
        var dam24Double = 0.0
        var dam7Double = 0.0
        var dam14Double = 0.0
        var dam30Double = 0.0
        var dam60Double = 0.0
        var dam90Double = 0.0
        if (!DamMoreInfoTab.noDataAvailable) {
            damActualDouble = java.lang.Double.parseDouble(DamMoreInfoTab.damlevel)
            damActual.text = String.format(Locale.US, "%." + DamMoreInfoTab.decimalplaces + "f %s", damActualDouble, metersText)
        } else
            damActual.text = noDataText
        if (DamMoreInfoTab.lastHourAvailable) {
            damLastDouble = java.lang.Double.parseDouble(DamMoreInfoTab.damlasthour)
            damLast.text = String.format(Locale.US, "%." + DamMoreInfoTab.decimalplaces + "f %s", damLastDouble, metersText)
        } else
            damLast.text = noDataText
        if (DamMoreInfoTab.sixHoursAvailable) {
            dam6Double = java.lang.Double.parseDouble(DamMoreInfoTab.dam6hours)
            dam6.text = String.format(Locale.US, "%." + DamMoreInfoTab.decimalplaces + "f %s", dam6Double, metersText)
        } else
            dam6.text = noDataText
        if (DamMoreInfoTab.twelveHoursAvailable) {
            dam12Double = java.lang.Double.parseDouble(DamMoreInfoTab.dam12hours)
            dam12.text = String.format(Locale.US, "%." + DamMoreInfoTab.decimalplaces + "f %s", dam12Double, metersText)
        } else
            dam12.text = noDataText
        if (DamMoreInfoTab.twentyFourHoursAvailable) {
            dam24Double = java.lang.Double.parseDouble(DamMoreInfoTab.dam24hours)
            dam24.text = String.format(Locale.US, "%." + DamMoreInfoTab.decimalplaces + "f %s", dam24Double, metersText)
        } else
            dam24.text = noDataText
        if (DamMoreInfoTab.sevenDaysAvailable) {
            dam7Double = java.lang.Double.parseDouble(DamMoreInfoTab.dam7days)
            dam7.text = String.format(Locale.US, "%." + DamMoreInfoTab.decimalplaces + "f %s", dam7Double, metersText)
        } else
            dam7.text = noDataText
        if (DamMoreInfoTab.days14available) {
            dam14Double = java.lang.Double.parseDouble(DamMoreInfoTab.dam14days)
            dam14.text = String.format(Locale.US, "%." + DamMoreInfoTab.decimalplaces + "f %s", dam14Double, metersText)
        } else
            dam14.text = noDataText
        if (DamMoreInfoTab.days30available) {
            dam30Double = java.lang.Double.parseDouble(DamMoreInfoTab.dam30days)
            dam30.text = String.format(Locale.US, "%." + DamMoreInfoTab.decimalplaces + "f %s", dam30Double, metersText)
        } else
            dam30.text = noDataText
        if (DamMoreInfoTab.days60available) {
            dam60Double = java.lang.Double.parseDouble(DamMoreInfoTab.dam60days)
            dam60.text = String.format(Locale.US, "%." + DamMoreInfoTab.decimalplaces + "f %s", dam60Double, metersText)
        } else
            dam60.text = noDataText
        if (DamMoreInfoTab.days90available) {
            dam90Double = java.lang.Double.parseDouble(DamMoreInfoTab.dam90days)
            dam90.text = String.format(Locale.US, "%." + DamMoreInfoTab.decimalplaces + "f %s", dam90Double, metersText)
        } else
            dam90.text = noDataText
        if (DamMoreInfoTab.days90available && DamMoreInfoTab.days60available) {
            when {
                dam90Double > dam60Double -> {
                    dam60.setTextColor(Color.parseColor("#f6000e")) //rojo
                    dam90.setTextColor(Color.parseColor("#13cc00")) //verde
                }
                dam90Double < dam60Double -> {
                    dam60.setTextColor(Color.parseColor("#13cc00")) //verde
                    dam90.setTextColor(Color.parseColor("#f6000e")) //rojo
                }
                else -> {
                    dam60.setTextColor(Color.parseColor("#d9c900")) //amarillo
                    dam90.setTextColor(Color.parseColor("#d9c900")) //amarillo
                }
            }
        } else {
            dam60.setTextColor(Color.parseColor("#f6000e")) //rojo
            dam90.setTextColor(Color.parseColor("#f6000e")) //rojo
        }
        if (DamMoreInfoTab.days60available && DamMoreInfoTab.days30available)
            when {
                dam60Double > dam30Double -> {
                    dam60.setTextColor(Color.parseColor("#13cc00")) //verde
                    dam30.setTextColor(Color.parseColor("#f6000e")) //rojo
                }
                dam60Double < dam30Double -> {
                    dam60.setTextColor(Color.parseColor("#f6000e")) //rojo
                    dam30.setTextColor(Color.parseColor("#13cc00")) //verde
                }
                else -> {
                    dam60.setTextColor(Color.parseColor("#d9c900")) //amarillo
                    dam30.setTextColor(Color.parseColor("#d9c900")) //amarillo
                }
            }
        else {
            dam30.setTextColor(Color.parseColor("#f6000e")) //rojo
        }
        if (DamMoreInfoTab.days30available && DamMoreInfoTab.days14available)
            when {
                dam30Double > dam14Double -> {
                    dam30.setTextColor(Color.parseColor("#13cc00")) //verde
                    dam14.setTextColor(Color.parseColor("#f6000e")) //rojo
                }
                dam30Double < dam14Double -> {
                    dam30.setTextColor(Color.parseColor("#f6000e")) //rojo
                    dam14.setTextColor(Color.parseColor("#13cc00")) //verde
                }
                else -> {
                    dam30.setTextColor(Color.parseColor("#d9c900")) //amarillo
                    dam14.setTextColor(Color.parseColor("#d9c900")) //amarillo
                }
            }
        else {
            dam14.setTextColor(Color.parseColor("#f6000e")) //rojo
        }
        if (DamMoreInfoTab.days14available && DamMoreInfoTab.sevenDaysAvailable)
            when {
                dam14Double > dam7Double -> {
                    dam14.setTextColor(Color.parseColor("#13cc00")) //verde
                    dam7.setTextColor(Color.parseColor("#f6000e")) //rojo
                }
                dam14Double < dam7Double -> {
                    dam14.setTextColor(Color.parseColor("#f6000e")) //rojo
                    dam7.setTextColor(Color.parseColor("#13cc00")) //verde
                }
                else -> {
                    dam14.setTextColor(Color.parseColor("#d9c900")) //amarillo
                    dam7.setTextColor(Color.parseColor("#d9c900")) //amarillo
                }
            }
        else {
            dam7.setTextColor(Color.parseColor("#f6000e")) //rojo
        }
        if (DamMoreInfoTab.sevenDaysAvailable && DamMoreInfoTab.twentyFourHoursAvailable)
            when {
                dam7Double > dam24Double -> {
                    dam7.setTextColor(Color.parseColor("#13cc00")) //verde
                    dam24.setTextColor(Color.parseColor("#f6000e")) //rojo
                }
                dam7Double < dam24Double -> {
                    dam7.setTextColor(Color.parseColor("#f6000e")) //rojo
                    dam24.setTextColor(Color.parseColor("#13cc00")) //verde
                }
                else -> {
                    dam7.setTextColor(Color.parseColor("#d9c900")) //amarillo
                    dam24.setTextColor(Color.parseColor("#d9c900")) //amarillo
                }
            }
        else {
            dam24.setTextColor(Color.parseColor("#f6000e")) //rojo
        }
        if (DamMoreInfoTab.twentyFourHoursAvailable && DamMoreInfoTab.twelveHoursAvailable)
            when {
                dam24Double > dam12Double -> {
                    dam24.setTextColor(Color.parseColor("#13cc00")) //verde
                    dam12.setTextColor(Color.parseColor("#f6000e")) //rojo
                }
                dam24Double < dam12Double -> {
                    dam24.setTextColor(Color.parseColor("#f6000e")) //rojo
                    dam12.setTextColor(Color.parseColor("#13cc00")) //verde
                }
                else -> {
                    dam24.setTextColor(Color.parseColor("#d9c900")) //amarillo
                    dam12.setTextColor(Color.parseColor("#d9c900")) //amarillo
                }
            }
        else
            dam12.setTextColor(Color.parseColor("#f6000e")) //rojo
        if (DamMoreInfoTab.twelveHoursAvailable && DamMoreInfoTab.sixHoursAvailable)
            when {
                dam12Double > dam6Double -> {
                    dam12.setTextColor(Color.parseColor("#13cc00")) //verde
                    dam6.setTextColor(Color.parseColor("#f6000e")) //rojo
                }
                dam12Double < dam6Double -> {
                    dam12.setTextColor(Color.parseColor("#f6000e")) //rojo
                    dam6.setTextColor(Color.parseColor("#13cc00")) //verde
                }
                else -> {
                    dam12.setTextColor(Color.parseColor("#d9c900")) //amarillo
                    dam6.setTextColor(Color.parseColor("#d9c900")) //amarillo
                }
            }
        else
            dam6.setTextColor(Color.parseColor("#f6000e")) //rojo
        if (DamMoreInfoTab.sixHoursAvailable && DamMoreInfoTab.lastHourAvailable)
            when {
                dam6Double > damLastDouble -> {
                    dam6.setTextColor(Color.parseColor("#13cc00")) //verde
                    damLast.setTextColor(Color.parseColor("#f6000e")) //rojo
                }
                dam6Double < damLastDouble -> {
                    dam6.setTextColor(Color.parseColor("#f6000e")) //rojo
                    damLast.setTextColor(Color.parseColor("#13cc00")) //verde
                }
                else -> {
                    dam6.setTextColor(Color.parseColor("#d9c900")) //amarillo
                    damLast.setTextColor(Color.parseColor("#d9c900")) //amarillo
                }
            }
        else
            damLast.setTextColor(Color.parseColor("#f6000e")) //rojo
        if (DamMoreInfoTab.lastHourAvailable && DamMoreInfoTab.noDataAvailable)
            when {
                damLastDouble > damActualDouble -> {
                    damLast.setTextColor(Color.parseColor("#13cc00")) //verde
                    damActual.setTextColor(Color.parseColor("#f6000e")) //rojo
                }
                damLastDouble < damActualDouble -> {
                    damLast.setTextColor(Color.parseColor("#f6000e")) //rojo
                    damActual.setTextColor(Color.parseColor("#13cc00")) //verde
                }
                else -> {
                    damLast.setTextColor(Color.parseColor("#d9c900")) //amarillo
                    damActual.setTextColor(Color.parseColor("#d9c900")) //amarillo
                }
            }
        else
            damActual.setTextColor(Color.parseColor("#f6000e")) //rojo
        return v
    }
}
