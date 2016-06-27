package com.pszemek.betterfy.backend.apis;

import com.google.gson.GsonBuilder;
import com.pszemek.betterfy.backend.interceptors.OAuthTokenInterceptor;
import com.pszemek.betterfy.backend.interceptors.PrintResponseInterceptor;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Ciemek on 18/06/16.
 */
public abstract class BaseApi {

    public static final String SPOTIFY_API_BASE_URL = "https://api.spotify.com/v1/";

    protected Retrofit          retrofit;


    public BaseApi(boolean attachOAuthTokenInterceptor, String spotifyAuthorizationToken, boolean attachPrintResponseInterceptor) {

        //todo: this should be instantiated in AppController and then cloned here

        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        if (attachOAuthTokenInterceptor) {
            builder.interceptors().add(new OAuthTokenInterceptor(spotifyAuthorizationToken));
        }
        if (attachPrintResponseInterceptor) {
            builder.interceptors().add(new PrintResponseInterceptor());
        }


        retrofit = new Retrofit.Builder()
                .baseUrl(SPOTIFY_API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create( new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create() ))
                .client(builder.build())
                .build();


    }


}
