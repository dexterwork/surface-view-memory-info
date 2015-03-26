package stu.dex.tools;

import java.util.Random;

import stu.dex.memorytest.ImgType;
import stu.dex.memorytest.R;

/**
 * Created by dexter on 2015/3/26.
 */
public class ResourceForTesting {

    //images for testing.
    private static String[] imgsString = new String[]{"country.png", "dd.png", "nerse2.jpg", "trumpet1.jpg"};//[0]為小張圖，[1]為大張圖
    private static int[] imgsInt = new int[]{R.drawable.country, R.drawable.dd, R.drawable.nerse2, R.drawable.trumpet1};

    /**
     * return resources image assets file name.
     * @param type
     * @return
     */
    public static String getImageFileName(ImgType type) {
        switch (type) {
            case Ran:
                return imgsString[new Random().nextInt(imgsString.length)];
            case Small:
                return imgsString[0];
            case Big:
                return imgsString[1];
            case Nerse:
                return imgsString[2];
            case Trumpet:
                return imgsString[3];
        }
        return null;
    }

    /**
     * return resources image drawable id.
     * @param type
     * @return
     */
    public static int getImageResource(ImgType type) {
        switch (type) {
            case Ran:
                return imgsInt[new Random().nextInt(imgsInt.length)];
            case Small:
                return imgsInt[0];
            case Big:
                return imgsInt[1];
            case Nerse:
                return imgsInt[2];
            case Trumpet:
                return imgsInt[3];
        }
        return 0;
    }

}
