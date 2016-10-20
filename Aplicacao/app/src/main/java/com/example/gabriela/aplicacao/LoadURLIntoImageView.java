package com.example.claquete;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class LoadURLIntoImageView extends AsyncTask<String, Void, Bitmap> {

    private final ImageView mImageView;
    private static final Map<String, Bitmap> mImageCache = new HashMap<>();;

    public LoadURLIntoImageView(ImageView imageView) {
        this.mImageView = imageView;
    }

    @Override
    protected Bitmap doInBackground(String... params) {
        String posterURL = params[0];
        Bitmap bitmap = null;

        if (mImageCache.containsKey(posterURL)) {
            return mImageCache.get(posterURL);
        }

        try {
            URL url = new URL(posterURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            bitmap = BitmapFactory.decodeStream(conn.getInputStream());
            mImageCache.put(posterURL, bitmap);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return bitmap;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        if (mImageView != null && bitmap != null) {
            mImageView.setImageBitmap(bitmap);
        }
    }
}
