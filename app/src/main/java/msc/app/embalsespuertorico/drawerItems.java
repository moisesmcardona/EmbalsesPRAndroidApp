package msc.app.embalsespuertorico;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;

/**
 * Created by Moisés Cardona on 2/16/2019.
 */

public class drawerItems extends Activity {
    public void mainDrawerItems(Activity activity, Class activityClass, int position, int my_position) {
        mainDrawerItems(activity, activityClass, position, my_position, false, null);
    }

    public void mainDrawerItems(Activity activity, Class activityClass, int position, int my_position, boolean video, String id) {
        int offset = 0;
        if (video)
            offset++;
        if (position == 0 && position != my_position) //Lista/Mapa de embalses
        {
            Intent main = new Intent(activity, MainActivityTab.class);
            activity.startActivity(main);
        } else if (position == 1 && position != my_position && video) {
            Intent player = new Intent(activity, YTPlayer.class);
            player.putExtra("video", id);
            activity.startActivity(player);
        } else if (position == 1 + offset && position != my_position) //Acerca de
        {
            Intent main = new Intent(activity, AboutApp.class);
            activity.startActivity(main);
        } else if (position == 2 + offset && position != my_position) //Facebook Page
        {
            launchFBPage(activity);
        } else if (position == 3 + offset && position != my_position) //Language Select
        {
            app_settings app_language = new app_settings(activity);
            app_language.setLanguage(activity, activityClass);
        } else if (position == 4 + offset) //Default Select
        {
            app_settings default_view = new app_settings(activity);
            default_view.setDefaultView(activity, activityClass);
        } else if (position == 5 + offset) //Default Select
        {
            launchPrivacyPolicy(activity);
        }
    }

    private void launchFBPage(Activity activity) {
        try {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("fb://page/172345859774139"));
            activity.startActivity(browserIntent);
        } catch (Exception e) {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://facebook.com/EmbalsesPRApp"));
            activity.startActivity(browserIntent);
        }
    }

    private void launchPrivacyPolicy(Activity activity){
        app_settings as = new app_settings(activity);
        Intent browserIntent;
        if (as.getLanguage().equals("Spanish")) {
            browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://moisescardona.me/PoliticaPrivacidadEmbalsesPR"));
        }
        else {
            browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://moisescardona.me/EmbalsesPRPrivacyPolicy"));
        }
        activity.startActivity(browserIntent);
    }
}
