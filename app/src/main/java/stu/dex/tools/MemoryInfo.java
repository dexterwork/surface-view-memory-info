package stu.dex.tools;

import android.app.ActivityManager;
import android.content.Context;

import java.math.BigDecimal;

import stu.dex.memorytest.MainActivity;

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
        MLog.i(this, "FREE MEMORY: " + (free / 1024 / 1024) + " MB.");

        double total = Runtime.getRuntime().totalMemory();
        MLog.i(this, "TOTAL MEMORY: " + (total / 1024 / 1024) + " MB.");

        double max = Runtime.getRuntime().maxMemory();
        MLog.i(this, "MAX MEMORY: " + (max / 1024 / 1024) + "MB.");

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
    public long getFreeMemoryInfoOfMB() {
        ActivityManager activityManager = (ActivityManager) activity.getSystemService(Context.ACTIVITY_SERVICE);
        ActivityManager.MemoryInfo mi = new ActivityManager.MemoryInfo();
        activityManager.getMemoryInfo(mi);
        return mi.availMem;
    }

    /**
     * 可用記憶體小於 freeMB 時返回 true
     *
     * @return
     */
    public boolean smallMemory(int freeMB) {
        return (getMaxMemoryOfMB() - getTotalMemoryOfMB() < freeMB);
    }

    public float getFreeMemoryOfMbWithTotal() {
        return new BigDecimal(getMaxMemoryOfMB() - getTotalMemoryOfMB()).setScale(2, BigDecimal.ROUND_DOWN).floatValue();
    }

    public static float converToMB(long bytes) {
        return new BigDecimal((float) bytes / 1024 / 1024).setScale(2, BigDecimal.ROUND_DOWN).floatValue();
    }
}
