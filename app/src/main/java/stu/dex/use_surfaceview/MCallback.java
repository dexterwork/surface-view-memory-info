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

    SurfaceHolder holder;

    public MCallback(MSurfaceView surfaceView) {
        this.holder = surfaceView.holder;
    }


    class MThread implements Runnable {
        @Override
        public void run() {
            MLog.w(this, "callback is run!");
            Canvas canvas = holder.lockCanvas(null);
            canvas.drawBitmap(Pub.colors, 0, Pub.width, 0, 0, Pub.width, Pub.height, false, new Paint());
            holder.unlockCanvasAndPost(canvas);
        }
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        MLog.i(this,"surfaceCreated");
        new Thread(new MThread()).start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }
}
