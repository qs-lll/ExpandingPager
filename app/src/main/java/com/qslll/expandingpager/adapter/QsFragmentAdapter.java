package com.qslll.expandingpager.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.qslll.library.fragments.QsContainFragment;

import java.util.List;

/**
 * Created by Qs on 16/5/30.
 */
public class QsFragmentAdapter extends FragmentPagerAdapter {
    List<QsContainFragment> fragments;

    public QsFragmentAdapter(FragmentManager fm, List<QsContainFragment> fragments) {
        super(fm);
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

}
