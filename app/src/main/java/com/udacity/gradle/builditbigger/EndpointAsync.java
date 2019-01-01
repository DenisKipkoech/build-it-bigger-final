package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

import com.example.jokedisplaylibrary.JokeActivity;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.udacity.gradle.builditbigger.backend.myApi.MyApi;

import java.io.IOException;

/**
 * Created by denis on 31/12/18.
 */

public class EndpointAsync extends AsyncTask< Context, Void, String> {
    private static MyApi apiService = null;
    private Context context;
    @Override
    protected String doInBackground(Context... contexts) {
        if (apiService == null){
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)

            .setRootUrl("http://10.0.2.2:8080/_ah/api/")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> request) throws IOException {
                            request.setDisableGZipContent(true);
                        }
                    });

            apiService = builder.build();
        }

        context = contexts[0];
        try{
            return apiService.sendJoke().execute().getData();
        }catch (IOException e){
           return e.getMessage();
        }
    }

    @Override
    protected void onPostExecute(String s) {
//        super.onPostExecute(s);

        Intent intent = new Intent(context, JokeActivity.class);
        intent.putExtra("joke", s);
        context.startActivity(intent);
    }
}
