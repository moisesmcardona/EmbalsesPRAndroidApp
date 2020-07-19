package msc.app.embalsespuertorico

import android.app.Activity
import android.content.Intent
import android.net.Uri

/**
 * Created by Moisés Cardona on 2/16/2019.
 */

class DrawerItems : Activity() {

    @JvmOverloads
    fun mainDrawerItems(activity: Activity, activityClass: Class<*>, position: Int, my_position: Int, video: Boolean = false, id: String? = null) {
        var offset = 0
        if (video)
            offset++
        if (position == 0 && position != my_position)
        //Lista/Mapa de embalses
        {
            val main = Intent(activity, MainActivityTab::class.java)
            activity.startActivity(main)
        } else if (position == 1 && position != my_position && video) {
            val player = Intent(activity, YTPlayer::class.java)
            player.putExtra("video", id)
            activity.startActivity(player)
        } else if (position == 1 + offset && position != my_position)
        //Acerca de
        {
            val main = Intent(activity, AboutApp::class.java)
            activity.startActivity(main)
        } else if (position == 2 + offset && position != my_position)
        //Facebook Page
        {
            launchFBPage(activity)
        } else if (position == 3 + offset && position != my_position)
        //Language Select
        {
            val appLanguage = app_settings(activity)
            appLanguage.setLanguage(activity, activityClass)
        } else if (position == 4 + offset)
        //Default Select
        {
            val defaultView = app_settings(activity)
            defaultView.setDefaultView(activity, activityClass)
        } else if (position == 5 + offset)
        //Default Select
        {
            launchPrivacyPolicy(activity)
        }
    }

    private fun launchFBPage(activity: Activity) {
        try {
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse("fb://page/172345859774139"))
            activity.startActivity(browserIntent)
        } catch (e: Exception) {
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse("http://facebook.com/EmbalsesPRApp"))
            activity.startActivity(browserIntent)
        }

    }

    private fun launchPrivacyPolicy(activity: Activity) {
        val `as` = app_settings(activity)
        val browserIntent: Intent
        browserIntent = if (`as`.language == "Spanish") {
            Intent(Intent.ACTION_VIEW, Uri.parse("https://moisescardona.me/PoliticaPrivacidadEmbalsesPR"))
        } else {
            Intent(Intent.ACTION_VIEW, Uri.parse("https://moisescardona.me/EmbalsesPRPrivacyPolicy"))
        }
        activity.startActivity(browserIntent)
    }
}
