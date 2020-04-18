package com.bpapps.jobimclone.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bpapps.jobimclone.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyDetailsFragment extends Fragment {

    public MyDetailsFragment() {
        // Required empty public constructor
    }

    public static Fragment getInstance() {
        return new MyDetailsFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my_details, container, false);
    }
}
