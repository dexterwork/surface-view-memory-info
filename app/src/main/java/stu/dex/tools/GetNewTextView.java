package stu.dex.tools;

import android.widget.LinearLayout;
import android.widget.TextView;

import stu.dex.memorytest.MainActivity;
import stu.dex.memorytest.R;
import stu.dex.settings.Setting;

/**
 * Created by dexter on 2015/3/30.
 */
public class GetNewTextView {


    MainActivity activity;

    public GetNewTextView(MainActivity activity) {
        this.activity = activity;
    }

    public TextView getNewTextView(String msg) {
        TextView tv = new TextView(activity);
        tv.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        tv.setText(msg);
        tv.setTextSize(Setting.TEXT_SIZE);
        tv.setTextColor(activity.getResources().getColor(R.color.light_green));
        return tv;
    }



}
