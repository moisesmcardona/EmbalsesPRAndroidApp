package msc.app.embalsespuertorico

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

import com.jjoe64.graphview.DefaultLabelFormatter
import com.jjoe64.graphview.GraphView
import com.jjoe64.graphview.series.DataPoint
import com.jjoe64.graphview.series.LineGraphSeries

import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by Moisés Cardona on 1/13/2016.
 */
class DamGraph : Fragment() {
    @SuppressLint("MissingPermission")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.damgraph, container, false)
        try {
            val graph7days = v.findViewById<GraphView>(R.id.graph7days)
            val graph14days = v.findViewById<GraphView>(R.id.graph14days)
            val graph30days = v.findViewById<GraphView>(R.id.graph30days)
            val graph60days = v.findViewById<GraphView>(R.id.graph60days)
            val graph90days = v.findViewById<GraphView>(R.id.graph90days)
            val damName = v.findViewById<TextView>(R.id.embalse)
            val days7Text = v.findViewById<TextView>(R.id.textView2)
            val days14Text = v.findViewById<TextView>(R.id.fecha)
            val days30Text = v.findViewById<TextView>(R.id.textView4)
            val days60Text = v.findViewById<TextView>(R.id.textView5)
            val days90Text = v.findViewById<TextView>(R.id.textView6)
            val adFrame = v.findViewById<FrameLayout>(R.id.graphad)
            val mAdFunctions = AdFunctions()
            mAdFunctions.loadBanner(adFrame, R.string.graphAd, activity as AppCompatActivity)

            val `as` = app_settings(activity!!)
            if (`as`.language == "Spanish") {
                days7Text.setText(R.string._7days_spanish)
                days14Text.setText(R.string._14days_spanish)
                days30Text.setText(R.string._30days_spanish)
                days60Text.setText(R.string._60days_spanish)
                days90Text.setText(R.string._90days_spanish)
            } else {
                days7Text.setText(R.string._7days_english)
                days14Text.setText(R.string._14days_english)
                days30Text.setText(R.string._30days_english)
                days60Text.setText(R.string._60days_english)
                days90Text.setText(R.string._90days_english)
            }
            val f = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.US)
            val timeBack = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.US)
            val getDate = DamMoreInfoTab.time[DamMoreInfoTab.time.size - 1]
            damName.text = DamMoreInfoTab.damNameToDisplay
            val d: Date? = f.parse(getDate)
            val dateRightNow = Date(d!!.time)
            val dayArray = ArrayList<Date>()
            val dayStringArray = ArrayList<String>()
            for (i in 0..89) {
                dayArray.add(Date(d.time - 60.toLong() * 60 * 1000 * 24 * (i + 1).toLong()))
            }
            for (i in 0..89) {
                dayStringArray.add(timeBack.format(dayArray[i]))
            }
            val series7days = LineGraphSeries<DataPoint>()
            val series14days = LineGraphSeries<DataPoint>()
            val series30days = LineGraphSeries<DataPoint>()
            val series60days = LineGraphSeries<DataPoint>()
            val series90days = LineGraphSeries<DataPoint>()
            graph7days.addSeries(series7days)
            series7days.color = Color.BLUE
            graph14days.addSeries(series14days)
            series14days.color = Color.BLUE
            graph30days.addSeries(series30days)
            series30days.color = Color.BLUE
            graph60days.addSeries(series60days)
            series60days.color = Color.BLUE
            graph90days.addSeries(series90days)
            series90days.color = Color.BLUE
            for (i in 0 until DamMoreInfoTab.time.size - 1) {
                if (dayStringArray.contains(DamMoreInfoTab.time[i])) {
                    val index = dayStringArray.indexOf(DamMoreInfoTab.time[i])
                    //for (int j = 0; j < dayStringArray.size() - 1; j++) {
                    //if (DamMoreInfoTab.time.get(i).equals(dayStringArray.get(i))) {
                    val data = java.lang.Double.parseDouble(DamMoreInfoTab.meters[i])
                    if (index < 8)
                        series7days.appendData(DataPoint(dayArray[index], data), true, 8)
                    if (index < 15)
                        series14days.appendData(DataPoint(dayArray[index], data), true, 15)
                    if (index < 31)
                        series30days.appendData(DataPoint(dayArray[index], data), true, 31)
                    if (index < 61)
                        series60days.appendData(DataPoint(dayArray[index], data), true, 61)
                    if (index < 91)
                        series90days.appendData(DataPoint(dayArray[index], data), true, 91)
                }
            }
            val rightnowdata = java.lang.Double.parseDouble(DamMoreInfoTab.meters[DamMoreInfoTab.meters.size - 1])
            series7days.appendData(DataPoint(dateRightNow, rightnowdata), true, 8)
            series14days.appendData(DataPoint(dateRightNow, rightnowdata), true, 15)
            series30days.appendData(DataPoint(dateRightNow, rightnowdata), true, 31)
            series60days.appendData(DataPoint(dateRightNow, rightnowdata), true, 61)
            series90days.appendData(DataPoint(dateRightNow, rightnowdata), true, 91)
            graph7days.addSeries(series7days)
            graph14days.addSeries(series14days)
            graph30days.addSeries(series30days)
            graph60days.addSeries(series60days)
            graph90days.addSeries(series90days)
            val mDateFormat: DateFormat = android.text.format.DateFormat.getDateFormat(activity)
            val mCalendar: Calendar = Calendar.getInstance()
            graph7days.gridLabelRenderer.labelFormatter = object : DefaultLabelFormatter() {
                override fun formatLabel(value: Double, isValueX: Boolean): String {
                    return if (isValueX) {
                        mCalendar.timeInMillis = value.toLong()
                        mDateFormat.format(mCalendar.timeInMillis)
                    } else {
                        super.formatLabel(value, isValueX) + " m"
                    }
                }
            }
            graph14days.gridLabelRenderer.labelFormatter = object : DefaultLabelFormatter() {
                override fun formatLabel(value: Double, isValueX: Boolean): String {
                    return if (isValueX) {
                        mCalendar.timeInMillis = value.toLong()
                        mDateFormat.format(mCalendar.timeInMillis)
                    } else {
                        super.formatLabel(value, isValueX) + " m"
                    }
                }
            }
            graph30days.gridLabelRenderer.labelFormatter = object : DefaultLabelFormatter() {
                override fun formatLabel(value: Double, isValueX: Boolean): String {
                    return if (isValueX) {
                        mCalendar.timeInMillis = value.toLong()
                        mDateFormat.format(mCalendar.timeInMillis)
                    } else {
                        super.formatLabel(value, isValueX) + " m"
                    }
                }
            }
            graph60days.gridLabelRenderer.labelFormatter = object : DefaultLabelFormatter() {
                override fun formatLabel(value: Double, isValueX: Boolean): String {
                    return if (isValueX) {
                        mCalendar.timeInMillis = value.toLong()
                        mDateFormat.format(mCalendar.timeInMillis)
                    } else {
                        super.formatLabel(value, isValueX) + " m"
                    }
                }
            }
            graph90days.gridLabelRenderer.labelFormatter = object : DefaultLabelFormatter() {
                override fun formatLabel(value: Double, isValueX: Boolean): String {
                    return if (isValueX) {
                        mCalendar.timeInMillis = value.toLong()
                        mDateFormat.format(mCalendar.timeInMillis)
                    } else {
                        super.formatLabel(value, isValueX) + " m"
                    }
                }
            }
            graph7days.gridLabelRenderer.numHorizontalLabels = 3
            graph7days.viewport.setMinX(dayArray[6].time.toDouble())
            graph7days.viewport.setMaxX(dateRightNow.time.toDouble())
            graph7days.viewport.isXAxisBoundsManual = true
            graph14days.gridLabelRenderer.numHorizontalLabels = 3
            graph14days.viewport.setMinX(dayArray[13].time.toDouble())
            graph14days.viewport.setMaxX(dateRightNow.time.toDouble())
            graph14days.viewport.isXAxisBoundsManual = true
            graph30days.gridLabelRenderer.numHorizontalLabels = 3
            graph30days.viewport.setMinX(dayArray[29].time.toDouble())
            graph30days.viewport.setMaxX(dateRightNow.time.toDouble())
            graph30days.viewport.isXAxisBoundsManual = true
            graph60days.gridLabelRenderer.numHorizontalLabels = 3
            graph60days.viewport.setMinX(dayArray[59].time.toDouble())
            graph60days.viewport.setMaxX(dateRightNow.time.toDouble())
            graph60days.viewport.isXAxisBoundsManual = true
            graph90days.gridLabelRenderer.numHorizontalLabels = 3
            graph90days.viewport.setMinX(dayArray[89].time.toDouble())
            graph90days.viewport.setMaxX(dateRightNow.time.toDouble())
            graph90days.viewport.isXAxisBoundsManual = true

        } catch (e: Exception) {
            e.printStackTrace()
        }

        return v
    }
}
