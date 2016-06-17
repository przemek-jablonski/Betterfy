package com.pszemek.betterfy.misc;

/**
 * Created by Ciemek on 11/06/16.
 */
public class SpotifyAuthorizationScopes {

    //todo move this shit somewhere else
    public static final String BASE_URL_API = "https://api.spotify.com/v1/";

    //TODO instantiate this shit upon opening up app after closeup
    public SpotifyAuthorizationScopes() {
//        SIMPLE_SCOPES = new String[] {
//                USER_READ_PRIVATE,
//                STREAMING
//        };
//
//        FULL_ACCESS_SCOPES = new String[] {
//                USER_READ_PRIVATE,
//                STREAMING,
//                USER_LIBRARY_READ,
//                PLAYLIST_READ_PRIVATE,
//                PLAYLIST_READ_COLLABORATIVE
//        };
    }

    public static String USER_READ_PRIVATE = "user-read-private";
    public static String STREAMING = "streaming";
    public static String USER_LIBRARY_READ = "user-library-read";
    public static String PLAYLIST_READ_PRIVATE = "playlist-read-private";
    public static String PLAYLIST_READ_COLLABORATIVE = "playlist-read-collaborative";

    public static String[] SIMPLE_SCOPES = new String[] {
            USER_READ_PRIVATE,
            STREAMING
    };

    public static String[] FULL_ACCESS_SCOPES = new String[] {
            USER_READ_PRIVATE,
            STREAMING,
            USER_LIBRARY_READ,
            PLAYLIST_READ_PRIVATE,
            PLAYLIST_READ_COLLABORATIVE
    };

}
