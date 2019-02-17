package msc.app.embalsespuertorico;


import android.app.Application;
import android.content.Context;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;

import android.app.Activity;
import android.os.Bundle;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by Moisés Cardona on 2/16/2019.
 */

public class app_settings extends Activity {
    private SharedPreferences settings;
    private String language;
    private int defaultView;

    public app_settings(){

    }

    public app_settings(Activity activity) {
        settings  = activity.getSharedPreferences("embalses_pr", MODE_PRIVATE);
        language = settings.getString("Language", null);
        defaultView = settings.getInt("DefaultView", 0);
    }

    public void initialize(Activity activity , Class activityClass) {
        if (language == null) {
            SharedPreferences.Editor editor = settings.edit();
            language = "Spanish";
            editor.putString("Language", language);
            editor.apply();
            setLanguage(activity, activityClass);
        }

    }

    public void setLanguage(final Activity activity, final Class activityClass) {
        CharSequence languageSelection[] = new CharSequence[]{"Español / Spanish", "Inglés / English"};
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle("Seleccione un Idioma / Select a Language");
        builder.setItems(languageSelection, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (which == 0) {
                    language = "Spanish";
                } else if (which == 1) {
                    language = "English";
                }
                SharedPreferences.Editor editor = settings.edit();
                editor.putString("Language", language);
                editor.apply();
                if (activity != null)
                    activity.startActivity(new Intent(activity, activityClass));
            }
        });
        builder.show();
    }

    public void setDefaultView(final Activity activity, final Class activityClass) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        CharSequence languageSelection[];
        if (language.equals("Spanish")) {
            languageSelection = new CharSequence[]{"Lista", "Mapa"};
            builder.setTitle("Mostrar Lista o Mapa al abrir app:");
        } else {
            languageSelection = new CharSequence[]{"List", "Map"};
            builder.setTitle("Show map or list when app is launched");
        }
        builder.setItems(languageSelection, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (which == 0) {
                    defaultView = 0;
                } else if (which == 1) {
                    defaultView = 1;
                }
                SharedPreferences.Editor editor = settings.edit();
                editor.putInt("DefaultView", defaultView);
                editor.apply();
                if (activity != null)
                    activity.startActivity(new Intent(activity, activityClass));
            }
        });
        builder.show();
    }

    public int getDefaultView() {
        return defaultView;
    }

    public String getLanguage() {
        return language;
    }
}
