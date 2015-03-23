package stu.dex.tools;

import android.app.ActivityManager;
import android.content.Context;

import stu.dex.memorytest.MainActivity;

/**
 * Created by dexter on 2015/3/23.
 */
public class MemoryControler {
    MainActivity activity;

    public MemoryControler(MainActivity activity) {
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

    public double getFreeMemoryOfMB() {
        return Runtime.getRuntime().freeMemory() / 1024 / 1024;
    }

    public double getTotalMemoryOfMB() {
        return Runtime.getRuntime().totalMemory() / 1024 / 1024;
    }

    public double getMaxMemoryOfMB() {
        return Runtime.getRuntime().maxMemory() / 1024 / 1024;
    }

    public double getFreeMemory() {
        return Runtime.getRuntime().freeMemory();
    }

    public double getTotalMemory() {
        return Runtime.getRuntime().totalMemory();
    }

    public double getMaxMemory() {
        return Runtime.getRuntime().maxMemory();
    }

    public double getFreeMemoryInfo() {
        ActivityManager.MemoryInfo mi = new ActivityManager.MemoryInfo();
        ActivityManager activityManager = (ActivityManager) activity.getSystemService(Context.ACTIVITY_SERVICE);
        activityManager.getMemoryInfo(mi);
        return mi.availMem;
    }
}
