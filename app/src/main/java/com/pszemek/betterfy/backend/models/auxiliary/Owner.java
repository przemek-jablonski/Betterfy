package com.pszemek.betterfy.backend.models.auxiliary;

/**
 * Created by Ciemek on 12/06/16.
 */
public class Owner {

    /**
     * external url (open.spotify.com) for user
     */
    private ExternalUrls externalUrls;

    /**
     * api url (api.spotify.com) for user
     */
    private String href;

    /**
     * user's id
     */
    private String id;

    /**
     * object type (should be 'user')
     */
    private String type;


    /**
     * spotify URI for the user
     */
    private String uri;




    public ExternalUrls getExternalUrls() {
        return externalUrls;
    }

    public String getHref() {
        return href;
    }

    public String getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getUri() {
        return uri;
    }
}
