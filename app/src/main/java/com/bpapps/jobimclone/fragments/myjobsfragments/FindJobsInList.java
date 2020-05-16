package com.bpapps.jobimclone.fragments.myjobsfragments;

import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.bpapps.jobimclone.MainActivity;
import com.bpapps.jobimclone.R;


public class FindJobsInList extends FindJobs {
    public static final String FRAGMENT_TAG = "FRAGMENT_TAG_" + FindJobsInList.class.getName();
    public static final String STACK_TAG = "STACK_TAG" + FindJobsInList.class.getName();
    private static final String TAG = "TAG_" + FindJobsInList.class.getName();

    private TextView mTextViewCounterIncrementer;
    private TextView mTextViewCounterShower;

    private int mCounter = 0;

    public static FindJobsInList getInstance() {
        return new FindJobsInList();
    }

    @Override
    protected void initAdditionalViewsViews(View rootView) {
        mTextViewCounterIncrementer = rootView.findViewById(R.id.text_view_my_jobs_counter_incrementer);
        mTextViewCounterIncrementer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCounter++;
                mTextViewCounterShower.setText(mCounter + "");
            }
        });

        mTextViewCounterShower = rootView.findViewById(R.id.text_view_my_jobs_counter_shower);
        mTextViewCounterShower.setText(mCounter + "");
    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_my_jobs_in_list;
    }

    @Override
    protected void updateVisibilityForOptionMenus() {
        mMenu.findItem(R.id.menu_item_show_in_list).setVisible(false);
        mMenu.findItem(R.id.menu_item_show_on_map).setVisible(true);
    }

    @Override
    public boolean onBackPressed() {
        boolean stopApp = true;

        Log.d(TAG, "onBackPressed :  stopApp ==" + stopApp);


        return stopApp;
    }
}
