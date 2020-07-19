package msc.app.embalsespuertorico

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.FrameLayout
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import java.util.*

/**
 * Created by Moises Cardona on 2/7/2017.
 */
class DamHistoryFragment : Fragment() {
    var compare1 = 0.0
    var compare2 = 0.0
    //Overriden method onCreateView
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.damhistory, container, false)
        val textViewHistory = v.findViewById<TextView>(R.id.textViewHistory)
        val lv = v.findViewById<ListView>(R.id.listView2)
        val adFrame = v.findViewById<FrameLayout>(R.id.damhistoryad)
        val mAdFunctions = AdFunctions()
        mAdFunctions.loadBanner(adFrame, R.string.historyad, activity as AppCompatActivity)
        if (!DamMoreInfoTab.historyAvailable) {
            DamMoreInfoTab.datahistory.add("No hay historial disponible en estos momentos.")
            DamMoreInfoTab.datahistoryEnglish.add("There's no reservoir statistics available at this moment.")
        }
        val `as` = app_settings(activity!!)
        val historyArrayList: ArrayList<String>
        historyArrayList = if (`as`.language == "Spanish")
            DamMoreInfoTab.datahistory
        else
            DamMoreInfoTab.datahistoryEnglish
        textViewHistory.text = DamMoreInfoTab.damNameToDisplay
        if (activity != null) {
            val arrayAdapter = object : ArrayAdapter<String>(activity!!, R.layout.listviewcustomsize, historyArrayList) {
                override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
                    val textView = super.getView(position, convertView, parent) as TextView
                    if (position < DamMoreInfoTab.datahistorymeters.size - 1) {
                        compare1 = java.lang.Double.parseDouble(DamMoreInfoTab.datahistorymeters[position])
                        compare2 = java.lang.Double.parseDouble(DamMoreInfoTab.datahistorymeters[position + 1])
                        if (compare1 > compare2) {
                            textView.setTextColor(Color.parseColor("#13cc00")) //verde
                        }
                        if (compare1 < compare2) {
                            textView.setTextColor(Color.parseColor("#f6000e")) //rojo
                        }
                        if (compare1 == compare2) {
                            textView.setTextColor(Color.parseColor("#d9c900")) //amarillo
                        }
                    }
                    return textView
                }
            }
            lv.adapter = arrayAdapter
        }
        return v
    }
}
