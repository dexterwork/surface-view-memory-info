package stu.dex.use_surfaceview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.SurfaceView;

/**
 * Created by dexter on 2015/3/24.
 */
public class MSurfaceView extends SurfaceView  {
    private MBundle mBundle;

    //從xml代入的要使用這個建構子
    public MSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        getHolder().addCallback(new MCallback(this));
    }


    public MBundle getBundle() {
        return mBundle == null ? mBundle = new MBundle() : mBundle;
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
