package com.bpapps.jobimclone.fragments.addnewjob;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import com.bpapps.jobimclone.R;
import com.bpapps.jobimclone.dataclasees.Job;
import com.bpapps.jobimclone.dialogs.NewJobHelpDialog;

public class NewJobCompanyDetailsFragment extends Fragment {

    private static final String HELP_DIALOG_TAG = "HELP_DIALOG_TAG.AddNewJobFragment";
    private static final String TAG = "TAG." + NewJobCompanyDetailsFragment.class.getSimpleName();

    private AppCompatEditText mEditTextCompanyName;
    private AppCompatEditText mEditTextBranchName;
    private AppCompatTextView mTextViewHelp;

    private Job mJob;
    private boolean mFirstFocusChanged;
    private ICompanyDetailsUpdated mDataUpdatedCallBack;

    public NewJobCompanyDetailsFragment() {
        // Required empty public constructor
    }


    public static NewJobCompanyDetailsFragment getInstance(@NonNull Job job, ICompanyDetailsUpdated dataUpdatedCallBack) {
        NewJobCompanyDetailsFragment fragment = new NewJobCompanyDetailsFragment();

        fragment.setJob(job);
        fragment.setDataUpdatedCallBack(dataUpdatedCallBack);

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_new_job_company_deatail, container, false);

        mEditTextCompanyName = view.findViewById(R.id.editTextCompanyName);
        mEditTextCompanyName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    v.setBackground(getResources().getDrawable(R.drawable.new_job_company_details_focused));
                } else {
                    v.setBackground(getResources().getDrawable(R.drawable.new_job_company_details_not_focused));
                    if (!mFirstFocusChanged)
                        mFirstFocusChanged = true;
                }
            }
        });

        mEditTextCompanyName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mJob.setCompanyName(s.toString());
             //   Log.d(TAG, mJob.getCompanyName());

                if (s.length() > 0) {
                    mDataUpdatedCallBack.companyDetailsDataUpdated(true);
                } else {
                    mDataUpdatedCallBack.companyDetailsDataUpdated(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        mEditTextBranchName = view.findViewById(R.id.editTextBranchName);
        mEditTextBranchName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    v.setBackground(getResources().getDrawable(R.drawable.new_job_company_details_focused));
                    if (!mFirstFocusChanged)
                        mEditTextCompanyName.setBackground(getResources().getDrawable(R.drawable.new_job_company_details_not_focused));
                } else {
                    v.setBackground(getResources().getDrawable(R.drawable.new_job_company_details_not_focused));
                }
            }
        });
        mEditTextBranchName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mJob.setBranchName(s.toString());
                Log.d(TAG, mJob.getBranchName());
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        mTextViewHelp = view.findViewById(R.id.textViewNewJobHelp);
        mTextViewHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(requireContext(), "help", Toast.LENGTH_SHORT).show();
                showHelpDialog();
            }
        });

        return view;
    }

    private void showHelpDialog() {
        NewJobHelpDialog dialog = NewJobHelpDialog.getInstance();
        dialog.show(requireActivity().getSupportFragmentManager(), HELP_DIALOG_TAG);
    }

    public void setJob(Job job) {
        mJob = job;
    }

    public void setDataUpdatedCallBack(ICompanyDetailsUpdated dataUpdatedCallBack) {
        mDataUpdatedCallBack = dataUpdatedCallBack;
    }

    public interface ICompanyDetailsUpdated extends IDataUpdatedBaseInterface{
        void companyDetailsDataUpdated(boolean dataLegal);
    }
}
