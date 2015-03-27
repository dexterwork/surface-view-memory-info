package stu.dex.memorytest;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import stu.dex.memory.MemoryInfo;
import stu.dex.tools.MLog;
import stu.dex.tools.ResourceForTesting;
import stu.dex.use_surfaceview.UseSurfaceView;

/**
 * 為模擬加入大圖的記憶體監看
 */
public class MainActivity extends ActionBarActivity {

    //TODO 在 manifests 文件中加入 android:largeHeap="true"

    LinearLayout linearLayout;
    MemoryInfo memoryInfo;
    UseSurfaceView useSurfaceView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        memoryInfo = new MemoryInfo(this);
        linearLayout = (LinearLayout) findViewById(R.id.imgs_layout);
        useSurfaceView = new UseSurfaceView(this, linearLayout);
    }


    int index;

    private TextView getNewTextView(String msg) {
        TextView tv = new TextView(this);
        tv.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
        tv.setText(msg);
        tv.setTextSize(12);
        tv.setTextColor(getResources().getColor(R.color.light_red));
        return tv;
    }


    @Override
    public void onBackPressed() {
        System.exit(0);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn:
                MLog.i(this, "use memory total:" + memoryInfo.getTotalMemoryOfMB() + " MB");
                String msg = null;
                System.gc();
                //插圖
                if (useSurfaceView.addSurfaceView(ResourceForTesting.getImageResource(ImgType.Ran))) {
                    msg = "image index: " + String.valueOf(++index) + "\n[free memory]: " + memoryInfo.getFreeMemoryOfMbWithTotal() + " MB.";
                } else {
                    msg = "[NOT IMAGE!!] index: " + String.valueOf(++index);
                }
                msg += "\n------------------------------------------";
                linearLayout.addView(getNewTextView(msg));//插文字
                System.gc();
                break;
        }
    }
}
