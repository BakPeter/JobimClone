package com.bpapps.jobimclone.adapters;

import android.util.MalformedJsonException;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.bpapps.jobimclone.dataclasees.Job;
import com.bpapps.jobimclone.fragments.addnewjob.IDataUpdatedBaseInterface;
import com.bpapps.jobimclone.fragments.addnewjob.NewJobCompanyDetailsFragment;
import com.bpapps.jobimclone.fragments.addnewjob.NewJobDescriptionFragment;
import com.bpapps.jobimclone.fragments.addnewjob.NewJobLocationFragment;
import com.bpapps.jobimclone.fragments.addnewjob.NewJobPicAttachmentFragment;
import com.bpapps.jobimclone.fragments.addnewjob.NewJobTypeFragment;

public class NewJobAdapter extends FragmentStateAdapter {

    private Job mJob;
    private IDataUpdatedBaseInterface mDataUpdatedInterfaceHolder;

    public NewJobAdapter(@NonNull Fragment fragment, Job job,  IDataUpdatedBaseInterface dataUpdatedInterfaceHolder) {
        super(fragment);

        mJob = job;
        mDataUpdatedInterfaceHolder = dataUpdatedInterfaceHolder;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return NewJobCompanyDetailsFragment.getInstance(mJob, (NewJobCompanyDetailsFragment.ICompanyDetailsUpdated) mDataUpdatedInterfaceHolder);
            case 1:
                return NewJobTypeFragment.getInstance(mJob, (NewJobTypeFragment.IOnJobTypeUpdatedListener) mDataUpdatedInterfaceHolder);
            case 2:
                return NewJobDescriptionFragment.getInstance(mJob, (NewJobDescriptionFragment.IOnNewJobDescriptionDataUpdatedListener) mDataUpdatedInterfaceHolder);
            case 3:
                return NewJobLocationFragment.getInstance(mJob, (NewJobLocationFragment.IJobLocationUpdated) mDataUpdatedInterfaceHolder);
            case 4:
                return NewJobPicAttachmentFragment.getInstance(mJob);
        }

        return null;
    }

    @Override
    public int getItemCount() {
        return 5;
    }
}
