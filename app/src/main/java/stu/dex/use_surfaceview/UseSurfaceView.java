package stu.dex.use_surfaceview;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import stu.dex.memory.MemoryInfo;
import stu.dex.memorytest.MainActivity;
import stu.dex.memorytest.R;
import stu.dex.tools.BitmapTool;
import stu.dex.tools.Pub;

/**
 * Created by dexter on 2015/3/26.
 */
public class UseSurfaceView {
    MainActivity activity;
    LinearLayout linearLayout;
    MemoryInfo memoryInfo;


    public UseSurfaceView(MainActivity activity, LinearLayout linearLayout) {
        this.activity = activity;
        this.linearLayout = linearLayout;
        memoryInfo = new MemoryInfo(activity);

    }


    public boolean addSurfaceView(int drawable) {
        View fl = getSurfaceView(drawable);
        if (fl != null) {
            fl.setVisibility(View.VISIBLE);
            linearLayout.addView(fl, Pub.width, Pub.height);
            return true;
        }
        return false;
    }

    public View getSurfaceView(int drawable) {
        if (drawable == 0) {
            Toast.makeText(activity, "未獲取圖片資源", Toast.LENGTH_SHORT).show();
            return null;
        }
        System.gc();
        Bitmap bitmap = BitmapFactory.decodeResource(activity.getResources(), drawable);
        BitmapTool bitmapTool = new BitmapTool(activity);
        bitmap = bitmapTool.reScaleAsScreenWidth(bitmap);//if the bitmap width bigger than screen width to set scale same screen width.


        //TODO 檢測剩下可用記憶體
        Pub.width = bitmap.getWidth();
        Pub.height = bitmap.getHeight();
        Pub.colors = memoryInfo.checkMemoryForNewIntArray(Pub.width, Pub.height, "[記憶體不足] 已使用:" + memoryInfo.getTotalMemoryOfMB() + " MB.");
        if (Pub.colors == null) return null;
        bitmap.getPixels(Pub.colors, 0, Pub.width, 0, 0, Pub.width, Pub.height);
        bitmap.recycle();
        System.gc();
        View view = activity.getLayoutInflater().inflate(R.layout.msurfaceview, null);
        MSurfaceView mv = (MSurfaceView) view.findViewById(R.id.view);
        MSurfaceView.MBundle bundle = mv.getBundle();
        bundle.setIndex(activity.index + 1);
        return view;
    }
}
