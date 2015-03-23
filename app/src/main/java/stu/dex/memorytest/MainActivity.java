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
import java.util.ArrayList;
import java.util.Random;

import stu.dex.tools.MLog;


public class MainActivity extends ActionBarActivity {
    ArrayList<Bitmap> imgList;
    LinearLayout linearLayout;
    private final int IMGS = 12;
    private String[] imgs = new String[]{"country.png", "dd.png"};

    enum ImgType {Ran, Small, Big}


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        linearLayout = (LinearLayout) findViewById(R.id.imgs_layout);
        imgList = new ArrayList<Bitmap>();
    }


    @Override
    protected void onResume() {
        super.onResume();
        pickMemory();

        for (int i = 0; i < IMGS; i++) {
            linearLayout.addView(getNewImageView(pickImage()));
            linearLayout.addView(getNewTextView(i));
        }


        pickMemory();


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
            InputStream is = getAssets().open(getRanImgFileName(ImgType.Big));
//            MLog.i(this, "input stream size: " + is.available());
            bitmap = BitmapFactory.decodeStream(is);
            is.close();
            return bitmap;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String getRanImgFileName(ImgType type) {
        switch (type) {

            case Ran:
                Random ran = new Random();
                return imgs[ran.nextInt(imgs.length)];
            case Small:
                return "country.png";
            case Big:
                return "dd.png";
        }
        return null;
    }


    private void pickMemory() {
        double free = Runtime.getRuntime().freeMemory();
        MLog.i(this, "free memory: " + free + " bytes.");


        double total = Runtime.getRuntime().totalMemory();
        MLog.i(this, "total memory: " + (total / 1024) + " KB.");
        MLog.w(this, "total memory: " + (total / 1024 / 1024) + " MB.");

        double max = Runtime.getRuntime().maxMemory();
        MLog.w(this, "max memory: " + (max / 1024 / 1024) + "MB.");
    }

    @Override
    public void onBackPressed() {
        System.exit(0);
    }

}
