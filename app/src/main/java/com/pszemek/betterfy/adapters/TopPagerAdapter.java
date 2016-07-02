package com.pszemek.betterfy.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.pszemek.betterfy.fragments.TopArtistsFragment;
import com.pszemek.betterfy.fragments.TopTracksFragment;

/**
 * Created by Ciemek on 02/07/16.
 */
public class TopPagerAdapter extends FragmentPagerAdapter {

    private final int FRAGMENTS_COUNT = 2;
    //1: topartists
    //2: toptracks


    public TopPagerAdapter(FragmentManager manager) {
        super(manager);
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new TopArtistsFragment();
        } else if (position == 1) {
            return new TopTracksFragment();
        }
        return null;
    }

    @Override
    public int getCount() {
        return FRAGMENTS_COUNT;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (position == 0)
            return "Top Artists";
        return "Top Tracks";
    }
}
