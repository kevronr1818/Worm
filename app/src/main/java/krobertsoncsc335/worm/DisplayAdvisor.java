package krobertsoncsc335.worm;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.DisplayMetrics;

import static android.R.attr.id;

/**
 * Created by Kevron on 2/27/2017.
 */
public class DisplayAdvisor {
    static int maxX;
    static int maxY;
    static float scaleX;
    static float scaleY;
    final static int IDEAL_WIDTH = 620;
    final static int IDEAL_HEIGHT = 1024;


    public static void setScreenDimmensions(DisplayMetrics metrics) {
        maxX = metrics.widthPixels;
        maxY = metrics.heightPixels;

        scaleX = (float) maxX/IDEAL_WIDTH;
        scaleY = (float) maxX/IDEAL_HEIGHT;

        scaleX = Math.min(scaleX, scaleY);
        scaleY = scaleX;

    }

    public static Bitmap loadBitmap(Resources resources, int id){
       BitmapFactory.Options options = new BitmapFactory.Options();
        options.inScaled = false;

        Bitmap bitmap = BitmapFactory.decodeResource(resources, id, options);
        return bitmap;
    }

    public static Bitmap loadScaledToIdeal(Resources resources, int idealWidth, int idealHeight, int id){
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inScaled = false;
        options.inPreferredConfig = Bitmap.Config.ARGB_8888;

        Bitmap bitmap = BitmapFactory.decodeResource(resources, id, options);
        bitmap = Bitmap.createScaledBitmap(bitmap, (int) (idealWidth * scaleX), (int) (idealHeight * scaleY), false);
        return bitmap;

    }
}
