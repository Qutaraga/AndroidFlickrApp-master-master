package com.example.qutaraga.myapplicationtestparsedelete;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;

import java.io.IOException;
import java.net.URL;

public class GetBitmapTask implements Runnable{
    private final String uri;
    private final Callback callback;
    ImageView image;
    public GetBitmapTask(ImageView image, String uri, Callback callback) {
        this.uri = uri;
        this.callback = callback;
        this.image = image;
    }

    @Override public void run() {
        try {
            URL url = new URL(uri);
            final Bitmap bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());

            callback.onFinish(image,bmp,uri);


        } catch (IOException e) {
            callback.onError(e);
        }
    }

    public interface Callback{
        void onFinish(ImageView image,Bitmap bitmap,String url);
        void onError(Throwable t);
    }
}
