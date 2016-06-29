package com.pszemek.betterfy.backend.apis;

import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;

import com.pszemek.betterfy.backend.models.AlbumFullObject;
import com.pszemek.betterfy.backend.models.SpotifyBaseResponse;
import com.pszemek.betterfy.backend.models.simplified.AddedAtResponse;
import com.pszemek.betterfy.backend.services.AlbumsService;

import retrofit2.Callback;

/**
 * Created by Ciemek on 28/06/16.
 */
public class AlbumsApi extends BaseApi {

    private AlbumsService service;

    public AlbumsApi(boolean attachOAuthTokenInterceptor, String spotifyAuthorizationToken, boolean attachPrintResponseInterceptor) {
        super(attachOAuthTokenInterceptor, spotifyAuthorizationToken, attachPrintResponseInterceptor);
        createService();
    }

    private void createService() {
        service = retrofit.create(AlbumsService.class);
    }



    public void getSavedAlbums(
            @Nullable Integer limit,
            @Nullable Integer offset,
            @Nullable String  marketCode,
            Callback<SpotifyBaseResponse<AddedAtResponse<AlbumFullObject>>> callback) {

        service.getSavedAlbums(limit, offset, marketCode).enqueue(callback);

    }


    public AlbumsService getService() {
        return service;
    }
}
