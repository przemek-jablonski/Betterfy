package com.pszemek.betterfy.backend.services;

import com.pszemek.betterfy.backend.models.TracksModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Ciemek on 20/06/16.
 */
public interface TracksService {

    enum TimeRange {
        long_term,      //calculation scope of several (~4) years
        medium_term,    //calculation scope of last 4 months
        short_term      //calculation scope of last 4 weeks
    }

    @GET("tracks/{track_id}")
    Call<TracksModel> getTrack(
            @Path("track_id")   String  trackId,
            @Query("market")    String  marketCode
    );


//    @GET("tracks")
//    Call<List<TracksModel>> getTracks(
//            @Query("tracks_id")  String[] tracksId,  //todo: propably (String[] tracksId) is wrong, fixit!
//            @Query("market")    String marketCode
//    );


//    @GET("audio-features/{track_id}")
//    Call<AudioFeaturesModel> getTrackAudioFeatures(
//            @Path("track_id")  String trackId
//    );


//    @GET("audio-features")
//    Call<List<AudioFeaturesModel>> getTracksAudioFeatures(
//            @Query("ids")       String[] tracksId   //todo: propably (String[] tracksId) is wrong, fixit!
//    );


    @GET("albums/{album_id}/tracks")
    Call<List<TracksModel>> getAlbumTracks(
            @Path("album_id")   String  albumId,
            @Query("limit")     Integer limit,
            @Query("offset")    Integer offset,
            @Query("market")    String  marketCode
    );


    @GET("artists/{artist_id}/top-tracks")
    Call<List<TracksModel>> getArtistTracksTop(
            @Path("artist_id")  String  artistId,
            @Query("country")   String  countryCode
    );


    @GET("artists/{artist_id}/related-artists")
    Call<List<TracksModel>> getArtistTracksRelated(
            @Path("artist_id")  String  artistId
    );


    @GET("users/{user_id}/playlists/{playlist_id}/tracks")
    Call<TracksModel> getPlaylistTracks(
            @Path("user_id")    String  userId,
            @Path("playlist_id") String playlistId,
            @Query("market")    String  marketCode,
            /*@Query("fields")    String[] fields,*/     //todo: figure out this FIELD shit
            @Query("limit")     Integer limit,
            @Query("offset")    Integer offset
    );


    @GET("me/tracks")
    Call<List<TracksModel>> getLoggedUserSavedTracks(
            @Query("market")    String  marketCode,
            @Query("limit")     Integer limit,
            @Query("offset")    Integer offset
    );

    @GET("me/top/tracks")
    Call<List<TracksModel>> getLoggedUserTopTracks(
            @Query("limit")     Integer limit,
            @Query("offset")    String  offset,
            @Query("time_range") TimeRange timeRange
    );
}
