package stu.dex.use_surfaceview;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.Toast;

import java.util.ArrayList;

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
    MemoryInfo memoryInfo;
    FrameLayout layout;

    ArrayList<FrameLayout> fuckingLayout;

    public UseSurfaceView(MainActivity activity, FrameLayout relativeLayout) {
        this.activity = activity;
        this.layout = relativeLayout;
        memoryInfo = new MemoryInfo(activity);
        fuckingLayout = new ArrayList<FrameLayout>();
    }

    /**
     * 插入圖片，成功時返回 true
     *
     * @param drawable
     * @return
     */
    public boolean addSurfaceView(int drawable) {
        FrameLayout fl = getSurfaceView(drawable);
        if (fl != null) {
            layout.addView(fl, 0, new ViewGroup.LayoutParams(Pub.width, Pub.height));//這裡必需要設置寬高，否則 surface view 不會顯示
            fuckingLayout.add(0, fl);
            moveChildToFront(fl);
            fl.setOnTouchListener(new TouchListener(fl));
            return true;
        }
        return false;
    }

    private FrameLayout getSurfaceView(int drawable) {
        if (drawable == 0) {
            Toast.makeText(activity, "未獲取圖片資源", Toast.LENGTH_SHORT).show();
            return null;
        }
        System.gc();

        Bitmap bitmap = BitmapFactory.decodeResource(activity.getResources(), drawable);
        BitmapTool bitmapTool = new BitmapTool(activity);
        bitmap = bitmapTool.reScaleAsScreenWidth(bitmap);//if the bitmap width bigger than screen width to set scale same screen width.

        Pub.width = bitmap.getWidth();
        Pub.height = bitmap.getHeight();
        Pub.colors = memoryInfo.checkMemoryForNewIntArray(Pub.width, Pub.height, "[記憶體不足] 已使用:" + memoryInfo.getTotalMemoryOfMB() + " MB.");
        if (Pub.colors == null) return null;
        bitmap.getPixels(Pub.colors, 0, Pub.width, 0, 0, Pub.width, Pub.height);
        bitmap.recycle();
        System.gc();

        //依需要塞入資料包
        FrameLayout view = (FrameLayout) activity.getLayoutInflater().inflate(R.layout.msurfaceview, null);
        MSurfaceView mv = (MSurfaceView) view.findViewById(R.id.view);
        MSurfaceView.MBundle bundle = mv.getBundle();
        bundle.setIndex(activity.index + 1);
        return view;
    }

    private class TouchListener implements View.OnTouchListener {
        private FrameLayout frameLayout;
        private MSurfaceView.MBundle mBundle;
        private float touchX, touchY;

        private TouchListener(FrameLayout frameLayout) {
            this.frameLayout = frameLayout;
            mBundle = ((MSurfaceView) frameLayout.getChildAt(0)).getBundle();
        }

        @Override
        public boolean onTouch(View view, MotionEvent event) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
//                    MLog.i(this, "touch index:" + mBundle.getIndex());

                    touchX = event.getX();
                    touchY = event.getY();
                    return true;
                case MotionEvent.ACTION_MOVE:
                    //加入可控制圖片
                    frameLayout.setX(frameLayout.getX() + event.getX() - touchX);
                    frameLayout.setY(frameLayout.getY() + event.getY() - touchY);
                    return true;
                case MotionEvent.ACTION_UP:
//                    moveChildToFront(frameLayout);
                    return true;
            }
            return false;
        }
    }

    private void moveChildToFront(final FrameLayout frameLayout) {

    }


}
