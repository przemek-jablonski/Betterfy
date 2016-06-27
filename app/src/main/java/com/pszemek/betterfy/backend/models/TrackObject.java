package com.pszemek.betterfy.backend.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.pszemek.betterfy.backend.models.simplified.AlbumSimpleObject;
import com.pszemek.betterfy.backend.models.simplified.ArtistSimpleObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ciemek on 20/06/16.
 */
public class TrackObject {

    @SerializedName("album")
    @Expose
    public AlbumSimpleObject album;

    @SerializedName("artists")
    @Expose
    public List<ArtistSimpleObject> artists = new ArrayList<>();

    @SerializedName("available_markets")
    public List<String> availableMarkets = new ArrayList<>();

    @SerializedName("disc_number")
    @Expose
    public int discNumber;

    @SerializedName("duration_ms")
    @Expose
    public int durationMs;

    @SerializedName("explicit")
    @Expose
    public boolean explicit;

    @SerializedName("external_ids")
    @Expose
    public ExternalIdObject externalId;

    @SerializedName("external_urls")
    @Expose
    public ExternalUrlObject externalUrl;

    @SerializedName("href")
    @Expose
    public String href;

    @SerializedName("id")
    @Expose
    public String id;

    @SerializedName("is_playable")
    @Expose
    public boolean isPlayable;

    @SerializedName("linked_from")
    public TrackLinkObject linkedFromTrack;

    @SerializedName("name")
    @Expose
    public String name;

    @SerializedName("popularity")
    @Expose
    public int popularity;

    @SerializedName("preview_url")
    @Expose
    public String previewUrl;

    @SerializedName("track_number")
    @Expose
    public int trackNumber;

    @SerializedName("type")
    @Expose
    public String type;

    @SerializedName("uri")
    @Expose
    public String uri;

}
