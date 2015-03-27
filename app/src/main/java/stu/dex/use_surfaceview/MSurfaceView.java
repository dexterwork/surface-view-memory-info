package stu.dex.use_surfaceview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Toast;

/**
 * Created by dexter on 2015/3/24.
 */
public class MSurfaceView extends SurfaceView implements View.OnTouchListener {
    private MBundle mBundle;

    //從xml代入的要使用這個建構子
    public MSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        getHolder().addCallback(new MCallback(this));
        this.setOnTouchListener(this);
    }

    float touchX;

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Toast.makeText(getContext(), "INDEX " + mBundle.getIndex(), Toast.LENGTH_SHORT).show();
                touchX = event.getX();
                return true;
            case MotionEvent.ACTION_MOVE:
                this.setX(this.getX() + event.getX() - touchX);
                return true;
        }
        return false;
    }

    public MBundle getBundle() {
        if (mBundle == null) mBundle = new MBundle();
        return mBundle;
    }


    /**
     * Bundle for MSurfaceView 模擬自訂資料封包
     */
    public class MBundle {
        private int index;
        private String picName;

        public void setIndex(int index) {
            this.index = index;
        }

        public void setPicName(String picName) {
            this.picName = picName;
        }

        public int getIndex() {
            return index;
        }

        public String getPicName() {
            return picName;
        }

    }
}
