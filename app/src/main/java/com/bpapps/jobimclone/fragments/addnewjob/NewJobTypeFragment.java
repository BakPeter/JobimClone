package com.bpapps.jobimclone.fragments.addnewjob;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.bpapps.jobimclone.R;
import com.bpapps.jobimclone.dataclasees.Job;
import com.bpapps.jobimclone.dataclasees.JobType;
import com.bpapps.jobimclone.fragments.jobtypeschoser.JobTypeChoseFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewJobTypeFragment extends Fragment implements JobTypeChoseFragment.IOnJobTypeChosen {

    private IOnJobTypeUpdatedListener mOnJobTypeUpdatedListener;

    private Job mJob;

    public NewJobTypeFragment() {
        // Required empty public constructor
    }

    public static NewJobTypeFragment getInstance(Job job, IOnJobTypeUpdatedListener callBack) {
        NewJobTypeFragment instance = new NewJobTypeFragment();
        instance.setJob(job);
        instance.setOnJobTypeUpdatedListener(callBack);

        return instance;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_new_job_type, container, false);

        requireActivity().getSupportFragmentManager().beginTransaction()
                .add(R.id.fragmentJobTypesChooserContainer, JobTypeChoseFragment.getInstance(this))
                .addToBackStack(JobTypeChoseFragment.STACK_TAG)
                .commit();

        return view;
    }

    @Override
    public void jobTypeChosen(@JobType int jobType) {
        mJob.setJobType(jobType);

        if (mOnJobTypeUpdatedListener != null) {
            mOnJobTypeUpdatedListener.onJobTypeUpdated(true);
        }
    }

    public void setJob(Job job) {
        mJob = job;
    }

    public interface IOnJobTypeUpdatedListener extends IDataUpdatedBaseInterface {
        void onJobTypeUpdated(boolean dataLegal);
    }

    public void setOnJobTypeUpdatedListener(IOnJobTypeUpdatedListener onJobTypeUpdatedListener) {
        mOnJobTypeUpdatedListener = onJobTypeUpdatedListener;
    }
}
