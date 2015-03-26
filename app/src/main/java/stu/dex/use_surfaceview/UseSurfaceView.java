package stu.dex.use_surfaceview;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import stu.dex.memorytest.MainActivity;
import stu.dex.memorytest.R;
import stu.dex.tools.MLog;

/**
 * Created by dexter on 2015/3/26.
 */
public class UseSurfaceView {
    MainActivity activity;
    LinearLayout linearLayout;

    public UseSurfaceView(MainActivity activity, LinearLayout linearLayout) {
        this.activity = activity;
        this.linearLayout=linearLayout;
    }

    public Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            MLog.i(this, "handler");
            linearLayout.addView(getSurfaceView(msg.getData().getInt("img")));
        }
    };

    public void addSurfaceView(int drawable){
        Bundle bundle=new Bundle();
        bundle.putInt("img",drawable);
        Message message=new Message();
        message.setData(bundle);
        handler.sendMessage(message);
    }

    public FrameLayout getSurfaceView(int drawable) {
        if(drawable!=0) MLog.i(this,"drawable!!");
        FrameLayout view = (FrameLayout) LayoutInflater.from(activity).inflate(R.layout.msurfaceview, null, false);
        Bitmap bitmap = BitmapFactory.decodeResource(activity.getResources(), drawable);
        int w = bitmap.getWidth();
        int h = bitmap.getHeight();
        int[] pixel = new int[w * h];
        bitmap.getPixels(pixel, 0, w, 0, 0, w, h);

        MSurfaceView sur = (MSurfaceView) view.findViewById(R.id.view);
        sur.init(pixel, w, h);
        return view;
    }
}
