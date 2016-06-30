package com.pszemek.betterfy.backend.apis;

import com.pszemek.betterfy.backend.models.AlbumFullObject;
import com.pszemek.betterfy.backend.models.ArtistFullObject;
import com.pszemek.betterfy.backend.models.SpotifyBaseResponse;
import com.pszemek.betterfy.backend.models.TrackFullObject;
import com.pszemek.betterfy.backend.services.TopService;

import retrofit2.Callback;

/**
 * Created by Ciemek on 30/06/16.
 */
public class TopApi extends BaseApi {

    private TopService service;

    public TopApi(boolean attachOAuthTokenInterceptor, String spotifyAuthorizationToken, boolean attachPrintResponseInterceptor) {
        super(attachOAuthTokenInterceptor, spotifyAuthorizationToken, attachPrintResponseInterceptor);
        createService();
    }


    private void createService() {
        service = retrofit.create(TopService.class);
    }



    public void getTopArtists(Integer limit, Integer offset, String timeRange, Callback<SpotifyBaseResponse<ArtistFullObject>> callback) {
        service.getTopArtists(limit, offset, timeRange).enqueue(callback);
    }

    public void getTopTracks(Integer limit, Integer offset, String timeRange, Callback<SpotifyBaseResponse<TrackFullObject>> callback) {
        service.getTopTracks(limit, offset, timeRange).enqueue(callback);
    }


    public TopService getService() {
        return service;
    }
}
