package com.bpapps.jobimclone.fragments.myjobsfragments.searchparameters;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.bpapps.jobimclone.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchParametersLocationFragment extends Fragment {
    public static SearchParametersLocationFragment getInstance() {
        return new SearchParametersLocationFragment();
    }

    public SearchParametersLocationFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_serach_parameters_location, container, false);
    }
}
