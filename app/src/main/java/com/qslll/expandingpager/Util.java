package com.qslll.expandingpager;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.media.ThumbnailUtils;
import android.util.Log;

/**
 * Created by Qs on 16/6/22.
 */
public class Util {
    /**
     * please do not try this!!!!!!
     * @param bitmap
     * @return
     */
    public static int getAVGColor(Bitmap bitmap) {
        bitmap = ThumbnailUtils.extractThumbnail(bitmap, 100, 200);
        int count = 0;
        int A = 0, R = 0, G = 0, B = 0;
        int pixelColor;
        int height = bitmap.getHeight();
        int width = bitmap.getWidth();


        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                pixelColor = bitmap.getPixel(x, y);
                A = A + Color.alpha(pixelColor);
                R = R + Color.red(pixelColor);
                G = G + Color.green(pixelColor);
                B = B + Color.blue(pixelColor);
                count++;
                Log.e("A:", A + "");
                Log.e("R:", R + "");
                Log.e("G:", G + "");
                Log.e("B:", B + "");


            }
        }
        return Color.argb(A / count, R / count, G / count, B / count);
    }
}
