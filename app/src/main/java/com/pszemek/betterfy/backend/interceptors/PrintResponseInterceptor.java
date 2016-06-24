package com.pszemek.betterfy.backend.interceptors;

import android.util.Log;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;

/**
 * Created by Ciemek on 18/06/16.
 */
public class PrintResponseInterceptor implements Interceptor {

    private final   String LOGTAG = "Retrofit";
    private final   String REQUEST = "Request:" + "\n";
    private final   String RESPONSE = "Response:" + "\n";
    private final   int    REQUEST_LOG_LEVEL = Log.DEBUG;
    private final   int    RESPONSE_LOG_LEVEL = Log.ERROR;
    private         String spotifyAccessToken = null;

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        long t1 = System.nanoTime();

        spotifyAccessToken = request.header("Authorization").substring(0, 25);
        String loggingDialog = REQUEST + "URL: " + request.url() + "\n" + "OAuth token: " + spotifyAccessToken;
        Log.println(REQUEST_LOG_LEVEL, LOGTAG, loggingDialog);

        Response response = chain.proceed(request);
        long t2 = System.nanoTime();
        String bodyString = response.body().string();
        if (response.body() == null) {
            //todo: exception here
            loggingDialog = RESPONSE +
                    "TIME: " + ((t2 - t1) / 1e6d) + "ms" + "\n" +
                    "URL: " + request.url() + "\n" +
                    "REQUEST BODY FOR GIVEN URL IS NULL!";
        } else {
            loggingDialog = RESPONSE +
                    "URL: " + response.request().url() + "\n" +
                    "CODE: " + response.code() + " / " + response.message() + "\n" +
                    "TIME: " + ((t2 - t1) / 1e6d) + "ms" + "\n" +
                    "DATE: " + response.header("Date") + "\n" +
                    "TYPE: " + response.header("Content-Type");
            Log.println(RESPONSE_LOG_LEVEL, LOGTAG, loggingDialog + "\n" + bodyString);
        }


        return response.newBuilder()
                .body(ResponseBody.create(response.body().contentType(), bodyString))
                .build();
    }



}
