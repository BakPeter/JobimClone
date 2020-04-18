package com.bpapps.jobimclone;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.bpapps.jobimclone.fragments.MyDetailsFragment;
import com.bpapps.jobimclone.fragments.SplashScreenFragment;
import com.bpapps.jobimclone.navigation.NavigationFragment;

public class MainActivity extends AppCompatActivity implements NavigationFragment.IOnNavClickedListener {
    public static final String PACKAGE_NAME = "com.bpapps.jobimclone";

    public static final String PREFERENCE_IS_REGISTERED_KEY = PACKAGE_NAME + ".PREFERENCE_IS_REGISTERED_KEY";
    public static final String PREFERENCES_FILE_NAME = PACKAGE_NAME + ".PREFERENCES_FILE_NAME";

    private Toolbar mToolbar;
    private DrawerLayout mDrawerLayout;

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


        mToolbar.findViewById(R.id.image_view_main_action).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDrawerLayout.openDrawer(GravityCompat.START);
                mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);

            }
        });

        mToolbar.findViewById(R.id.image_view_main_nav).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDrawerLayout.openDrawer(GravityCompat.START);
                mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_nav_container, SplashScreenFragment.getInstance(), SplashScreenFragment.FRAGMENT_TAG)
                .addToBackStack(SplashScreenFragment.STACK_TAG)
                .commit();
    }

    @Override
    public void onClick(LinearLayoutCompat navClicked) {
        StringBuilder msg = new StringBuilder();
        switch (navClicked.getId()) {
            case R.id.nav_my_details:
                msg.append("nav_my_details");
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_nav_container, MyDetailsFragment.getInstance())
                        .commit();
                break;
            case R.id.nav_notification:
                msg.append("nav_notification");
//                getSupportFragmentManager().beginTransaction()
//                        .replace(R.id.fragment_container, MyDetailsFragment.getInstance())
//                        .commit();
                break;
            case R.id.nav_my_jobs:
                msg.append("nav_my_jobs");
//                getSupportFragmentManager().beginTransaction()
//                        .replace(R.id.fragment_container, MyDetailsFragment.getInstance())
//                        .commit();
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
//                getSupportFragmentManager().beginTransaction()
//                        .replace(R.id.fragment_container, MyDetailsFragment.getInstance())
//                        .commit();
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
        mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
    }
}
