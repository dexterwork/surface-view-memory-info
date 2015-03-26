package stu.dex.memorytest;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import stu.dex.memory.MemoryInfo;
import stu.dex.tools.MLog;
import stu.dex.tools.ResourceForTesting;
import stu.dex.use_surfaceview.UseSurfaceView;

/**
 * 為模擬加入大圖的記憶體監看
 */
public class MainActivity extends ActionBarActivity implements View.OnClickListener {

    //TODO 在 manifests 文件中加入 android:largeHeap="true"

    LinearLayout linearLayout;
//    private final int IMGS = 10;//設定要加入圖片的張數做測試用


    MemoryInfo memoryInfo;

    Button btn;

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
        btn = (Button) findViewById(R.id.btn);
        btn.setOnClickListener(this);
        useSurfaceView = new UseSurfaceView(this, linearLayout);
    }



    int index;

    private TextView getNewTextView() {
        TextView tv = new TextView(this);
        tv.setLayoutParams(params);
        tv.setText("image: " + String.valueOf(index++) + "\n[free memory]: " + (int) memoryInfo.getFreeMemoryOfMbWithTotal() + " MB.");
        tv.setTextColor(Color.rgb(200, 200, 200));
        return tv;
    }

    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);


    @Override
    public void onBackPressed() {
        System.exit(0);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn:
                MLog.i(this, "use memory total:" + memoryInfo.getTotalMemoryOfMB()+" MB");
                useSurfaceView.addSurfaceView(ResourceForTesting.getImageResource(ImgType.Trumpet));
                linearLayout.addView(getNewTextView());
                System.gc();
                break;
        }
    }
}
