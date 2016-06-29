package com.pszemek.betterfy.misc;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.pszemek.betterfy.R;
import com.pszemek.betterfy.backend.models.PlaylistObject;
import com.pszemek.betterfy.backend.models.UserPublicObject;

import java.net.InetAddress;

/**
 * Created by Ciemek on 23/06/16.
 */
public class Utils {

    public static String getSpotifyAccessToken(final Context context) {
        return getSharedPreferences(context, R.string.sharedpreferences_global).getString(
                context.getString(R.string.spotifyAccessToken_value),
                null
        );
    }

    public static String getStringFromSharedPreferences(final Context context, int sharedPrefsId, String key) {
        return getStringFromSharedPreferences(context, sharedPrefsId, key, null);
    }

    public static String getStringFromSharedPreferences(final Context context, int sharedPrefsId, String key, String defValue) {
        return getSharedPreferences(context, sharedPrefsId).getString(key, defValue);
    }

    public static SharedPreferences getSharedPreferences(final Context context, int sharedPrefsId) {
        return getSharedPreferences(context, sharedPrefsId, Context.MODE_PRIVATE);
    }

    public static SharedPreferences getSharedPreferences(final Context context, int sharedPrefsId, int mode) {
        return context.getSharedPreferences(context.getString(sharedPrefsId), mode);
    }

    public static int checkInternetConnection(final Context context) {
        ConnectivityManager connectivityManager =
                (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = connectivityManager.getActiveNetworkInfo();

        if (activeNetwork != null && activeNetwork.isConnectedOrConnecting()) {
            return activeNetwork.getType();
        }

        return -1;
    }

    public static String checkInternetConnectionString(final Context context) {
        int connectivityType = checkInternetConnection(context);
        if (connectivityType == -1)
            return "NO INTERNET CONNECTION!";
        else if (connectivityType == ConnectivityManager.TYPE_WIFI)
            return "Internet connection: WIFI";
        else if (connectivityType == ConnectivityManager.TYPE_MOBILE)
            return "Internet connection: CELLULAR";
        else
            return "UNDEFINED INTERNET CONNECTION";
    }

    public static String createPlaylistAuxiliaryText(PlaylistObject playlist) {
        StringBuilder builder = new StringBuilder();

        builder.append(playlist.tracks.totalTracks).append(" tracks, ");

        if (playlist.user.displayName != null)
            builder.append(playlist.user.displayName).append(", ");

        if (playlist.isPublic)
            builder.append("public").append(", ");
        else
            builder.append("private").append(", ");

        if (!playlist.collaborative)
            builder.append("non");
        builder.append("collaborative");

        return builder.toString();
    }


}
