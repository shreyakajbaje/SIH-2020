package com.example.goaapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class PagerAdapter extends FragmentStatePagerAdapter {
    int mNoOfTabs;

    public PagerAdapter(FragmentManager fm, int NumberOfTabs){
        super(fm);
        this.mNoOfTabs = NumberOfTabs;
    }



    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                HomeTab homeTab = new HomeTab();
                return homeTab;
            case 1:
                TouristPlaces tp = new TouristPlaces();
                return tp;
            case 2:
                Hotels hotels = new Hotels();
                return hotels;
            case 3:
                Restaurants restaurants = new Restaurants();
                return restaurants;
            case 4:
                Markets markets = new Markets();
                return markets;
            case 5:
                Transportation transportation = new Transportation();
                return transportation;
            default:
                return null;
        }

    }

    @Override
    public int getCount() {
        return mNoOfTabs;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return "Home";
            case 1:
                return "Tourist Places";
            case 2:
                return "Hotels";
            case 3:
                return "Restaurants";
            case 4:
                return "Markets";
            case 5:
                return "Transportation";
        }
        return null;
    }

}
