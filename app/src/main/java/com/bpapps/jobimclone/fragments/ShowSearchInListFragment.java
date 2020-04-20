package com.bpapps.jobimclone.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.bpapps.jobimclone.MainActivity;
import com.bpapps.jobimclone.R;

public class ShowSearchInListFragment extends Fragment implements MainActivity.IOnBackPressed {

    public static final String FRAGMENT_TAG = "FRAGMENT_TAG." + ShowSearchInListFragment.class.getSimpleName();
    public static final String STACK_TAG = "STACK_TAG." + ShowSearchInListFragment.class.getSimpleName();

    public ShowSearchInListFragment() {
    }

    public static ShowSearchInListFragment getInstance() {
        return new ShowSearchInListFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_show_serach_in_list, container, false);

        return view;
    }


    @Override
    public boolean onBackPressed() {
        boolean stopApp = true;

        if(stopApp) {
            Log.d(FRAGMENT_TAG, "onBackPressed, true, continueApp return false" );
            return true;
        } else {
            Log.d(FRAGMENT_TAG, "onBackPressed, false, endApp return true" );
            return false;
        }
    }
}
