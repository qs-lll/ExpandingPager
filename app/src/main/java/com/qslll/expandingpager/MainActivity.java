package com.qslll.expandingpager;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.transition.Explode;

import com.qslll.expandingpager.adapter.TravelViewPagerAdapter;
import com.qslll.expandingpager.model.Travel;
import com.qslll.library.ExpandingViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.viewPager) ViewPager viewPager;
    ExpandingViewPager expandingViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setupWindowAnimations();

        TravelViewPagerAdapter adapter = new TravelViewPagerAdapter(getSupportFragmentManager());
        adapter.addAll(generateTravelList());
        viewPager.setAdapter(adapter);

        expandingViewPager = new ExpandingViewPager(viewPager);
        expandingViewPager.setupViewPager();
    }

    @Override
    public void onBackPressed() {
        if(!expandingViewPager.onBackPressed()){
            super.onBackPressed();
        }
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void setupWindowAnimations() {
        Explode slideTransition = new Explode();
        getWindow().setReenterTransition(slideTransition);
        getWindow().setExitTransition(slideTransition);
    }

    private List<Travel> generateTravelList(){
        List<Travel> travels = new ArrayList<>();
        for(int i=0;i<5;++i){
            travels.add(new Travel("Seychelles", R.drawable.seychelles));
            travels.add(new Travel("New York", R.drawable.newyork));
        }
        return travels;
    }
}
