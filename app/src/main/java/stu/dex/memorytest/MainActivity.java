package stu.dex.memorytest;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import stu.dex.memory.MemoryInfo;
import stu.dex.tools.GetNewTextView;
import stu.dex.tools.Pub;
import stu.dex.tools.ResourceForTesting;
import stu.dex.tools.ScreenSize;
import stu.dex.use_surfaceview.UseSurfaceView;

/**
 * 模擬加入大圖的記憶體監看
 */
public class MainActivity extends ActionBarActivity {

    //TODO 在 manifests 文件中加入 android:largeHeap="true"

    LinearLayout textLayout;
    RelativeLayout layout;
    MemoryInfo memoryInfo;
    UseSurfaceView useSurfaceView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one_layout);
        init();
    }

    private void init() {
        memoryInfo = new MemoryInfo(this);
        layout = (RelativeLayout) findViewById(R.id.layout);
        textLayout = (LinearLayout) findViewById(R.id.text_layout);

        useSurfaceView = new UseSurfaceView(this);
        Pub.screenSize = new ScreenSize(this);
    }


    public int index;


    @Override
    public void onBackPressed() {
        System.exit(0);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn:
                String msg = null;
                System.gc();
                //插圖
                if (useSurfaceView.addSurfaceView(layout, ResourceForTesting.getImageResource(ImgType.Ran))) {
                    msg = "image index: " + String.valueOf(++index) + "\n[free memory]: " + memoryInfo.getFreeMemoryOfMbWithTotal() + " MB.";
                } else {
                    msg = "[NO IMAGE!!] index: " + String.valueOf(++index);
                }
                msg += "\n------------------------------------------";
                GetNewTextView getNewTextView = new GetNewTextView(this);
                textLayout.addView(getNewTextView.getNewTextView(msg));//插文字
                System.gc();
                break;
        }
    }
}
