package com.example.qutaraga.myapplicationtestparsedelete.Utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.qutaraga.myapplicationtestparsedelete.GetBitmapTask;
import com.example.qutaraga.myapplicationtestparsedelete.MyList;
import com.example.qutaraga.myapplicationtestparsedelete.R;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ViewHolder>{

    private MyList<PhotoURL> photoURLMyList;
    private Context context;
    Handler handler = new Handler(Looper.getMainLooper());

    public ImageAdapter(Context context, MyList<PhotoURL> _photoURLMyList) {
        this.context = context;
        this.photoURLMyList = _photoURLMyList;
    }

    @Override
    public ImageAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_layout, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int i) {

      //  Picasso.with(context).load(photoURLMyList.get(i).getPhotoURL()).resize(640, 640).into(viewHolder.img_android);

        viewHolder.img_android.setImageBitmap(null);
        viewHolder.img_android.setTag(photoURLMyList.get(i).getPhotoURL());
        new Thread(new GetBitmapTask(viewHolder.img_android,photoURLMyList.get(i).getPhotoURL(), new GetBitmapTask.Callback() {
            @Override
            public void onFinish(final ImageView image,final Bitmap bitmap,String uri)
            {
                if(image.getTag().toString().equals(uri))
                    handler.post( new Runnable() {
                        @Override
                        public void run() {

                            image.setImageBitmap(bitmap);
                        }
                    });
            }

            @Override
            public void onError(Throwable t) {

            }
        })).start();

    }

    @Override
    public int getItemCount() {
        return photoURLMyList.listSize();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView img_android;
        public ViewHolder(View view) {
            super(view);
            img_android = (ImageView)view.findViewById(R.id.img_android);

        }
    }

    public MyList<PhotoURL> getPhotoURLMyList() {
        return photoURLMyList;
    }


}