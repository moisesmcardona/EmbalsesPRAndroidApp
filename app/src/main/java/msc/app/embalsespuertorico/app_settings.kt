package msc.app.embalsespuertorico


import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatDelegate

/**
 * Created by Moisés Cardona on 2/16/2019.
 */

class app_settings : Activity {
    private lateinit var settings: SharedPreferences
    var language: String? = "N/A"
        private set
    var defaultView: Int = 0
        private set
    var defaultTheme: Int = AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM
        private set

    constructor() {

    }

    constructor(activity: Activity) {
        settings = activity.getSharedPreferences("embalses_pr", MODE_PRIVATE)
        language = settings.getString("Language", "N/A")
        defaultView = settings.getInt("DefaultView", 0)
        defaultTheme = settings.getInt("DefaultTheme", AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
    }

    private fun setDefaultTheme(){
        AppCompatDelegate.setDefaultNightMode(defaultTheme)
    }
    fun initialize(activity: Activity, activityClass: Class<*>) {
        if (language == "N/A") {
            val editor = settings.edit()
            language = "Spanish"
            editor.putString("Language", language)
            editor.apply()
            setLanguage(activity, activityClass)
        }
        else {
            setDefaultTheme()
        }
    }

    fun setLanguage(activity: Activity?, activityClass: Class<*>) {
        val languageSelection = arrayOf<CharSequence>("Español / Spanish", "Inglés / English")
        val builder = AlertDialog.Builder(activity)
        builder.setTitle("Seleccione un Idioma / Select a Language")
        builder.setItems(languageSelection) { _, which ->
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
            builder.setTitle("Mostrar Lista o Mapa al abrir app")
        } else {
            DefaultViewSelection = arrayOf("List", "Map")
            builder.setTitle("Show map or list when app is launched")
        }
        builder.setItems(DefaultViewSelection) { _, which ->
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
    fun setDefaultTheme(activity: Activity?) {
        val builder = AlertDialog.Builder(activity)
        val DefaultViewSelection: Array<CharSequence>
        if (language == "Spanish") {
            DefaultViewSelection = arrayOf("Claro", "Oscuro", "Modo del sistema por defecto")
            builder.setTitle("Seleccione el tema por defecto")
        } else {
            DefaultViewSelection = arrayOf("Light", "Dark", "System default")
            builder.setTitle("Select default theme to use")
        }
        builder.setItems(DefaultViewSelection) { dialog, which ->
            if (which == 0) {
                defaultTheme = AppCompatDelegate.MODE_NIGHT_NO
            } else if (which == 1) {
                defaultTheme = AppCompatDelegate.MODE_NIGHT_YES
            }
            else{
                defaultTheme = AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM
            }
            val editor = settings.edit()
            editor.putInt("DefaultTheme", defaultTheme)
            editor.apply()
            setDefaultTheme()
        }
        builder.show()
    }
}
