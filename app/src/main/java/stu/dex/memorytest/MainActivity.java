package stu.dex.memorytest;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import java.io.IOException;
import java.io.InputStream;

import stu.dex.tools.MLog;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    protected void onResume() {
        super.onResume();

        pickMemory();
        pickImage();
        pickMemory();


    }

    private Bitmap pickImage() {
        Bitmap bitmap = null;
        try {
            MLog.i(this, "open dd.png.");
            InputStream is = getAssets().open("dd.png");
            int size = is.available();
            is.read(new byte[size]);
            MLog.i(this, "input stream size: " + size);
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
        MLog.i(this, "total memory: " + (total) + " bytes.");

        double max = Runtime.getRuntime().maxMemory();
        MLog.i(this, "max memory: " + (max / 1024 / 1024) + "MB.");
    }

    @Override
    public void onBackPressed() {
        System.exit(0);
    }

}
