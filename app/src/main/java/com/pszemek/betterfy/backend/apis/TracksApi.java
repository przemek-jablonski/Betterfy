package com.pszemek.betterfy.backend.apis;

import android.support.annotation.Nullable;

import com.pszemek.betterfy.backend.models.TrackModel;
import com.pszemek.betterfy.backend.services.PlaylistsService;
import com.pszemek.betterfy.backend.services.TracksService;

import java.util.List;

import retrofit2.Callback;

/**
 * Created by Ciemek on 22/06/16.
 */
public class TracksApi extends BaseApi {

    private TracksService service;

    public TracksApi(boolean attachOAuthTokenInterceptor, String spotifyAuthorizationToken, boolean attachPrintResponseInterceptor) {
        super(attachOAuthTokenInterceptor, spotifyAuthorizationToken, attachPrintResponseInterceptor);
        createService();
    }

    public TracksService createService() {
        service = retrofit.create(TracksService.class);
        return service;
    }

    public void getPlaylistTracks(String userId, String playlistId,
                                  @Nullable String marketCode, /*@Nullable String[] fields?*/
                                  @Nullable Integer limit, @Nullable Integer offset,
                                  Callback<List<TrackModel>> callback) {

//        if (limit > 50 || limit < 1 || offset < 0) {
//            //todo write custom exception for improper OFFSET or LIMIT
//            return;
//        }

        service.getPlaylistTracks(userId, playlistId, marketCode, limit, offset).enqueue(callback);
    }


    public TracksService getService() {
        return service;
    }
}
