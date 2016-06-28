package com.pszemek.betterfy.misc;

import com.pszemek.betterfy.R;

/**
 * Created by Ciemek on 28/06/16.
 */
public class MainActivityItem {

    public int resIdImage;
    public int resIdButton;
    public int resIdText;
    public MainActivityItemEnum type;

    public MainActivityItem(MainActivityItemEnum type) {
        this.type = type;
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
        } else if (type == MainActivityItemEnum.TOP) {
            resIdImage = R.drawable.betterfy_top_001;
            resIdButton = R.string.main_top_button;
            resIdText = R.string.main_top_text;
        }
    }

}
