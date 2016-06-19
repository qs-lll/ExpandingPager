package com.qslll.library.fragments;


import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.qslll.library.R;

import java.io.Serializable;

/**
 * A simple {@link Fragment} subclass.
 */
public class QsContainFragment extends Fragment {
    Fragment fragment1, fragment2;
    private ViewGroup back;
    private ViewGroup front;
    private ViewGroup layout3;
    private View inflate;
    private ValueAnimator valueAnimator;
    private ObjectAnimator objectAnimator;

    private float startY = 0;
    private View.OnClickListener listener;

    //    public static Fragment getInstance(Fragment fragment1, Fragment fragment2) {
//        return new QsContainFragment(fragment1, fragment2);
//    }
    public QsContainFragment() {

    }

    public static QsContainFragment getInstance(Fragment front, Fragment back) {
        // Required empty public constructor

        QsContainFragment fragment = new QsContainFragment();
        Bundle args = new Bundle();
        args.putSerializable("front", (Serializable) front);
        args.putSerializable("back", (Serializable) back);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.fragment1 = (Fragment) getArguments().get("front");
        this.fragment2 = (Fragment) getArguments().get("back");
        inflate = View.inflate(getActivity(), R.layout.fragment_qs_contain, null);
        getChildFragmentManager().beginTransaction().add(R.id.front, fragment1).commit();
        getChildFragmentManager().beginTransaction().add(R.id.bottomLayout, fragment2).commit();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        back = (ViewGroup) inflate.findViewById(R.id.back);
        front = (ViewGroup) inflate.findViewById(R.id.front);
        layout3 = (ViewGroup) inflate.findViewById(R.id.bottomLayout);

        inflate.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                float my = 0;
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        startY = event.getY();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        my = event.getY();
                        break;
                    case MotionEvent.ACTION_UP:
                        if (event.getY() - startY > 0) {
                            doAnimationOut();
                            return true;
                        }
                        break;
                }
                return false;
            }
        });

        inflate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if (objectAnimator != null) {
//                    doAnimationOut();
//                } else {
//                    doAnimation();
//                }
                if (objectAnimator == null)
                    doAnimation();
                else
                    listener.onClick(v);
            }
        });
        return inflate;
    }

    private void doAnimation() {
        ViewGroup.LayoutParams layoutParams = layout3.getLayoutParams();
        layoutParams.height = (int) (front.getHeight() * 1.2 / 4 * 1.2);
        layout3.setLayoutParams(layoutParams);
        PropertyValuesHolder layout1Y = PropertyValuesHolder.ofFloat(View.TRANSLATION_Y, 0, -front.getHeight() / 4);
        PropertyValuesHolder scale1 = PropertyValuesHolder.ofFloat(View.SCALE_X, 1, 1);
        valueAnimator = ObjectAnimator.ofPropertyValuesHolder(front, layout1Y, scale1);
        PropertyValuesHolder layout2_scaleX = PropertyValuesHolder.ofFloat(View.SCALE_X, 1f, 1.2f);
        PropertyValuesHolder layout2_scaleY = PropertyValuesHolder.ofFloat(View.SCALE_Y, 1f, 1.2f);
        objectAnimator = ObjectAnimator.ofPropertyValuesHolder(back, layout2_scaleX, layout2_scaleY);
        back.setPivotY(0);
        objectAnimator.start();
        valueAnimator.start();
    }

    private void doAnimationOut() {
        if (objectAnimator != null) {
            valueAnimator.reverse();
            objectAnimator.reverse();
            objectAnimator = null;
            valueAnimator = null;
        }
    }

    /**
     * expanding close
     */
    public void close() {
        doAnimationOut();
    }

    /**
     * expanding is closed?
     *
     * @return
     */
    public boolean isClosed() {
        return objectAnimator == null;
    }

    /**
     * expanding out clickListener
     *
     * @param listener
     */
    public void setOnExpandingClickListener(View.OnClickListener listener) {
        this.listener = listener;
    }

}
