package com.example.milos.vezba;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by Milos on 19-Jul-17.
 */

public class PageAdapter extends FragmentPagerAdapter {
    private String[] titles= new String[]{"Take Photo", "Shoot Video"};

    public PageAdapter(FragmentManager fm ){
        super(fm);

    }
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new FragmentClass1();
            case 1:
                return new FragmentClass2();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 2;
    }
    // Returns the page title for the top indicator
    @Override
    public CharSequence getPageTitle(int position) {
        return  titles[position];
    }
}
