package com.bpapps.jobimclone;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.bpapps.jobimclone.fragments.SplashScreenFragment;
import com.bpapps.jobimclone.fragments.addnewjob.AddNewJobFragment;
import com.bpapps.jobimclone.fragments.addnewjob.NewJobPicAttachmentFragment;
import com.bpapps.jobimclone.navigation.NavigationFragment;

public class MainActivity extends AppCompatActivity implements NavigationFragment.IOnNavClickedListener {
    public static final String PACKAGE_NAME = "com.bpapps.jobimclone";

    public static final String PREFERENCE_IS_REGISTERED_KEY = PACKAGE_NAME + ".PREFERENCE_IS_REGISTERED_KEY";
    public static final String PREFERENCES_FILE_NAME = PACKAGE_NAME + ".PREFERENCES_FILE_NAME";
    private static final String TAG = "TAG." + MainActivity.class.getName();

    private Toolbar mToolbar;
    private DrawerLayout mDrawerLayout;

    FragmentManager mFM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDrawerLayout = findViewById(R.id.drawer_layout);
        mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        NavigationFragment navigationFragment = (NavigationFragment) getSupportFragmentManager().findFragmentById(R.id.navigation_view_fragment);
        navigationFragment.setOnNavClickedListener(this);


        mToolbar = findViewById(R.id.tool_bar);
        Toolbar.LayoutParams layoutParams = new Toolbar.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        View toolBarView = getLayoutInflater().inflate(R.layout.tool_bar_layout, null);
        mToolbar.addView(toolBarView, layoutParams);
        setSupportActionBar(mToolbar);

        mToolbar.findViewById(R.id.image_view_main_nav).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDrawerLayout.openDrawer(GravityCompat.START);
                //   mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        mFM = getSupportFragmentManager();

        mFM.beginTransaction()
                .replace(R.id.app_main_fragment_nav_container, SplashScreenFragment.getInstance(), SplashScreenFragment.FRAGMENT_TAG)
                .addToBackStack(SplashScreenFragment.STACK_TAG)
                .commit();
    }


    @Override
    public void onClick(LinearLayoutCompat navClicked) {
//            Log.d(TAG, "Total fragments : " +fm.getBackStackEntryCount());
//            for(int entry = 0; entry < fm.getBackStackEntryCount(); entry++){
//                Log.d(TAG, "Found fragment: " + fm.getBackStackEntryAt(entry).getName());

        StringBuilder msg = new StringBuilder();
        switch (navClicked.getId()) {
            case R.id.nav_my_details:
                msg.append("nav_my_details");
//                getSupportFragmentManager().beginTransaction()
//                        .replace(R.id.fragment_nav_container, MyDetailsFragment.getInstance())
//                        .commit();
                break;
            case R.id.nav_notification:
                msg.append("nav_notification");
//                getSupportFragmentManager().beginTransaction()
//                        .replace(R.id.fragment_container, MyDetailsFragment.getInstance())
//                        .commit();
                break;
            case R.id.nav_my_jobs:
                msg.append("nav_my_jobs");
//                if (!(mFM.findFragmentById(R.id.fragment_nav_container) instanceof MyJobsShowInListFragment)) {
//                    clearBackStack();
//
//                    getSupportFragmentManager().beginTransaction()
//                            .add(R.id.fragment_nav_container, MyJobsShowInListFragment.getInstance(), MyJobsShowInListFragment.FRAGMENT_TAG)
//                            .addToBackStack(MyJobsShowInListFragment.STACK_TAG)
//                            .commit();
//                }
                break;
            case R.id.nav_find_jobs:
                msg.append("nav_find_jobs");
//                getSupportFragmentManager().beginTransaction()
//                        .replace(R.id.fragment_container, MyDetailsFragment.getInstance())
//                        .commit();
                break;
            case R.id.nav_smart_agent:
                msg.append("nav_smart_agent");
//                getSupportFragmentManager().beginTransaction()
//                        .replace(R.id.fragment_container, MyDetailsFragment.getInstance())
//                        .commit();
                break;
            case R.id.nav_information:
                msg.append("nav_information");
//                getSupportFragmentManager().beginTransaction()
//                        .replace(R.id.fragment_container, MyDetailsFragment.getInstance())
//                        .commit();
                break;
            case R.id.nav_add_new_job:
                msg.append("nav_add_new_job");
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.app_main_fragment_nav_container, AddNewJobFragment.getInstance())
                        .addToBackStack(AddNewJobFragment.STACK_TAG)
                        .commit();
                break;
            case R.id.nav_open_web_site:
                msg.append("nav_open_web_site");
//                getSupportFragmentManager().beginTransaction()
//                        .replace(R.id.fragment_container, MyDetailsFragment.getInstance())
//                        .commit();
                break;
        }
        Toast.makeText(this, "nav_on_clicked : " + msg.toString(), Toast.LENGTH_SHORT).show();
        mDrawerLayout.closeDrawers();

    }

    @Override
    public void onBackPressed() {
        Log.d(TAG, "MainActivity::onBackPressed");

        Fragment fragment = mFM.findFragmentById(R.id.app_main_fragment_nav_container);
        if (fragment != null) {
            if (fragment instanceof IOnBackPressed) {
                if (((IOnBackPressed) fragment).onBackPressed()) {
                    finish();
                }
            }
        }

        super.onBackPressed();
    }

    public interface IOnBackPressed {
        /**
         * If you return true the back press will not be taken into account, otherwise the activity will act naturally
         *
         * @return true if your processing has priority if not false
         */
        boolean onBackPressed();
    }
}
