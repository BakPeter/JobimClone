package com.bpapps.jobimclone.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.bpapps.jobimclone.R;


public class MyJobsFragment extends Fragment {
    public static final String FRAGMENT_TAG = "FRAGMENT_TAG.MyJobsFragment";
    public static final String STACK_TAG = "STACK_TAG.MyJobsFragment";

    private Menu mMenu;
    private ConstraintLayout mSearch_parameters_jobs_action;
    private FrameLayout mFragmentSearchResultShowerContainer;

    private FragmentManager mFM;

    public static MyJobsFragment getInstance() {
        return new MyJobsFragment();
    }

    public MyJobsFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_jobs, container, false);

        AppCompatActivity activity = (AppCompatActivity) requireActivity();
        setUpToolBar(activity);


        mSearch_parameters_jobs_action = view.findViewById(R.id.search_parameters_jobs_action);
        mSearch_parameters_jobs_action.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mFM.beginTransaction()
                        .add(R.id.navigation_view_fragment, SearchParametersDefinerFragment.getInstance(), SearchParametersDefinerFragment.FRAGMENT_TAG)
                        .addToBackStack(SearchParametersDefinerFragment.STACK_TAG)
                        .commit();
            }
        });

        mFragmentSearchResultShowerContainer = view.findViewById(R.id.search_result_nav_fragment_container);

        mFM = requireActivity().getSupportFragmentManager();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        requireActivity().getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);

        mFM.beginTransaction()
                .add(R.id.search_result_nav_fragment_container, ShowSearchInListFragment.getInstance(), ShowSearchInListFragment.FRAGMENT_TAG)
                .addToBackStack(ShowSearchInListFragment.STACK_TAG)
                .commit();

    }

    private void setUpToolBar(AppCompatActivity activity) {
        activity.getSupportActionBar().show();

        Toolbar toolbar = activity.findViewById(R.id.tool_bar);
        ((TextView) toolbar.findViewById(R.id.text_view_tool_bar_title)).setText(R.string.nav_my_jobs_text);

        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        inflater.inflate(R.menu.jobs_screen_menu, menu);
        mMenu = menu;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        item.setVisible(false);
        switch (item.getItemId()) {
            case R.id.menu_item_show_in_list:
                mMenu.findItem(R.id.menu_item_show_on_map).setVisible(true);
                mFM.popBackStack();
                break;
            case R.id.menu_item_show_on_map:
                mMenu.findItem(R.id.menu_item_show_in_list).setVisible(true);

                mFM.beginTransaction()
                        .add(R.id.search_result_nav_fragment_container, ShowSearchOnMapFragment.getInstance(), ShowSearchOnMapFragment.FRAGMENT_TAG)
                        .addToBackStack(ShowSearchOnMapFragment.STACK_TAG)
                        .commit();
                break;
        }

        return true;
    }
}
