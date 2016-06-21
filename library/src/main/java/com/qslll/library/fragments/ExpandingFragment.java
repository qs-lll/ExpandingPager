package com.qslll.library.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qslll.library.R;

public abstract class ExpandingFragment extends Fragment {

    private static final float SCALE_OPENED = 1.2f;
    private static final int SCALE_CLOSED = 1;
    private static final int ELEVATION_OPENED = 40;

    Fragment fragmentFront;
    Fragment fragmentBottom;
    ChildTop childTop;
    ChildBottom childBottom;

    private CardView back;
    private CardView front;
    private CardView layout3;

    float defaultCardElevation;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.expanding_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        this.fragmentFront = getFragmentFront();
        this.fragmentBottom = getFragmentBottom();
        if(fragmentFront instanceof ChildTop){
            childTop = (ChildTop) fragmentFront;
        } else {
            throw new RuntimeException("FragmentFront must implements ExpandingFragment.ChildTop");
        }
        if(fragmentBottom instanceof ChildBottom){
            childBottom = (ChildBottom) fragmentBottom;
        } else {
            throw new RuntimeException("FragmentBottom must implements ExpandingFragment.ChildBottom");
        }

        if (fragmentFront != null && fragmentBottom != null) {
            getChildFragmentManager().beginTransaction()
                .replace(R.id.front, fragmentFront)
                .replace(R.id.bottomLayout, fragmentBottom)
                .commit();
        }

        if(childTop != null && childBottom != null) {
            childTop.onAttachedToExpanding(this);
            childBottom.onAttachedToExpanding(this);
        }

        back = (CardView) view.findViewById(R.id.back);
        front = (CardView) view.findViewById(R.id.front);
        layout3 = (CardView) view.findViewById(R.id.bottomLayout);

        defaultCardElevation = front.getCardElevation();
    }

    public abstract Fragment getFragmentFront();

    public abstract Fragment getFragmentBottom();

    public boolean isClosed() {
        return ViewCompat.getScaleX(back) == SCALE_CLOSED;
    }

    public boolean isOpenend() {
        return ViewCompat.getScaleX(back) == SCALE_OPENED;
    }

    public void toggle() {
        if (isClosed()) {
            open();
        } else {
            close();
        }
    }

    public void open() {
        ViewGroup.LayoutParams layoutParams = layout3.getLayoutParams();
        layoutParams.height = (int) (front.getHeight() * SCALE_OPENED / 4 * SCALE_OPENED);
        layout3.setLayoutParams(layoutParams);

        ViewCompat.animate(front)
            .translationY(-front.getHeight() / 4);

        ViewCompat.animate(back)
            .scaleX(SCALE_OPENED)
            .scaleY(SCALE_OPENED);

        ViewCompat.setPivotY(back, 0);

        front.setCardElevation(ELEVATION_OPENED);
    }

    public void close() {
        ViewCompat.animate(front)
            .translationY(0);
        ViewCompat.animate(back)
            .scaleY(SCALE_CLOSED)
            .scaleX(SCALE_CLOSED);
        front.setCardElevation(defaultCardElevation);
    }

    interface Child {
        void onAttachedToExpanding(ExpandingFragment expandingFragment);

        void onDetachedToExpanding();
    }

    public interface ChildTop extends ExpandingFragment.Child {
    }

    public interface ChildBottom extends ExpandingFragment.Child {
    }

}
