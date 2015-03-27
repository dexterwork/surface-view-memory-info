package stu.dex.use_surfaceview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Toast;

import stu.dex.memorytest.MainActivity;

/**
 * Created by dexter on 2015/3/24.
 */
public class MSurfaceView extends SurfaceView implements View.OnClickListener {
    private int index;

    //從xml代入的要使用這個建構子
    public MSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        getHolder().addCallback(new MCallback(this));
        this.setOnClickListener(this);
        this.index = ((MainActivity) context).index + 1;
    }

    @Override
    public void onClick(View v) {
        Toast.makeText(getContext(), "I'm No." + index + " picture.", Toast.LENGTH_SHORT).show();
    }
}
