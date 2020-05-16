package com.bpapps.jobimclone.fragments.addnewjob;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.widget.AppCompatEditText;
import androidx.fragment.app.Fragment;

import com.bpapps.jobimclone.R;
import com.bpapps.jobimclone.dataclasees.Job;
import com.bpapps.jobimclone.dataclasees.JobDescription;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewJobDescriptionFragment extends Fragment {

    private IOnNewJobDescriptionDataUpdatedListener mOnNewJobDescriptionDataUpdatedListener;
    private Job mJob;

    private AppCompatEditText mETJobSummary;
    private AppCompatEditText mETJobDescription;

    public NewJobDescriptionFragment() {
        // Required empty public constructor
    }

    public static NewJobDescriptionFragment getInstance(Job job, IOnNewJobDescriptionDataUpdatedListener callBack) {
        NewJobDescriptionFragment instance = new NewJobDescriptionFragment();
        instance.mJob = job;
        instance.mJob.setJobDescription(new JobDescription());
        instance.mOnNewJobDescriptionDataUpdatedListener = callBack;

        return instance;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_new_job_description, container, false);

        mJob.setJobDescription(new JobDescription());

        mETJobSummary = view.findViewById(R.id.editTextNewJobSummary);
        mETJobSummary.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mJob.getJobDescription().setSummary(s.toString());

                updateViewsAfterInput();
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        mETJobDescription = view.findViewById(R.id.editTextNewJobDescription);
        mETJobDescription.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mJob.getJobDescription().setDescription(s.toString());

                updateViewsAfterInput();
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        return view;
    }

    private void updateViewsAfterInput() {
        String summary = mJob.getJobDescription().getSummary();
        String description = mJob.getJobDescription().getDescription();

        if (summary != null && summary.length() > 0
                && description != null && description.length() > 0) {
            mOnNewJobDescriptionDataUpdatedListener.oneDescriptionUpdated(true);
        } else {
            mOnNewJobDescriptionDataUpdatedListener.oneDescriptionUpdated(false);
        }
    }

    public interface IOnNewJobDescriptionDataUpdatedListener extends IDataUpdatedBaseInterface {
        void oneDescriptionUpdated(boolean isDataLegal);
    }
}
