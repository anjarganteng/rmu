package com.tripoin.rmumobile.rest.base;

import android.util.Log;

import java.io.IOException;

/**
 * Created by Achmad Fauzi on 11/19/2014.
 * fauzi.knightmaster.achmad@gmail.com
 *
 * This Class is used to communicate with REST method GET without progress bar
 */
public abstract class ARestGETAsyncTask extends ABaseRest {

    @Override
    protected String doInBackground(String... params) {
        Log.e("URL WS", initUrl());
        jsonObject = getJsonParser().retrieveJSONAsGet( initUrl() );
        try {
            objectResult = objectMapper.readValue( String.valueOf(jsonObject), initClassResult() );
        } catch (IOException e) {
            Log.e("ERROR WEB SERVICE", e.toString());
        }
        return null;
    }

}
