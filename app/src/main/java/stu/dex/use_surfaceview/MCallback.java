package stu.dex.use_surfaceview;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.SurfaceHolder;

import stu.dex.tools.MLog;
import stu.dex.tools.Pub;

/**
 * Created by dexter on 2015/3/26.
 */
public class MCallback implements SurfaceHolder.Callback {
    MSurfaceView mSurfaceView;

    public MCallback(MSurfaceView surfaceView) {
        this.mSurfaceView = surfaceView;
    }


    class MThread implements Runnable {
        @Override
        public void run() {
            MLog.w(this, "callback is run!");
            Canvas canvas = mSurfaceView.getHolder().lockCanvas(null);
            try {
                synchronized (mSurfaceView.getHolder()) {
                    canvas.drawBitmap(Pub.colors, 0, Pub.width, 0, 0, Pub.width, Pub.height, false, new Paint());
                }
            } finally {
                mSurfaceView.getHolder().unlockCanvasAndPost(canvas);
                Pub.colors = null;
                System.gc();
            }
        }
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        new Thread(new MThread()).start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }
}
