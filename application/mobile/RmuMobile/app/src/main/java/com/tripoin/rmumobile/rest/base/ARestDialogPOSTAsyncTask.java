package com.tripoin.rmumobile.rest.base;

import android.util.Log;

import java.io.IOException;

/**
 * Created by Achmad Fauzi on 11/21/2014.
 * fauzi.knightmaster.achmad@gmail.com
 *
 * This Class is used to communicate with REST method POST with progress bar
 */
public abstract class ARestDialogPOSTAsyncTask extends ABaseDialogRest {

    @Override
    protected String doInBackground(String... params) {
        Log.e("URL WS", initUrl());
        jsonObject = getJsonParser().getJSONFromUrl( initUrl() );
        try {
            objectResult = objectMapper.readValue(String.valueOf(jsonObject), initClassResult());
        } catch (IOException e) {
            Log.e("ERROR WEB SERVICE", e.toString());
        }
        return null;
    }

}
