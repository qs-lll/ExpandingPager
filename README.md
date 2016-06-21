# ExpandingPager
[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-ExpandingPager-green.svg?style=true)](https://android-arsenal.com/details/1/3747)

ExpandingPager is a card peek/pop controller

[![gif](img/preview.gif)]()

#Setup

Just extends `ExpandingViewPagerAdapter` in your Fragment Adapter and setup the ExpandingViewPager

```java
ViewPager viewPager;
ExpandingViewPager expandingViewPager;

@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    viewPager = (ViewPager) findViewById(...);
     
    viewPager.setAdapter( new CustomViewPagerAdapter(getSupportFragmentManager()) );
    expandingViewPager = new ExpandingViewPager(viewPager);
    expandingViewPager.setupViewPager();
}
```

##Adapter

Just extends `ExpandingViewPagerAdapter` in your Fragment Adapter, which returns ExpandingFragment`

```java
public class CustomViewPagerAdapter extends ExpandingViewPagerAdapter {

    @Override
    public Fragment getItem(int position) {
        return CustomExpandingFragment.newInstance();
    }

}
```

Your `ExpandingFragment` must returns a FragmentTop and a FragmentBottom 

```java
public class CustomExpandingFragment extends ExpandingFragment {

    @Override
    public Fragment getFragmentFront() {
        return CustomFragmentTop.newInstance();
    }

    @Override
    public Fragment getFragmentBottom() {
        return CustomFragmentBottom.newInstance();
    }
}

```

##Fragments

###Top

Create your top fragment implementing `ExpandingFragment.ChildTop`

```java
public class CustomFragmentTop extends Fragment implements ExpandingFragment.ChildTop {
    
    @Nullable ExpandingFragment expandingFragment;
    
    @Override
    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (expandingFragment != null) {
                    if (expandingFragment.isOpenend()) {
                        //start your activity 
                    } else {
                        expandingFragment.open();
                    }
                }
            }
        });
    }


    @Override
    public void onAttachedToExpanding(ExpandingFragment expandingFragment) {
        this.expandingFragment = expandingFragment;
    }

    @Override
    public void onDetachedToExpanding() {
        this.expandingFragment = null;
    }
}
```

##Bottom

Create your top fragment implementing `ExpandingFragment.ChildTop`

```java
public class CustomFragmentBottom extends Fragment implements ExpandingFragment.ChildBottom {
    
    @Nullable ExpandingFragment expandingFragment;

    @Override
    public void onAttachedToExpanding(ExpandingFragment expandingFragment) {
        this.expandingFragment = expandingFragment;
    }

    @Override
    public void onDetachedToExpanding() {
        this.expandingFragment = null;
    }
}
```

##BackPress


```java
@Override
public void onBackPressed() {
    if(!expandingViewPager.onBackPressed()){
        super.onBackPressed();
    }
}
```