package stu.dex.memorytest;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

import stu.dex.tools.MemoryInfo;

/**
 * 為模擬加入大圖的記憶體監看
 */
public class MainActivity extends ActionBarActivity {

    //TODO 在 manifests 文件中加入 android:largeHeap="true"

    LinearLayout linearLayout;
    private final int IMGS = 50;//設定要加入圖片的張數做測試用
    private String[] imgs = new String[]{"country.png", "dd.png"};//[0]為小張圖，[1]為大張圖

    enum ImgType {Ran, Small, Big}//測試時可選擇大圖小圖作佔用記憶體容量的差別

    MemoryInfo memoryInfo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        memoryInfo = new MemoryInfo(this);
        linearLayout = (LinearLayout) findViewById(R.id.imgs_layout);
    }


    @Override
    protected void onResume() {
        super.onResume();
//        memoryControler.pickMemory();

        for (int i = 0; i < IMGS; i++) {
            Bitmap b = pickImage();
            System.gc();
            if (b == null) return;
            linearLayout.addView(getNewImageView(b));
            linearLayout.addView(getNewTextView(i));
        }

//        memoryControler.pickMemory();



    }

    private ImageView getNewImageView(Bitmap bitmap) {
        ImageView iv = new ImageView(this);
        iv.setLayoutParams(params);
        iv.setImageBitmap(bitmap);
        System.gc();
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

            if (memoryInfo.smallMemory(10))//記憶體空間小於 10MB
                return null;

            bitmap = BitmapFactory.decodeStream(is);//吃記憶體的地方
            memoryInfo.pickMemory();
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bitmap;
    }

    private String getRanImgFileName(ImgType type) {
        switch (type) {

            case Ran:
                Random ran = new Random();
                return imgs[ran.nextInt(imgs.length)];
            case Small:
                return imgs[0];
            case Big:
                return imgs[1];
        }
        return null;
    }


    @Override
    public void onBackPressed() {
        System.exit(0);
    }

}
