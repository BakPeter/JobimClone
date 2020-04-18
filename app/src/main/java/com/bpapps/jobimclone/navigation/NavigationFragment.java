package com.bpapps.jobimclone.navigation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.fragment.app.Fragment;

import com.bpapps.jobimclone.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class NavigationFragment extends Fragment implements View.OnClickListener {

    private IOnNavClickedListener mOnNavClickedListener;

    public interface IOnNavClickedListener {
        void onClick(LinearLayoutCompat navClicked);
    }

    public NavigationFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_navigation, container, false);

        initView(view);

        return view;
    }

    private void initView(View view) {
        LinearLayoutCompat navDetails = view.findViewById(R.id.nav_my_details);
        navDetails.setOnClickListener(this);

        LinearLayoutCompat navNotification = view.findViewById(R.id.nav_notification);
        navNotification.setOnClickListener(this);

        LinearLayoutCompat navMyJobs = view.findViewById(R.id.nav_my_jobs);
        navMyJobs.setOnClickListener(this);

        LinearLayoutCompat navFindJobs = view.findViewById(R.id.nav_find_jobs);
        navFindJobs.setOnClickListener(this);

        LinearLayoutCompat navSmartAgent = view.findViewById(R.id.nav_smart_agent);
        navSmartAgent.setOnClickListener(this);

        LinearLayoutCompat navInformation = view.findViewById(R.id.nav_information);
        navInformation.setOnClickListener(this);

        LinearLayoutCompat navAddNewJob = view.findViewById(R.id.nav_add_new_job);
        navAddNewJob.setOnClickListener(this);

        LinearLayoutCompat navOpenWebSite = view.findViewById(R.id.nav_open_web_site);
        navOpenWebSite.setOnClickListener(this);
    }

    public void setOnNavClickedListener(@NonNull IOnNavClickedListener onNavClickedListener) {
        mOnNavClickedListener = onNavClickedListener;
    }

    @Override
    public void onClick(View v) {
//        v.setBackgroundColor(getResources().getColor(R.color.nav_on_click_background_color));
        if (mOnNavClickedListener != null) {
            mOnNavClickedListener.onClick((LinearLayoutCompat) v);
        }

    }
}
