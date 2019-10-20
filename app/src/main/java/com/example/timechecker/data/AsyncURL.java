package com.example.timechecker.data;

import android.os.AsyncTask;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class AsyncURL extends AsyncTask<String, Void, String> {
    String entityName;

    AsyncURL(String entityName) {
        this.entityName = entityName;
    }

    @Override
    protected String doInBackground(String... params) {
        try {
            Connection conn = Jsoup.connect(params[0]);
            Document doc = conn.get();
            return doc.html();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    public void onPostExecute(String string) {
        // TODO: make this function passed as functor, to remove the dependency
        EntityBuilder.processHtml(entityName, string);
    }
}