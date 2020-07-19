package msc.app.embalsespuertorico

import androidx.fragment.app.Fragment
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

import java.util.*

/**
 * Created by Moisés Cardona on 9/25/2015.
 */
class GetDamInfoAndShow : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.dampage, container, false)
        val damPic = v.findViewById<ImageView>(R.id.imageView)
        val damName = v.findViewById<TextView>(R.id.embalse)
        val damDesborde = v.findViewById<TextView>(R.id.desborde)
        val damSeguridad = v.findViewById<TextView>(R.id.seguridad)
        val damObservacion = v.findViewById<TextView>(R.id.observacion)
        val damAjuste = v.findViewById<TextView>(R.id.ajuste)
        val damControl = v.findViewById<TextView>(R.id.control)
        val leyendaText = v.findViewById<TextView>(R.id.nivelesdealerta)
        val damLevel = v.findViewById<TextView>(R.id.level)
        val damDate = v.findViewById<TextView>(R.id.fecha)
        val damLastHour = v.findViewById<TextView>(R.id.lastHour)
        val damIncreaseOrDrop = v.findViewById<TextView>(R.id.droporincrease)
        val adFrame = v.findViewById<FrameLayout>(R.id.damad)
        val mAdFunctions = AdFunctions()
        mAdFunctions.loadBanner(adFrame, R.string.dampagead, activity as AppCompatActivity)
        damName.text = DamMoreInfoTab.damNameToDisplay
        damPic.setImageResource(DamMoreInfoTab.imageToUse)
        val `as` = app_settings(activity!!)
        if (`as`.language == "Spanish") {
            leyendaText.setText(R.string.nivelesdealerta)
            damDesborde.text = String.format(Locale.US, "Desborde: %s", DamMoreInfoTab.desborde)
            damSeguridad.text = String.format(Locale.US, "Seguridad: %s", DamMoreInfoTab.seguridad)
            damObservacion.text = String.format(Locale.US, "Observación: %s", DamMoreInfoTab.observacion)
            damAjuste.text = String.format(Locale.US, "Ajuste: %s", DamMoreInfoTab.ajuste)
            damControl.text = String.format(Locale.US, "Control: %s", DamMoreInfoTab.control)
        } else {
            leyendaText.setText(R.string.alert_levels)
            damDesborde.text = String.format(Locale.US, "Overflow: %s", DamMoreInfoTab.desborde)
            damSeguridad.text = String.format(Locale.US, "Security: %s", DamMoreInfoTab.seguridad)
            damObservacion.text = String.format(Locale.US, "Observation: %s", DamMoreInfoTab.observacion)
            damAjuste.text = String.format(Locale.US, "Adjustment: %s", DamMoreInfoTab.ajuste)
            damControl.text = String.format(Locale.US, "Control: %s", DamMoreInfoTab.control)
        }
        if (!DamMoreInfoTab.noDataAvailable) {
            try {
                if (`as`.language == "Spanish") {
                    damLevel.text = String.format(Locale.US, "%s metros", DamMoreInfoTab.damlevel)
                    damDate.text = DamMoreInfoTab.damdate
                } else {
                    damLevel.text = String.format(Locale.US, "%s meters", DamMoreInfoTab.damlevel)
                    damDate.text = DamMoreInfoTab.damdateEnglish
                }

                val damLevelDouble = java.lang.Double.parseDouble(DamMoreInfoTab.damlevel)
                val damDesbordeDouble = java.lang.Double.parseDouble(DamMoreInfoTab.desborde)
                val damSeguridadDouble = java.lang.Double.parseDouble(DamMoreInfoTab.seguridad)
                val damObservacionDouble = java.lang.Double.parseDouble(DamMoreInfoTab.observacion)
                val damAjusteDouble = java.lang.Double.parseDouble(DamMoreInfoTab.ajuste)
                val damControlDouble = java.lang.Double.parseDouble(DamMoreInfoTab.control)
                if (DamMoreInfoTab.lastHourAvailable) {
                    if (`as`.language == "Spanish") {
                        damLastHour.text = String.format("Lectura en la pasada hora: %s metros", DamMoreInfoTab.damlasthour)
                    } else {
                        damLastHour.text = String.format("Data from last hour: %s meters", DamMoreInfoTab.damlasthour)
                    }
                    val damLastHourDouble = java.lang.Double.parseDouble(DamMoreInfoTab.damlasthour)
                    when {
                        damLastHourDouble < damLevelDouble -> {
                            val damMathDouble = damLevelDouble - damLastHourDouble
                            if (`as`.language == "Spanish") {
                                damIncreaseOrDrop.text = String.format("El embalse aumentó %." + DamMoreInfoTab.decimalplaces + "f metros.", damMathDouble)
                            } else {
                                damIncreaseOrDrop.text = String.format("The reservoir increased %." + DamMoreInfoTab.decimalplaces + "f meters.", damMathDouble)
                            }
                            damIncreaseOrDrop.setTextColor(Color.parseColor("#13cc00")) //verde
                        }
                        damLastHourDouble > damLevelDouble -> {
                            val damMathDouble = damLastHourDouble - damLevelDouble
                            if (`as`.language == "Spanish") {
                                damIncreaseOrDrop.text = String.format("El embalse disminuyó %." + DamMoreInfoTab.decimalplaces + "f metros", damMathDouble)
                            } else {
                                damIncreaseOrDrop.text = String.format("The reservoir decreased %." + DamMoreInfoTab.decimalplaces + "f meters", damMathDouble)
                            }
                            damIncreaseOrDrop.setTextColor(Color.parseColor("#f6000e")) // rojo
                        }
                        damLastHourDouble == damLevelDouble -> {
                            if (`as`.language == "Spanish") {
                                damIncreaseOrDrop.setText(R.string.same_spanish)
                            } else {
                                damIncreaseOrDrop.setText(R.string.same_english)
                            }
                            damIncreaseOrDrop.setTextColor(Color.parseColor("#d9c900")) // amarillo
                        }
                    }
                } else {
                    if (`as`.language == "Spanish") {
                        damLastHour.setText(R.string.no_data_last_hour_spanish)
                    } else {
                        damLastHour.setText(R.string.no_data_last_hour_english)
                    }
                    damIncreaseOrDrop.text = ""
                }
                when {
                    damLevelDouble >= damDesbordeDouble -> damLevel.setTextColor(Color.parseColor("#c000ff")) //violeta
                    damLevelDouble >= damSeguridadDouble -> damLevel.setTextColor(Color.parseColor("#13cc00")) //verde
                    damLevelDouble >= damObservacionDouble -> damLevel.setTextColor(Color.parseColor("#0006ff")) //azul
                    damLevelDouble >= damAjusteDouble -> damLevel.setTextColor(Color.parseColor("#d9c900")) //amarillo
                    damLevelDouble >= damControlDouble -> damLevel.setTextColor(Color.parseColor("#ff7700")) //arananjado
                    else -> damLevel.setTextColor(Color.parseColor("#f6000e")) //rojo
                }
            } catch (e: Exception) {
                Log.e("ERROR", "An error occurred parsing doubles")
            }

        } else {
            if (`as`.language == "Spanish") {
                damLevel.setText(R.string.no_data_right_now_spanish)
                damDate.setText(R.string.try_later_spanish)
            } else {
                damLevel.setText(R.string.no_data_right_now_english)
                damDate.setText(R.string.try_later_english)
            }
            damLastHour.text = ""
            damIncreaseOrDrop.text = ""
        }
        damLevel.visibility = View.VISIBLE
        damDate.visibility = View.VISIBLE
        damLastHour.visibility = View.VISIBLE
        damIncreaseOrDrop.visibility = View.VISIBLE

        return v
    }

}
