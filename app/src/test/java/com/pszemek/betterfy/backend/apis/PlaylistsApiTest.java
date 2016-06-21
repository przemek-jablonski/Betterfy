package com.pszemek.betterfy.backend.apis;

import com.pszemek.betterfy.backend.interceptors.OAuthTokenInterceptor;
import com.pszemek.betterfy.backend.interceptors.PrintResponseInterceptor;
import com.pszemek.betterfy.backend.services.PlaylistsService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

/**
 * Created by Ciemek on 22/06/16.
 */

@RunWith(MockitoJUnitRunner.class)
public class PlaylistsApiTest {

    @Mock
    PlaylistsApi playlistsApi;

    PlaylistsService playlistsService;

    @Test
    public void createService_classType() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
            builder.interceptors().add(new OAuthTokenInterceptor("."));
            builder.interceptors().add(new PrintResponseInterceptor());
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BaseApi.SPOTIFY_API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(builder.build())
                .build();
        playlistsService = retrofit.create(PlaylistsService.class);


        playlistsApi = new PlaylistsApi(true, ".", true);

        assertEquals(playlistsService.getClass(), playlistsApi.getService().getClass());

    }

    @Test
    public void createPlaylistService_null() {
        assertNull(playlistsApi.getService());
    }


    @Test
    public void createPlaylistService_testToFail() {
        assertNotNull(playlistsApi.getService());
    }


}
