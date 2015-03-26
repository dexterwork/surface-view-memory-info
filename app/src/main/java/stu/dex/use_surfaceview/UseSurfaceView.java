package stu.dex.use_surfaceview;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import stu.dex.memory.MemoryInfo;
import stu.dex.memorytest.MainActivity;
import stu.dex.memorytest.R;
import stu.dex.tools.MLog;
import stu.dex.tools.Pub;
import stu.dex.tools.ScreenSize;

/**
 * Created by dexter on 2015/3/26.
 */
public class UseSurfaceView {
    MainActivity activity;
    LinearLayout linearLayout;
    MemoryInfo memoryInfo;
    ScreenSize screenSize;

    public UseSurfaceView(MainActivity activity, LinearLayout linearLayout) {
        this.activity = activity;
        this.linearLayout = linearLayout;
        memoryInfo = new MemoryInfo(activity);
        screenSize = new ScreenSize(activity);
    }


    public void addSurfaceView(int drawable) {
//        Bundle bundle = new Bundle();
//        bundle.putInt("img", drawable);
//        Message message = new Message();
//        message.setData(bundle);
//        handler.sendMessage(message);

        View fl = getSurfaceView(drawable);
        if (fl != null) {
            fl.setVisibility(View.VISIBLE);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            fl.setLayoutParams(params);
            linearLayout.addView(fl, screenSize.getScreenWidth(), screenSize.getScreenHeight());
        }
    }

    public View getSurfaceView(int drawable) {
        if (drawable != 0) MLog.i(this, "drawable!!");

        Bitmap bitmap = BitmapFactory.decodeResource(activity.getResources(), drawable);
        Pub.width = bitmap.getWidth();
        Pub.height = bitmap.getHeight();
        Pub.colors = new int[Pub.width * Pub.height];
        bitmap.getPixels(Pub.colors, 0, Pub.width, 0, 0, Pub.width, Pub.height);


        View view = activity.getLayoutInflater().inflate(R.layout.msurfaceview, null);
//        MSurfaceView sur =(MSurfaceView) view.findViewById(R.id.view);
        return view;
    }
}
