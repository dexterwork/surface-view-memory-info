package stu.dex.memorytest;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import stu.dex.tools.MLog;

/**
 * Created by dexter on 2015/3/24.
 */
public class MSurfaceView extends SurfaceView implements SurfaceHolder.Callback {
    SurfaceHolder holder;
    Canvas canvas;

    private int[] color;
    private int width, height;

    public MSurfaceView(Context context) {
        super(context);
        holder = getHolder();
        holder.addCallback(this);
    }


    public void init(int[] color, int width, int height) {
        this.color = color;
        this.width = width;
        this.height = height;
    }


    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        MLog.i(this, "draw");

        if (color == null || width == 0 || height == 0)
            throw new RuntimeException("must to set color and width,height.");

        canvas.drawBitmap(color, 0, width, 0, 0, width, height, false, new Paint());
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
        MLog.i(this, "surfaceChanged");
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }


}
