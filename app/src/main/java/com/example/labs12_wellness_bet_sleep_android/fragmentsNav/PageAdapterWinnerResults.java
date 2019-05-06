package com.example.labs12_wellness_bet_sleep_android.fragmentsNav;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class PageAdapterWinnerResults extends FragmentStatePagerAdapter {

    int mNoOfTabs;

    public PageAdapterWinnerResults(FragmentManager fm, int NumberOfTabs)
    {
        super(fm);
        this.mNoOfTabs = NumberOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch(position)
        {

            case 0:
                WinnerFragment winnerFragment = new WinnerFragment();
                return winnerFragment;
            case 1:
                ResultsFragment resultsFragment = new ResultsFragment();
                return  resultsFragment;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNoOfTabs;
    }
}