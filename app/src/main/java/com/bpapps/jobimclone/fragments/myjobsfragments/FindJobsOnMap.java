package com.bpapps.jobimclone.fragments.myjobsfragments;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bpapps.jobimclone.R;

public class FindJobsOnMap extends FindJobs {

    public static final String FRAGMENT_TAG = "FRAGMENT_TAG_" + FindJobsOnMap.class.getName();
    public static final String STACK_TAG = "STACK_TAG" + FindJobsOnMap.class.getName();
    private static final String TAG = "TAG_" + FindJobsOnMap.class.getName();

    private ImageView mImageViewFlagShower;
    private Button mButtonChangeFlag;

    private boolean mCurrFlagIsraeli = true;

    public static FindJobsOnMap getInstance() {
        return new FindJobsOnMap();
    }

    @Override
    protected void initAdditionalViewsViews(View rootView) {
        mImageViewFlagShower = rootView.findViewById(R.id.iv_flag_shower);

        mButtonChangeFlag = rootView.findViewById(R.id.button_change_flag);
        mButtonChangeFlag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mCurrFlagIsraeli) {
                    mImageViewFlagShower.setImageDrawable(getResources().getDrawable(R.drawable.flag_of_belgium));
                } else {
                    mImageViewFlagShower.setImageDrawable(getResources().getDrawable(R.drawable.flag_of_israel));
                }

                mCurrFlagIsraeli = !mCurrFlagIsraeli;
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_my_jobs_on_map;
    }

    @Override
    protected void updateVisibilityForOptionMenus() {
        mMenu.findItem(R.id.menu_item_show_in_list).setVisible(true);
        mMenu.findItem(R.id.menu_item_show_on_map).setVisible(false);
    }


    @Override
    public boolean onBackPressed() {
        boolean stopApp = false;

        Log.d(TAG, "onBackPressed :  stopApp ==" + stopApp);


        return stopApp;
    }
}
