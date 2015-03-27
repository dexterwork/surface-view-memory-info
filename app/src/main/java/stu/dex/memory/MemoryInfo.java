package stu.dex.memory;

import android.app.ActivityManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.widget.Toast;

import java.math.BigDecimal;

import stu.dex.memorytest.MainActivity;
import stu.dex.settings.Setting;
import stu.dex.tools.MLog;

/**
 * Created by dexter on 2015/3/23.
 */
public class MemoryInfo {
    MainActivity activity;

    public MemoryInfo(MainActivity activity) {
        this.activity = activity;
    }

    /**
     * 將記憶體狀況 Log 出來
     */
    public void pickMemory() {
        double free = Runtime.getRuntime().freeMemory();
        MLog.i(this, "FREE MEMORY: " + converToMB(free) + " MB.");

        double total = Runtime.getRuntime().totalMemory();
        MLog.i(this, "TOTAL MEMORY: " + converToMB(total) + " MB.");

        double max = Runtime.getRuntime().maxMemory();
        MLog.i(this, "MAX MEMORY: " + converToMB(max) + "MB.");

    }

    /**
     * 回傳當前可用記憶體(在app限制可用範圍內)，單位 MB
     *
     * @return
     */
    public float getFreeMemoryOfMB() {
        return converToMB(Runtime.getRuntime().freeMemory());
    }

    /**
     * 回傳當前已佔用記憶體，單位 MB
     *
     * @return
     */
    public float getTotalMemoryOfMB() {
        return converToMB(Runtime.getRuntime().totalMemory());
    }

    /**
     * 回傳當前可用最大記憶體，單位 MB
     *
     * @return
     */
    public float getMaxMemoryOfMB() {
        return converToMB(Runtime.getRuntime().maxMemory());
    }

    /**
     * 回傳當前可用記憶體(在app限制可用範圍內)，單位 bytes
     *
     * @return
     */
    public long getFreeMemory() {
        return Runtime.getRuntime().freeMemory();
    }

    /**
     * 回傳當前已佔用記憶體，單位 bytes
     *
     * @return
     */
    public long getTotalMemory() {
        return Runtime.getRuntime().totalMemory();
    }

    /**
     * 回傳當前可用最大記憶體，單位 bytes
     *
     * @return
     */
    public long getMaxMemory() {
        return Runtime.getRuntime().maxMemory();
    }

    /**
     * 回傳裝置可用總記憶體，單位 MB
     *
     * @return
     */
    public float getFreeMemoryInfoOfMB() {
        ActivityManager activityManager = (ActivityManager) activity.getSystemService(Context.ACTIVITY_SERVICE);
        ActivityManager.MemoryInfo mi = new ActivityManager.MemoryInfo();
        activityManager.getMemoryInfo(mi);
        return converToMB(mi.availMem);
    }

    /**
     * 可用記憶體小於 freeMB 時返回 true
     *
     * @return
     */
    public boolean smallMemory(int freeMB) {
        return (getMaxMemoryOfMB() - getTotalMemoryOfMB() < freeMB);
    }

    /**
     * 計算最大可用記憶體減去總共已使用記憶體之剩餘可使用記憶體
     * @return
     */
    public float getFreeMemoryOfMbWithTotal() {
        return new BigDecimal(getMaxMemoryOfMB() - getTotalMemoryOfMB()).setScale(2, BigDecimal.ROUND_DOWN).floatValue();
    }

    /**
     * bytes to MB.
     * @param bytes
     * @return
     */
    public static float converToMB(double bytes) {
        return new BigDecimal((float) bytes / 1024 / 1024).setScale(2, BigDecimal.ROUND_DOWN).floatValue();
    }

    /**
     * 如果䛏憶體不足則傳回 true
     *
     * @return
     */
    public boolean isMemoryNotEnouph() {
        return getFreeMemoryOfMbWithTotal() < Setting.MIX_MEMORY_MB;
    }

    /**
     * 如果䛏憶體不足則傳回 true
     *
     * @return
     */
    public boolean isMemoryNotEnouph(Bitmap bitmap) {
        return getFreeMemoryOfMbWithTotal() <= bitmap.getByteCount();
    }

    /**
     * 檢測要 new int[] 時記憶體是否不足，以防 crash.
     *
     * @param width
     * @param height
     * @param outOfMemoryErrorMessage 若有字串，在記憶體不足時將會顯示其字串訊息
     * @return
     */
    public int[] checkMemoryForNewIntArray(int width, int height, String outOfMemoryErrorMessage) {
        try {
            System.gc();
            return new int[width * height];
        } catch (OutOfMemoryError e) {
            if (!TextUtils.isEmpty(outOfMemoryErrorMessage))
                Toast.makeText(activity, outOfMemoryErrorMessage, Toast.LENGTH_SHORT).show();
            return null;
        }
    }
}
