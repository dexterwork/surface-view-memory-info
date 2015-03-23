package stu.dex.memorytest;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import stu.dex.tools.MLog;


public class MainActivity extends ActionBarActivity {
    ArrayList<Bitmap> imgList;
    int[] imgId=new int[]{R.id.img1,R.id.img2,R.id.img3,R.id.img4,R.id.img5};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }


    @Override
    protected void onResume() {
        super.onResume();
        imgList=new ArrayList<Bitmap>();
        pickMemory();

        for(int i:imgId){
            ImageView img=(ImageView)findViewById(i);
            Bitmap bitmap=pickImage();
            imgList.add(bitmap);
            img.setImageBitmap(bitmap);
        }

        pickMemory();


    }

    private Bitmap pickImage() {
        Bitmap bitmap = null;
        try {
            MLog.i(this, "open dd.png.");
            InputStream is = getAssets().open("dd.png");
            MLog.i(this, "input stream size: " + is.available());
            bitmap = BitmapFactory.decodeStream(is);
            is.close();
            return bitmap;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    private void pickMemory() {
        double free = Runtime.getRuntime().freeMemory();
        MLog.i(this, "free memory: " + free + " bytes.");


        double total = Runtime.getRuntime().totalMemory();
        MLog.i(this, "total memory: " + (total/1024) + " KB.");
        MLog.d(this, "total memory: " + (total/1024/1024) + " MB.");

        double max = Runtime.getRuntime().maxMemory();
        MLog.d(this, "max memory: " + (max / 1024 / 1024) + "MB.");
    }

    @Override
    public void onBackPressed() {
        System.exit(0);
    }

}
