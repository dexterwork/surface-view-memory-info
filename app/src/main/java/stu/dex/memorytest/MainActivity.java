package stu.dex.memorytest;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Matrix;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

import stu.dex.tools.MLog;
import stu.dex.tools.MemoryInfo;
import stu.dex.tools.ScreenSize;

/**
 * 為模擬加入大圖的記憶體監看
 */
public class MainActivity extends ActionBarActivity {

    //TODO 在 manifests 文件中加入 android:largeHeap="true"

    LinearLayout linearLayout;
    private final int IMGS = 10;//設定要加入圖片的張數做測試用
    private String[] imgs = new String[]{"country.png", "dd.png", "nerse2.jpg", "trumpet1.jpg"};//[0]為小張圖，[1]為大張圖

    enum ImgType {Ran, Small, Big, Nerse}//測試時可選擇大圖小圖作佔用記憶體容量的差別

    MemoryInfo memoryInfo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

        //TODO 使用 canvas 來試
        //canvas.drawBitmap
    }

    private void init() {
        memoryInfo = new MemoryInfo(this);
        screenSize = new ScreenSize(this);
        linearLayout = (LinearLayout) findViewById(R.id.imgs_layout);
    }

    ScreenSize screenSize;


    @Override
    protected void onResume() {
        super.onResume();
        for (int i = 0; i < IMGS; i++) {
//            Bitmap b = pickImage();
            MSurfaceView sur = getSurfaceView();

            System.gc();
            if (sur == null) return;
//            linearLayout.addView(getNewImageView(b));
            linearLayout.addView(sur);
            linearLayout.addView(getNewTextView(i));
        }
    }

    int index;
    private MSurfaceView getSurfaceView() {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.trumpet1);
        int w = bitmap.getWidth();
        int h = bitmap.getHeight();
        MLog.i(this,"w="+w);
        MLog.i(this,"h="+h);
        MLog.i(this,"w*h="+(w*h));
//        if(index==0)return null;
        int[] pixel = new int[w * h];
        bitmap.getPixels(pixel, 0, w, 0, 0, w, h);
        MSurfaceView surfaceView = new MSurfaceView(this);
        surfaceView.init(pixel, w, h);
        surfaceView.setLayoutParams(params);
        return surfaceView;
    }


    private ImageView getNewImageView(Bitmap bitmap) {
        ImageView iv = new ImageView(this);
        iv.setLayoutParams(params);
        iv.setImageBitmap(bitmap);
        return iv;
    }

    private TextView getNewTextView(int index) {
        TextView tv = new TextView(this);
        tv.setLayoutParams(params);
        tv.setText("image: " + String.valueOf(index) + "\n[free memory]: " + (int) memoryInfo.getFreeMemoryOfMbWithTotal() + " MB.");
        tv.setTextColor(Color.rgb(200, 200, 200));
        return tv;
    }

    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

    private Bitmap pickImage() {
        Bitmap bitmap = null;
        try {
            InputStream is = getAssets().open(getRanImgFileName(ImgType.Big));
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

    private Bitmap getBitmap1(InputStream is) {
        return BitmapFactory.decodeStream(is);//吃記憶體的地方
    }

    private Bitmap resize(Bitmap bitmap) {
        //將 bitmap 的寬設為與螢幕一樣，如果比螢幕大的話
        int screenWidth = screenSize.getScreenWidth();
        int w = bitmap.getWidth();
        if (w <= screenWidth) return bitmap;
        Matrix matrix = new Matrix();
        float scale = (float) screenWidth / w;
        matrix.postScale(scale, scale);
        return Bitmap.createBitmap(bitmap, 0, 0, w, bitmap.getHeight(), matrix, true);
    }


    private String getRanImgFileName(ImgType type) {
        switch (type) {
            case Ran:
                return imgs[new Random().nextInt(imgs.length)];
            case Small:
                return imgs[0];
            case Big:
                return imgs[1];
            case Nerse:
                return imgs[2];
        }
        return null;
    }


    @Override
    public void onBackPressed() {
        System.exit(0);
    }

}
