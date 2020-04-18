package com.bpapps.jobimclone.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.bpapps.jobimclone.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyJobsFragment extends Fragment {
    private Menu mMenu;

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
        setUpDrawerLayout(activity);
        setUpToolBar(activity);

        return view;
    }

    private void setUpToolBar(AppCompatActivity activity) {
        activity.getSupportActionBar().show();

        Toolbar toolbar = activity.findViewById(R.id.tool_bar);
        ((TextView) toolbar.findViewById(R.id.text_view_tool_bar_title)).setText(R.string.tern_of_use_tool_bar_title);

        setHasOptionsMenu(true);
    }

    private void setUpDrawerLayout(AppCompatActivity activity) {
        DrawerLayout drawerLayout = activity.findViewById(R.id.drawer_layout);
        drawerLayout.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
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
                Toast.makeText(requireContext(), "menu_item_show_in_list", Toast.LENGTH_SHORT).show();
                mMenu.findItem(R.id.menu_item_show_on_map).setVisible(true);
                break;
            case R.id.menu_item_show_on_map:
                Toast.makeText(requireContext(), "menu_item_show_on_map", Toast.LENGTH_SHORT).show();
                mMenu.findItem(R.id.menu_item_show_in_list).setVisible(true);
                break;
        }

        return true;
    }
}
