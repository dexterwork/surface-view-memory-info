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

import stu.dex.tools.MemoryControler;


public class MainActivity extends ActionBarActivity {
    LinearLayout linearLayout;
    private final int IMGS = 50;
    private String[] imgs = new String[]{"country.png", "dd.png"};

    enum ImgType {Ran, Small, Big}

    MemoryControler memoryControler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        memoryControler = new MemoryControler(this);
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
        iv.setImageBitmap(bitmap);//TODO
        return iv;
    }

    private TextView getNewTextView(int index) {
        TextView tv = new TextView(this);
        tv.setLayoutParams(params);
        tv.setText("image: " + String.valueOf(index));
        tv.setTextColor(Color.rgb(200, 200, 200));
        return tv;

    }

    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

    private Bitmap pickImage() {
        //TODO
        Bitmap bitmap = null;

        try {
            //要先監看記憶體


            InputStream is = getAssets().open(getRanImgFileName(ImgType.Big));

            if (memoryControler.getMaxMemoryOfMB() - memoryControler.getTotalMemoryOfMB() < 10)
                return null;


            bitmap = BitmapFactory.decodeStream(is);
            memoryControler.pickMemory();
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
