package com.pszemek.betterfy.backend.models.auxiliary;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ciemek on 12/06/16.
 */
public class Playlist {

    /**
     * true, if owner allows others to collaborate on playlist
     */
    private boolean collaborative;

    /**
     * external url (open.spotify.com) to playlist
     */
    private ExternalUrls externalUrls;

    /**
     * api url (api.spotify.com) to playlist
     */
    private String href;

    /**
     * spotify playlist id
     */
    private String id;

    /**
     * playlist image (or images if many)
     */
    private List<Image> images = new ArrayList<Image>();

    /**
     * playlist name
     */
    private String name;

    //// TODO: 12/06/16 check what happens if playlist is collaborative (multiple people are contributing)
    //JSON will have a list instead or object or what?
    /**
     * playlist owner (user which created playlist)
     */
    private Owner owner;

    /**
     * is playlist public
     */
    private boolean _public;

    /**
     *  version identifier for the current playlist.
     *  Can be supplied in other requests to target a specific playlist version
     */
    private String snapshotId;

    /**
     *  tracks object
     *  contains:
     *      - api url (to retrieve track info)
     *      - total number of tracks in playlist
     **/
    private Tracks tracks;

    /**
     *  Spotify object type.
     *  should be "playlist"
     */
    private String type;

    /**
     * Spotify URI (Unique Resource Identifier)
     */
    private String uri;



    public boolean isCollaborative() {
        return collaborative;
    }

    public ExternalUrls getExternalUrls() {
        return externalUrls;
    }

    public String getHref() {
        return href;
    }

    public String getId() {
        return id;
    }

    public List<Image> getImages() {
        return images;
    }

    public String getName() {
        return name;
    }

    public Owner getOwner() {
        return owner;
    }

    public boolean is_public() {
        return _public;
    }

    public String getSnapshotId() {
        return snapshotId;
    }

    public Tracks getTracks() {
        return tracks;
    }

    public String getType() {
        return type;
    }

    public String getUri() {
        return uri;
    }
}
