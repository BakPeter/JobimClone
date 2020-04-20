package com.bpapps.jobimclone.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bpapps.jobimclone.R;

public class SearchParametersDefinerFragment extends Fragment {

    public static final String FRAGMENT_TAG = "FRAGMENT_TAG.SearchParametersDefinerFragment";
    public static final String STACK_TAG = "STACK_TAG.SearchParametersDefinerFragment";

    public static SearchParametersDefinerFragment getInstance() {
        return new SearchParametersDefinerFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_serach_parameters_definer, container, false);

        return view;
    }
}