package com.pszemek.betterfy.backend.models;

import android.media.Image;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.pszemek.betterfy.backend.models.simplified.ArtistSimpleObject;
import com.pszemek.betterfy.backend.models.simplified.TrackSimpleObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ciemek on 29/06/16.
 */
public class AlbumFullObject {

    @SerializedName("album_type")
    @Expose
    public String albumType;

    @SerializedName("artists")
    @Expose
    public List<ArtistSimpleObject> artists = new ArrayList<>();

    @SerializedName("available_markets")
    @Expose
    public List<String> availableMarkets = new ArrayList<String>();

    @SerializedName("copyrights")
    @Expose
    public List<CopyrightsObject> copyrights = new ArrayList<>(); //?

    @SerializedName("external_ids")
    @Expose
    public ExternalIdObject externalIds;

    @SerializedName("external_urls")
    @Expose
    public ExternalUrlObject externalUrls;

    @SerializedName("genres")
    @Expose
    public List<String> genres = new ArrayList<String>();

    @SerializedName("href")
    @Expose
    public String href;

    @SerializedName("id")
    @Expose
    public String id;

    @SerializedName("images")
    @Expose
    public List<ImageObject> images = new ArrayList<>();

    @SerializedName("name")
    @Expose
    public String name;

    @SerializedName("popularity")
    @Expose
    public int popularity;

    @SerializedName("release_date")
    @Expose
    public String releaseDate;

    @SerializedName("release_date_precision")
    @Expose
    public String releaseDatePrecision;

    @SerializedName("tracks")
    @Expose
    public SpotifyBaseResponse<TrackSimpleObject> tracks;

    @SerializedName("type")
    @Expose
    public String type;

    @SerializedName("uri")
    @Expose
    public String uri;

}

