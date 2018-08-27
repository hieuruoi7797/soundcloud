package com.example.admin.scloud.screen.home;

import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.example.admin.s_cloud.R;
import com.example.admin.scloud.screen.genre.GenreFragment;
import com.example.admin.scloud.screen.stream.StreamFragment;


public class ViewPagerAdapter extends FragmentPagerAdapter {

    public static final int TAB_SIZE = 3;

    @interface TabType {
        int STREAM_FRAGMENT = 0;
        int GENRE_FRAGMENT = 1;
        int LIBRARY_FRAGMENT = 2;

        String STREAM = "STREAM";
        String GENRE = "GENRE";
        String LIBRARY = "LIBRARY";

        int STREAM1 = R.drawable.ic_harddisk;
    }

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {

        switch (i) {
            case TabType.STREAM_FRAGMENT:
                return new StreamFragment();
            case TabType.GENRE_FRAGMENT:
                return new GenreFragment();
            case TabType.LIBRARY_FRAGMENT:
                return new LibraryFragment();
        }
        return null;
    }

    @Override
    public int getCount() {
        return TAB_SIZE;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {

        switch (position) {
            case TabType.STREAM_FRAGMENT:
                return "";
            case TabType.GENRE_FRAGMENT:
                return "";
            case TabType.LIBRARY_FRAGMENT:
                return "";
        }
        return getPageTitle(position);
    }
    public void setupTabs(TabLayout tabLayout, ViewPager viewPager){
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(TabType.STREAM_FRAGMENT)
                .setIcon(R.drawable.ic_lightning_electric_energy);
        tabLayout.getTabAt(TabType.GENRE_FRAGMENT)
                .setIcon(R.drawable.ic_genre);
        tabLayout.getTabAt(TabType.LIBRARY_FRAGMENT)
                .setIcon(R.drawable.ic_library);
    }

}
