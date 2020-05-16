package com.bpapps.jobimclone.fragments.myjobsfragments.searchparameters;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bpapps.jobimclone.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchParametersCompanyFragment extends Fragment {

    public static SearchParametersCompanyFragment getInstance() {
        return new SearchParametersCompanyFragment();

    }

    public SearchParametersCompanyFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search_parameters_company, container, false);
    }
}
