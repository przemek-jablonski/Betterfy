package com.pszemek.betterfy.backend.apis;


import com.pszemek.betterfy.backend.models.SpotifyBaseResponse;
import com.pszemek.betterfy.backend.models.simplified.AlbumSimpleObject;
import com.pszemek.betterfy.backend.services.BrowseSpotifyService;

import retrofit2.Callback;

/**
 * Created by Ciemek on 01/07/16.
 */
public class BrowseSpotifyApi extends BaseApi {

    private BrowseSpotifyService service;

    public BrowseSpotifyApi(boolean attachOAuthTokenInterceptor, String spotifyAuthorizationToken, boolean attachPrintResponseInterceptor) {
        super(attachOAuthTokenInterceptor, spotifyAuthorizationToken, attachPrintResponseInterceptor);
        createService();
    }

    private void createService() {
        service = retrofit.create(BrowseSpotifyService.class);
    }

    public void getNewReleasesWorldwide(Integer limit, Integer offset, Callback<SpotifyBaseResponse<AlbumSimpleObject>> callback) {
        service.getNewReleasesAlbums(null, limit, offset).enqueue(callback);
    }

    public void getNewReleases(String countryCode, Integer limit, Integer offset, Callback<SpotifyBaseResponse<AlbumSimpleObject>> callback) {
        service.getNewReleasesAlbums(countryCode, limit, offset).enqueue(callback);
    }

}
