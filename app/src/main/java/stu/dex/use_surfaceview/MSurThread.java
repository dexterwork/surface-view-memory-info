package stu.dex.use_surfaceview;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import stu.dex.memorytest.MainActivity;
import stu.dex.memorytest.R;

/**
 * Created by dexter on 2015/3/25.
 */
public class MSurThread extends Thread {

    LinearLayout layout;
    MainActivity activity;

    public MSurThread(LinearLayout layout, MainActivity activity) {
        this.layout = layout;
        this.activity = activity;
    }

    @Override
    public void run() {
        layout.addView(getSurfaceView());
    }

    private FrameLayout getSurfaceView() {
        FrameLayout view = (FrameLayout) LayoutInflater.from(activity).inflate(R.layout.msurfaceview, null, false);


        Bitmap bitmap = BitmapFactory.decodeResource(activity.getResources(), R.drawable.trumpet1);
        int w = bitmap.getWidth();
        int h = bitmap.getHeight();
        int[] pixel = new int[w * h];
        bitmap.getPixels(pixel, 0, w, 0, 0, w, h);

        MSurfaceView sur = (MSurfaceView) view.findViewById(R.id.view);
        return view;
    }
}
