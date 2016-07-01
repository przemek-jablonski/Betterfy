package com.pszemek.betterfy.misc;

import android.graphics.Color;
import android.support.annotation.ColorInt;

import com.pszemek.betterfy.R;

/**
 * Created by Ciemek on 28/06/16.
 */
public class MainActivityItem {

    public int resIdImage;
    public int resIdButton;
    @ColorInt public int resIdButtonColour;
    public int resIdText;
    public MainActivityItemEnum type;

    public MainActivityItem(MainActivityItemEnum type) {
        this.type = type;

        resIdButtonColour = Color.parseColor("#1ED760");
        if (type == MainActivityItemEnum.ARTISTS) {
            resIdImage = R.drawable.betterfy_artists_001;
            resIdButton = R.string.main_artists_button;
            resIdText = R.string.main_artists_text;
        } else if (type == MainActivityItemEnum.ALBUMS) {
            resIdImage = R.drawable.betterfy_albums_001;
            resIdButton = R.string.main_albums_button;
            resIdText = R.string.main_albums_text;
        } else if (type == MainActivityItemEnum.PLAYLISTS) {
            resIdImage = R.drawable.betterfy_playlists_002;
            resIdButton = R.string.main_playlist_button;
            resIdText = R.string.main_playlist_text;
        } else if (type == MainActivityItemEnum.DISCOVER) {
            resIdImage = R.drawable.betterfy_discover;
            resIdButton = R.string.main_discover_button;
            resIdText = R.string.main_discover_text;
            resIdButtonColour = Color.parseColor("#FF6F00"); //todo: FIX THIS COLOUR STUFF
        } else if (type == MainActivityItemEnum.TOP) {
            resIdImage = R.drawable.betterfy_top_001;
            resIdButton = R.string.main_top_button;
            resIdText = R.string.main_top_text;
            resIdButtonColour = Color.parseColor("#FF6F00");
        } else if (type == MainActivityItemEnum.FEATURED) {
            resIdImage = R.drawable.betterfy_artists_002;
            resIdButton = R.string.main_featured_button;
            resIdText = R.string.main_featured_text;
            resIdButtonColour = Color.parseColor("#FF6F00");
        } else if (type == MainActivityItemEnum.NEWRELEASES) {
            resIdImage = R.drawable.betterfy_playlists_001;
            resIdButton = R.string.main_newreleases_button;
            resIdText = R.string.main_newreleases_text;
            resIdButtonColour = Color.parseColor("#FF6F00");
        }
    }

}
