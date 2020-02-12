package com.example.timechecker.holiday;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

public class AsyncURLHoliday extends AsyncTask<String, Void, String> {

    private Activity activity;
    private String filename;

    AsyncURLHoliday(Activity activity, String filename) {
        this.activity = activity;
        this.filename = filename;
    }

    @Override
    protected String doInBackground(String... params) {
        // try downloading from website
        try (BufferedInputStream inputStream = new BufferedInputStream(new URL(params[0]).openStream());
             FileOutputStream fileOS = activity.openFileOutput(filename, Context.MODE_PRIVATE)) {
            byte[] data = new byte[1024];
            int byteContent;
            while ((byteContent = inputStream.read(data, 0, 1024)) != -1) {
                fileOS.write(data, 0, byteContent);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "";
    }

    @Override
    public void onPostExecute(String string) {
    }
}
