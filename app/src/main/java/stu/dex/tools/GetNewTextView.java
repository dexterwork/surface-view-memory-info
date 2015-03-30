package stu.dex.tools;

import android.widget.LinearLayout;
import android.widget.TextView;

import stu.dex.memorytest.MainActivity;
import stu.dex.memorytest.R;

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
        tv.setTextSize(12);
        tv.setTextColor(activity.getResources().getColor(R.color.light_red));
        return tv;
    }
}
