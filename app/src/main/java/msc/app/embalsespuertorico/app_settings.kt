package msc.app.embalsespuertorico


import android.app.AlertDialog
import android.content.Intent
import android.content.SharedPreferences

import android.app.Activity

/**
 * Created by Moisés Cardona on 2/16/2019.
 */

class app_settings : Activity {
    private lateinit var settings: SharedPreferences
    var language: String? = null
        private set
    var defaultView: Int = 0
        private set

    constructor() {

    }

    constructor(activity: Activity) {
        settings = activity.getSharedPreferences("embalses_pr", MODE_PRIVATE)
        language = settings.getString("Language", null)
        defaultView = settings.getInt("DefaultView", 0)
    }

    fun initialize(activity: Activity, activityClass: Class<*>) {
        if (language == null) {
            val editor = settings.edit()
            language = "Spanish"
            editor.putString("Language", language)
            editor.apply()
            setLanguage(activity, activityClass)
        }

    }

    fun setLanguage(activity: Activity?, activityClass: Class<*>) {
        val languageSelection = arrayOf<CharSequence>("Español / Spanish", "Inglés / English")
        val builder = AlertDialog.Builder(activity)
        builder.setTitle("Seleccione un Idioma / Select a Language")
        builder.setItems(languageSelection) { dialog, which ->
            if (which == 0) {
                language = "Spanish"
            } else if (which == 1) {
                language = "English"
            }
            val editor = settings.edit()
            editor.putString("Language", language)
            editor.apply()
            if (activity != null) {
                activity.startActivity(Intent(activity, activityClass))
                finish()
            }
        }
        builder.show()
    }

    fun setDefaultView(activity: Activity?, activityClass: Class<*>) {
        val builder = AlertDialog.Builder(activity)
        val DefaultViewSelection: Array<CharSequence>
        if (language == "Spanish") {
            DefaultViewSelection = arrayOf("Lista", "Mapa")
            builder.setTitle("Mostrar Lista o Mapa al abrir app:")
        } else {
            DefaultViewSelection = arrayOf("List", "Map")
            builder.setTitle("Show map or list when app is launched")
        }
        builder.setItems(DefaultViewSelection) { dialog, which ->
            if (which == 0) {
                defaultView = 0
            } else if (which == 1) {
                defaultView = 1
            }
            val editor = settings.edit()
            editor.putInt("DefaultView", defaultView)
            editor.apply()
            activity?.startActivity(Intent(activity, activityClass))
        }
        builder.show()
    }
}
