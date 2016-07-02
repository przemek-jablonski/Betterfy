package com.pszemek.betterfy.activities;

import android.os.Bundle;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import com.pszemek.betterfy.R;
import com.pszemek.betterfy.adapters.TopPagerAdapter;
import com.pszemek.betterfy.fragments.TopArtistsFragment;

import butterknife.BindView;
import butterknife.ButterKnife;


public class TopActivity extends AppCompatActivity {


    private final String    TOPARTISTSFRAGMENTTAG = "TOPARTISTSTAG";
    private TopPagerAdapter pagerAdapter;


    @BindView(R.id.viewpager_top)
    ViewPager       viewPager;

    @BindView(R.id.viewpager_top_strip)
    PagerTabStrip   pagerStrip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top);
        ButterKnife.bind(this);

        pagerAdapter = new TopPagerAdapter(getSupportFragmentManager());

        viewPager.setAdapter(pagerAdapter);


//        if (savedInstanceState == null) {
//            getSupportFragmentManager().beginTransaction()
//                    .add(R.id.activity_top_container, new TopArtistsFragment(), TOPARTISTSFRAGMENTTAG)
//                    .commit();
//        }


    }



}
