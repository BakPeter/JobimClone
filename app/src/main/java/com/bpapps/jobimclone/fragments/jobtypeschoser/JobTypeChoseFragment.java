package com.bpapps.jobimclone.fragments.jobtypeschoser;

import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bpapps.jobimclone.MainActivity;
import com.bpapps.jobimclone.R;
import com.bpapps.jobimclone.adapters.JobTypesAdapter;
import com.bpapps.jobimclone.dataclasees.JobType;
import com.bpapps.jobimclone.dataclasees.JobTypeDataManager;
import com.bpapps.jobimclone.fragments.addnewjob.IDataUpdatedBaseInterface;

import java.util.ArrayList;

public class JobTypeChoseFragment extends Fragment
        implements JobTypesAdapter.IOnJobTypeCLickListener,  MainActivity.IOnBackPressed{

    public static final String STACK_TAG = "STACK_TAG." + JobTypeChoseFragment.class.getSimpleName();
    private static final String TAG = "TAG." + JobTypeChoseFragment.class.getSimpleName();

    private IOnJobTypeChosen mOnJobTypeUpdatedListener;

    private RecyclerView mRecyclerViewJobTypes;
    private JobTypesAdapter mJobTypesAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    public static JobTypeChoseFragment getInstance(IOnJobTypeChosen callBack) {
        JobTypeChoseFragment instance = new JobTypeChoseFragment();
        instance.setOnJobTypeUpdatedListener(callBack);

        return instance;
    }

    public JobTypeChoseFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_job_type_chose, container, false);

        mRecyclerViewJobTypes = view.findViewById(R.id.recyclerViewJobType);
        mRecyclerViewJobTypes.hasFixedSize();

        mLayoutManager = new LinearLayoutManager(requireContext());
        mRecyclerViewJobTypes.setLayoutManager(mLayoutManager);
        mJobTypesAdapter = new JobTypesAdapter(getHJobsDataSet(), requireContext(), this);
        mRecyclerViewJobTypes.setAdapter(mJobTypesAdapter);

        return view;
    }

    private ArrayList<JobTypeDataManager> getHJobsDataSet() {
        ArrayList<JobTypeDataManager> dataSet = new ArrayList<>();
        Resources resources = getResources();

        String[] descriptions = resources.getStringArray(R.array.jobTypesNames);
        TypedArray iconsId = resources.obtainTypedArray(R.array.jobTypesIconsIds);

        for (int i = 0; i < descriptions.length; i++) {
            dataSet.add(new JobTypeDataManager(i, descriptions[i], iconsId.getResourceId(i, -1)));
        }

        for (JobTypeDataManager job : dataSet) {
            Log.d(TAG, job.toString());
        }

        return dataSet;
    }

    public void setOnJobTypeUpdatedListener(IOnJobTypeChosen callBack) {
        mOnJobTypeUpdatedListener = callBack;
    }

    @Override
    public void jobTypeClicked(JobTypeDataManager itemClicked) {
        Log.d(TAG, itemClicked.toString());

        if(mOnJobTypeUpdatedListener != null) {
            mOnJobTypeUpdatedListener.jobTypeChosen(itemClicked.getType());
        }
    }

    @Override
    public boolean onBackPressed() {
        boolean stopApp = false;

        Log.d(TAG, "onBackPressed :  stopApp ==" + stopApp);


        return stopApp;
    }

    public interface IOnJobTypeChosen  {
        void jobTypeChosen(@JobType int jobType);
    }
}
