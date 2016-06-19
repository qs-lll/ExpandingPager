package com.qslll.expandingpager.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qslll.expandingpager.R;

import java.io.Serializable;

/**
 * frontFragment
 *
 * A simple {@link Fragment} subclass.
 */
public class Fragment1 extends Fragment implements Serializable {


    public Fragment1() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fragment1, container, false);
    }

}
