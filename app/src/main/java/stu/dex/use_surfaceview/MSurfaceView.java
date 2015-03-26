package stu.dex.use_surfaceview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import stu.dex.memory.MemoryInfo;
import stu.dex.memorytest.MainActivity;
import stu.dex.tools.MLog;
import stu.dex.tools.Pub;

/**
 * Created by dexter on 2015/3/24.
 */
public class MSurfaceView extends SurfaceView implements SurfaceHolder.Callback {
    public SurfaceHolder holder;

    Canvas canvas;
    MemoryInfo memoryInfo;


    public MSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        holder = getHolder();
        holder.addCallback(this);
        memoryInfo = new MemoryInfo((MainActivity) context);
    }


    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);

        canvas.drawBitmap(Pub.colors, 0, Pub.width, 0, 0, Pub.width, Pub.height, false, new Paint());
        Pub.colors = null;
        System.gc();
    }


    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        MLog.i(this, "surfaceCreated");
        this.holder = holder;
        canvas = null;
        try {
            // 鎖定整個畫布，在內存要求比較高的情況下，建議參數不要為null
            canvas = holder.lockCanvas(null);
            synchronized (holder) {
                draw(canvas);
            }
        } finally {
            if (canvas != null) {
                //並釋放鎖
                holder.unlockCanvasAndPost(canvas);
            }
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }
}
