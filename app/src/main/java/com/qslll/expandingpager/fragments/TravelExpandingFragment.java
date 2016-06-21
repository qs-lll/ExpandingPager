package com.qslll.expandingpager.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.qslll.expandingpager.R;
import com.qslll.expandingpager.model.Travel;
import com.qslll.library.fragments.ExpandingFragment;

/**
 * Created by florentchampigny on 21/06/2016.
 */
public class TravelExpandingFragment extends ExpandingFragment {

    static final String ARG_TRAVEL = "ARG_TRAVEL";
    Travel travel;

    public static TravelExpandingFragment newInstance(Travel travel){
        TravelExpandingFragment fragment = new TravelExpandingFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_TRAVEL, travel);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        if(args != null) {
            travel = args.getParcelable(ARG_TRAVEL);
        }
    }

    @Override
    public Fragment getFragmentFront() {
        return FragmentTop.newInstance(travel);
    }

    @Override
    public Fragment getFragmentBottom() {
        return FragmentBottom.newInstance();
    }
}
