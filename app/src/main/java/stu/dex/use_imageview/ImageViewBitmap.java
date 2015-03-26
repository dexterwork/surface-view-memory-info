package stu.dex.use_imageview;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.io.IOException;
import java.io.InputStream;

import stu.dex.memorytest.ImgType;
import stu.dex.memorytest.MainActivity;
import stu.dex.tools.MLog;
import stu.dex.memory.MemoryInfo;
import stu.dex.tools.ResourceForTesting;
import stu.dex.tools.ScreenSize;

/**
 * Created by dexter on 2015/3/26.
 */
public class ImageViewBitmap {
    MainActivity activity;
    MemoryInfo memoryInfo;

    public ImageViewBitmap(MainActivity activity) {
        this.activity = activity;
        init();
    }

    private void init() {
        memoryInfo = new MemoryInfo(activity);
    }

    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);

    public ImageView getNewImageView(Bitmap bitmap) {
        ImageView iv = new ImageView(activity);
        iv.setLayoutParams(params);
        iv.setImageBitmap(bitmap);
        return iv;
    }

    private Bitmap pickImage() {
        Bitmap bitmap = null;
        try {
            InputStream is = activity.getAssets().open(ResourceForTesting.getImageFileName(ImgType.Big));
            //is = 圖片實體大小，沒有更多

            if (memoryInfo.smallMemory(10))//記憶體空間小於 10MB
                return null;
            MLog.i(this, "FREE:(before decodeStream) " + memoryInfo.getFreeMemoryOfMbWithTotal() + " MB.");
            //TODO test
//            bitmap = getBitmap1(is);
            bitmap = getBitmap2(is);//二個結果一樣
            bitmap = resize(bitmap);
            MLog.i(this, "FREE:(after decodeStream) " + memoryInfo.getFreeMemoryOfMbWithTotal() + " MB.");
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bitmap;
    }

    private Bitmap getBitmap2(InputStream is) {
        byte[] b = new byte[0];
        try {
            b = new byte[is.available()];
            is.read(b);
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        MLog.i(this, "byte size: " + b.length);
        return BitmapFactory.decodeByteArray(b, 0, b.length);//吃記憶體的地方
    }

    private Bitmap resize(Bitmap bitmap) {
        ScreenSize screenSize = new ScreenSize(activity);
        //將 bitmap 的寬設為與螢幕一樣，如果比螢幕大的話
        int screenWidth = screenSize.getScreenWidth();
        int w = bitmap.getWidth();
        if (w <= screenWidth) return bitmap;
        Matrix matrix = new Matrix();
        float scale = (float) screenWidth / w;
        matrix.postScale(scale, scale);
        return Bitmap.createBitmap(bitmap, 0, 0, w, bitmap.getHeight(), matrix, true);
    }

    private Bitmap getBitmap1(InputStream is) {
        return BitmapFactory.decodeStream(is);//吃記憶體的地方
    }
}
