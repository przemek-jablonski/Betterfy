package com.pszemek.betterfy.backend.interceptors;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Ciemek on 18/06/16.
 */
public class OAuthTokenInterceptor implements Interceptor {

    private final String    AUTHORIZATION = "Authorization";
    private final String    BEARER = "Bearer ";
    private String          spotifyAccessToken = null;


    public OAuthTokenInterceptor(String spotifyAccessToken) {
        this.spotifyAccessToken = spotifyAccessToken;
    }


    @Override
    public Response intercept(Chain chain) throws IOException {
        if (spotifyAccessToken != null) {
            Request request = chain.request();

            request = request.newBuilder()
                    .addHeader(AUTHORIZATION, BEARER + spotifyAccessToken)
                    .build();

            return chain.proceed(request);
        }

        throw new IOException(getClass().getName() + ", Spotify OAuth access token is null");
    }

}
