package com.qslll.library;

import android.app.Activity;
import android.support.v4.view.ViewPager;
import android.view.ViewGroup;

/**
 * Created by Qs on 16/6/20.
 */
public class Util {
    public static void setupPager(ViewPager v) {
        ViewGroup.LayoutParams layoutParams = v.getLayoutParams();
        ((ViewGroup) v.getParent()).setClipChildren(false);
        v.setClipChildren(false);
        layoutParams.width = ((Activity)v.getContext()).getWindowManager().getDefaultDisplay().getWidth() / 7 * 5;
        layoutParams.height = (int) ((layoutParams.width / 0.75));

    }
}
