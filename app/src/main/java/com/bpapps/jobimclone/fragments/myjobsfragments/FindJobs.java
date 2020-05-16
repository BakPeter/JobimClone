package com.bpapps.jobimclone.fragments.myjobsfragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.bpapps.jobimclone.MainActivity;
import com.bpapps.jobimclone.R;
import com.bpapps.jobimclone.fragments.myjobsfragments.searchparameters.SearchParametersDefinerFragment;

abstract public class FindJobs extends Fragment
        implements MainActivity.IOnBackPressed {

    private static final String TAG = "TAG_" + FindJobs.class.getName();

    protected Menu mMenu;

    public FindJobs() {
        setHasOptionsMenu(true);
    }

    protected abstract void initAdditionalViewsViews(View rootView);

    protected abstract int getLayoutId();

    protected abstract void updateVisibilityForOptionMenus();


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutId(), container, false);

        view.findViewById(R.id.change_parameters)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.d(TAG, "change_parameters : onClick");

                        requireActivity().getSupportFragmentManager()
                                .beginTransaction()
                                .setCustomAnimations(R.anim.slide_in_down, R.anim.slide_out_down_fade_out, R.anim.slidw_in_right, R.anim.slide_out_left)
                                .replace(R.id.app_main_fragment_nav_container, SearchParametersDefinerFragment.getInstance(), SearchParametersDefinerFragment.FRAGMENT_TAG)
                                .addToBackStack(SearchParametersDefinerFragment.STACK_TAG)
                                .commit();
                    }
                });initAdditionalViewsViews(view);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        AppCompatActivity activity = (AppCompatActivity) requireActivity();

        setUpToolBar(activity);

        activity.getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
    }

    private void setUpToolBar(AppCompatActivity activity) {
        activity.getSupportActionBar().show();

        Toolbar toolbar = activity.findViewById(R.id.tool_bar);

        toolbar.findViewById(R.id.image_view_main_nav).setVisibility(View.VISIBLE);
        ((TextView) toolbar.findViewById(R.id.text_view_tool_bar_title)).setText(R.string.nav_my_jobs_text);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        inflater.inflate(R.menu.jobs_menu, menu);

        mMenu = menu;

        updateVisibilityForOptionMenus();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case (R.id.menu_item_show_in_list):
                Log.d(TAG, "onOptionsItemSelected : menu_item_show_on_map --> menu_item_show_in_list");
                requireActivity().getSupportFragmentManager().popBackStack();
                return true;
            case (R.id.menu_item_show_on_map):
                Log.d(TAG, "onOptionsItemSelected : menu_item_show_in_list --> menu_item_show_on_map");
                requireActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.app_main_fragment_nav_container, FindJobsOnMap.getInstance(), FindJobsOnMap.FRAGMENT_TAG)
                        .addToBackStack(FindJobsOnMap.STACK_TAG)
                        .commit();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}