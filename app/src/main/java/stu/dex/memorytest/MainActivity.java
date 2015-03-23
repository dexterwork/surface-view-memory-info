package stu.dex.memorytest;

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

        try {
            InputStream is = getAssets().open("dd.png");
            is.read(new byte[is.available()]);
            BitmapFactory.decodeStream(is);
            MLog.i(this, "size: " + is.available());
            is.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        pickMemory();


    }


    private void pickMemory(){
        double free = Runtime.getRuntime().freeMemory();
        MLog.i(this, "free memory: " + free + " bytes.");


        double total = Runtime.getRuntime().totalMemory();
        MLog.i(this, "total memory: " + (total / 1024) + " KB.");

        double max = Runtime.getRuntime().maxMemory();
        MLog.i(this, "max memory: " + (max / 1024 / 1024) + "MB.");
    }

    @Override
    public void onBackPressed() {
        System.exit(0);
    }

}
