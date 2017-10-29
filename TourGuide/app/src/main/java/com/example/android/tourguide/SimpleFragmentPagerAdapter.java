package com.example.android.tourguide;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by hp on 9/13/2016.
 */
public class SimpleFragmentPagerAdapter extends FragmentPagerAdapter {
    private Context mContext;
    private String tabTitles[] = new String[]{"Attractions", "casinos", "Museums", "Restaurants"};


    public SimpleFragmentPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        mContext = context;
    }


    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new AttractionsFragment();
        } else if (position == 1) {
            return new CasinosFragment();
        } else if (position == 2) {
            return new MuseumsFragment();
        } else {
            return new RestaurantsFragment();
        }
    }


    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        return tabTitles[position];
    }
}
