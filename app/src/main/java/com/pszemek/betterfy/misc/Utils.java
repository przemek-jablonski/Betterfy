package com.pszemek.betterfy.misc;

import android.content.Context;
import android.content.SharedPreferences;

import com.pszemek.betterfy.R;

/**
 * Created by Ciemek on 23/06/16.
 */
public class Utils {

    public static String getSpotifyAccessToken(Context context) {
        return getSharedPreferences(context, R.string.sharedpreferences_userdata).getString(
                context.getString(R.string.spotifyAccessToken_value),
                null
        );
    }

    public static String getStringFromSharedPreferences(Context context, int sharedPrefsId, String key) {
        return getStringFromSharedPreferences(context, sharedPrefsId, key, null);
    }

    public static String getStringFromSharedPreferences(Context context, int sharedPrefsId, String key, String defValue) {
        return getSharedPreferences(context, sharedPrefsId).getString(key, defValue);
    }

    public static SharedPreferences getSharedPreferences(Context context, int sharedPrefsId) {
        return getSharedPreferences(context, sharedPrefsId, Context.MODE_PRIVATE);
    }

    public static SharedPreferences getSharedPreferences(Context context, int sharedPrefsId, int mode) {
        return context.getSharedPreferences(context.getString(sharedPrefsId), mode);
    }


}
