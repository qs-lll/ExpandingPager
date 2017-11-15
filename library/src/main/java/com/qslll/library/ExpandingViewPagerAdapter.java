package com.qslll.library;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.ViewGroup;

import java.lang.ref.WeakReference;

/**
 * Created by florentchampigny on 21/06/2016.
 */
public abstract class ExpandingViewPagerAdapter extends FragmentStatePagerAdapter {

    WeakReference<Fragment> currentFragmentReference;

    public ExpandingViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public Fragment getCurrentFragment() {
        if(currentFragmentReference != null){
            return currentFragmentReference.get();
        }
        return null;
    }

    @Override
    public void setPrimaryItem(ViewGroup container, int position, Object object) {
        super.setPrimaryItem(container, position, object);
        if (getCurrentFragment() != object) {
            currentFragmentReference = new WeakReference<>((Fragment) object);
        }
    }


}
