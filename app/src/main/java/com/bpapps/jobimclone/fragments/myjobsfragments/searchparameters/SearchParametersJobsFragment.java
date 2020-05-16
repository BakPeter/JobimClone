package com.bpapps.jobimclone.fragments.myjobsfragments.searchparameters;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bpapps.jobimclone.R;

public class SearchParametersJobsFragment extends Fragment {

    public static SearchParametersJobsFragment getInstance() {
        return new SearchParametersJobsFragment();
    }
    public SearchParametersJobsFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search_parameters_jobs, container, false);

        return view;
    }
}
