package stu.dex.tools;

import android.graphics.Bitmap;

import stu.dex.memorytest.MainActivity;

/**
 * Created by dexter on 2015/3/27.
 */
public class BitmapTool {
    MainActivity activity;

    public BitmapTool(MainActivity activity) {
        this.activity = activity;
    }

    /**
     * 縮至與螢幕同寬
     *
     * @param bitmap
     * @return
     */
    public Bitmap reScaleAsScreenWidth(Bitmap bitmap) {
        if (bitmap.getWidth() <= Pub.screenSize.getScreenWidth()) return bitmap;
        int height = (int) (((float) Pub.screenSize.getScreenWidth() / bitmap.getWidth()) * bitmap.getHeight());
        return Bitmap.createScaledBitmap(bitmap, Pub.screenSize.getScreenWidth(), height, false);
    }
}
