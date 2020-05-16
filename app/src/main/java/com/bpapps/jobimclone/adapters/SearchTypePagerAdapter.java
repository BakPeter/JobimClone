package com.bpapps.jobimclone.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.bpapps.jobimclone.fragments.myjobsfragments.searchparameters.SearchParametersCompanyFragment;
import com.bpapps.jobimclone.fragments.myjobsfragments.searchparameters.SearchParametersJobsFragment;
import com.bpapps.jobimclone.fragments.myjobsfragments.searchparameters.SearchParametersLocationFragment;

public class SearchTypePagerAdapter extends FragmentStateAdapter {
    private static final int NUM_PAGES = 3;
    public SearchTypePagerAdapter(@NonNull Fragment fragment) {
        super(fragment);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return SearchParametersCompanyFragment.getInstance();
            case 1:
                return SearchParametersLocationFragment.getInstance();
            case 2:
                return SearchParametersJobsFragment.getInstance();
            default:
                return null;
        }
    }

    @Override
    public int getItemCount() {
        return NUM_PAGES;
    }
}
