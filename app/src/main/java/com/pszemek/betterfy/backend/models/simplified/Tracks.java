package com.pszemek.betterfy.backend.models.simplified;

/**
 * Created by Ciemek on 12/06/16.
 */
public class Tracks {

    /**
     *  api url (api.spotify.com) to retrieve tracks info
     */
    private String href;

    /**
     *  total tracks count in playlist
     */
    private int total;


    public int getTotal() {
        return total;
    }

    public String getHref() {
        return href;
    }
}
