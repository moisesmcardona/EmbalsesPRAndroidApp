package msc.app.embalsespuertorico;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.jjoe64.graphview.DefaultLabelFormatter;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Moisés Cardona on 1/13/2016.
 */
@SuppressWarnings("ConstantConditions")
public class DamGraph extends Fragment {
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.damgraph, container, false);
        try {
            GraphView graph7days = v.findViewById(R.id.graph7days);
            GraphView graph14days = v.findViewById(R.id.graph14days);
            GraphView graph30days = v.findViewById(R.id.graph30days);
            GraphView graph60days = v.findViewById(R.id.graph60days);
            GraphView graph90days = v.findViewById(R.id.graph90days);
            final TextView DamName = v.findViewById(R.id.embalse);
            final TextView Days7Text = v.findViewById(R.id.textView2);
            final TextView Days14Text = v.findViewById(R.id.fecha);
            final TextView Days30Text = v.findViewById(R.id.textView4);
            final TextView Days60Text = v.findViewById(R.id.textView5);
            final TextView Days90Text = v.findViewById(R.id.textView6);
            final AdView mAdView = v.findViewById(R.id.graphad);
            AdRequest adRequest = new AdRequest.Builder().addTestDevice("03929FBEA0721F82E4AEE15546DBB5DC").build();
            mAdView.loadAd(adRequest);
            app_settings as = new app_settings(getActivity());
            if (as.getLanguage().equals("Spanish")) {
                Days7Text.setText(R.string._7days_spanish);
                Days14Text.setText(R.string._14days_spanish);
                Days30Text.setText(R.string._30days_spanish);
                Days60Text.setText(R.string._60days_spanish);
                Days90Text.setText(R.string._90days_spanish);
            } else {
                Days7Text.setText(R.string._7days_english);
                Days14Text.setText(R.string._14days_english);
                Days30Text.setText(R.string._30days_english);
                Days60Text.setText(R.string._60days_english);
                Days90Text.setText(R.string._90days_english);
            }
            DateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.US);
            SimpleDateFormat timeBack = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.US);
            String getDate = DamMoreInfoTab.time.get(DamMoreInfoTab.time.size() - 1);
            DamName.setText(DamMoreInfoTab.damNameToDisplay);
            Date d = f.parse(getDate);
            Date DateRightNow = new Date(d.getTime());
            ArrayList<Date> dayArray = new ArrayList<>();
            ArrayList<String> dayStringArray = new ArrayList<>();
            for (int i = 0; i < 90; i++) {
                dayArray.add(new Date(d.getTime() - (long) 60 * 60 * 1000 * 24 * (i + 1)));
            }
            for (int i = 0; i < 90; i++) {
                dayStringArray.add(timeBack.format(dayArray.get(i)));
            }
            LineGraphSeries<DataPoint> series7days = new LineGraphSeries<>();
            LineGraphSeries<DataPoint> series14days = new LineGraphSeries<>();
            LineGraphSeries<DataPoint> series30days = new LineGraphSeries<>();
            LineGraphSeries<DataPoint> series60days = new LineGraphSeries<>();
            LineGraphSeries<DataPoint> series90days = new LineGraphSeries<>();
            graph7days.addSeries(series7days);
            series7days.setColor(Color.BLUE);
            graph14days.addSeries(series14days);
            series14days.setColor(Color.BLUE);
            graph30days.addSeries(series30days);
            series30days.setColor(Color.BLUE);
            graph60days.addSeries(series60days);
            series60days.setColor(Color.BLUE);
            graph90days.addSeries(series90days);
            series90days.setColor(Color.BLUE);
            for (int i = 0; i < DamMoreInfoTab.time.size() - 1; i++) {
                if (dayStringArray.contains(DamMoreInfoTab.time.get(i))) {
                    int index = dayStringArray.indexOf(DamMoreInfoTab.time.get(i));
                    //for (int j = 0; j < dayStringArray.size() - 1; j++) {
                    //if (DamMoreInfoTab.time.get(i).equals(dayStringArray.get(i))) {
                    double data = Double.parseDouble(DamMoreInfoTab.meters.get(i));
                    series7days.appendData(new DataPoint(dayArray.get(index), data), true, 8);
                    series14days.appendData(new DataPoint(dayArray.get(index), data), true, 15);
                    series30days.appendData(new DataPoint(dayArray.get(index), data), true, 31);
                    series60days.appendData(new DataPoint(dayArray.get(index), data), true, 61);
                    series90days.appendData(new DataPoint(dayArray.get(index), data), true, 91);
                }
            }
            double rightnowdata = Double.parseDouble(DamMoreInfoTab.meters.get(DamMoreInfoTab.meters.size() - 1));
            series7days.appendData(new DataPoint(DateRightNow, rightnowdata), true, 8);
            series14days.appendData(new DataPoint(DateRightNow, rightnowdata), true, 15);
            series30days.appendData(new DataPoint(DateRightNow, rightnowdata), true, 31);
            series60days.appendData(new DataPoint(DateRightNow, rightnowdata), true, 61);
            series90days.appendData(new DataPoint(DateRightNow, rightnowdata), true, 91);
            graph7days.addSeries(series7days);
            graph14days.addSeries(series14days);
            graph30days.addSeries(series30days);
            graph60days.addSeries(series60days);
            graph90days.addSeries(series90days);
            final DateFormat mDateFormat;
            final Calendar mCalendar;
            mDateFormat = android.text.format.DateFormat.getDateFormat(getActivity());
            mCalendar = Calendar.getInstance();
            graph7days.getGridLabelRenderer().setLabelFormatter(new DefaultLabelFormatter() {
                @Override
                public String formatLabel(double value, boolean isValueX) {
                    if (isValueX) {
                        mCalendar.setTimeInMillis((long) value);
                        return mDateFormat.format(mCalendar.getTimeInMillis());
                    } else {
                        return super.formatLabel(value, isValueX) + " m";
                    }
                }
            });
            graph14days.getGridLabelRenderer().setLabelFormatter(new DefaultLabelFormatter() {
                @Override
                public String formatLabel(double value, boolean isValueX) {
                    if (isValueX) {
                        mCalendar.setTimeInMillis((long) value);
                        return mDateFormat.format(mCalendar.getTimeInMillis());
                    } else {
                        return super.formatLabel(value, isValueX) + " m";
                    }
                }
            });
            graph30days.getGridLabelRenderer().setLabelFormatter(new DefaultLabelFormatter() {
                @Override
                public String formatLabel(double value, boolean isValueX) {
                    if (isValueX) {
                        mCalendar.setTimeInMillis((long) value);
                        return mDateFormat.format(mCalendar.getTimeInMillis());
                    } else {
                        return super.formatLabel(value, isValueX) + " m";
                    }
                }
            });
            graph60days.getGridLabelRenderer().setLabelFormatter(new DefaultLabelFormatter() {
                @Override
                public String formatLabel(double value, boolean isValueX) {
                    if (isValueX) {
                        mCalendar.setTimeInMillis((long) value);
                        return mDateFormat.format(mCalendar.getTimeInMillis());
                    } else {
                        return super.formatLabel(value, isValueX) + " m";
                    }
                }
            });
            graph90days.getGridLabelRenderer().setLabelFormatter(new DefaultLabelFormatter() {
                @Override
                public String formatLabel(double value, boolean isValueX) {
                    if (isValueX) {
                        mCalendar.setTimeInMillis((long) value);
                        return mDateFormat.format(mCalendar.getTimeInMillis());
                    } else {
                        return super.formatLabel(value, isValueX) + " m";
                    }
                }
            });
            graph7days.getGridLabelRenderer().setNumHorizontalLabels(3);
            graph7days.getViewport().setMinX(dayArray.get(6).getTime());
            graph7days.getViewport().setMaxX(DateRightNow.getTime());
            graph7days.getViewport().setXAxisBoundsManual(true);
            graph14days.getGridLabelRenderer().setNumHorizontalLabels(3);
            graph14days.getViewport().setMinX(dayArray.get(13).getTime());
            graph14days.getViewport().setMaxX(DateRightNow.getTime());
            graph14days.getViewport().setXAxisBoundsManual(true);
            graph30days.getGridLabelRenderer().setNumHorizontalLabels(3);
            graph30days.getViewport().setMinX(dayArray.get(29).getTime());
            graph30days.getViewport().setMaxX(DateRightNow.getTime());
            graph30days.getViewport().setXAxisBoundsManual(true);
            graph60days.getGridLabelRenderer().setNumHorizontalLabels(3);
            graph60days.getViewport().setMinX(dayArray.get(59).getTime());
            graph60days.getViewport().setMaxX(DateRightNow.getTime());
            graph60days.getViewport().setXAxisBoundsManual(true);
            graph90days.getGridLabelRenderer().setNumHorizontalLabels(3);
            graph90days.getViewport().setMinX(dayArray.get(89).getTime());
            graph90days.getViewport().setMaxX(DateRightNow.getTime());
            graph90days.getViewport().setXAxisBoundsManual(true);

        } catch (
                Exception e) {
            e.printStackTrace();
        }
        return v;
    }
}
