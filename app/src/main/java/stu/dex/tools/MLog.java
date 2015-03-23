package stu.dex.tools;

import android.util.Log;

/**
 * 
 * @author dexter
 *
 */
public class MLog {
	private final static boolean PUBLISH = false;

	public static void v(Object obj, String msg) {
		if (!PUBLISH)
			Log.v(getClassName(obj), msg);
	}

	public static void i(Object obj, String msg) {
		if (!PUBLISH)
			Log.i(getClassName(obj), msg);
	}

	public static void d(Object obj, String msg) {
		if (!PUBLISH)
			Log.d(getClassName(obj), msg);
	}

	public static void w(Object obj, String msg) {
		if (!PUBLISH)
			Log.w(getClassName(obj), msg);
	}

	public static void e(Object obj, String msg) {
		if (!PUBLISH)
			Log.e(getClassName(obj), msg);
	}

	private static String getClassName(Object obj) {
		return obj.getClass().getSimpleName();

	}

}
