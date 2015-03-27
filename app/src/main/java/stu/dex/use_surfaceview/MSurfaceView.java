package stu.dex.use_surfaceview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.SurfaceView;

/**
 * Created by dexter on 2015/3/24.
 */
public class MSurfaceView extends SurfaceView {

    //從xml代入的要使用這個建構子
    public MSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        getHolder().addCallback(new MCallback(this));
    }


}
