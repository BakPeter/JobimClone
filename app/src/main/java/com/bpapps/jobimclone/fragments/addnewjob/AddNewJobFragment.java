package com.bpapps.jobimclone.fragments.addnewjob;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.bpapps.jobimclone.R;
import com.bpapps.jobimclone.adapters.NewJobAdapter;
import com.bpapps.jobimclone.dataclasees.Job;
import com.bpapps.jobimclone.dialogs.NewJobExitDialog;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddNewJobFragment extends Fragment
        implements View.OnClickListener,
        NewJobCompanyDetailsFragment.ICompanyDetailsUpdated,
        NewJobLocationFragment.IJobLocationUpdated,
        NewJobTypeFragment.IOnJobTypeUpdatedListener,
        NewJobDescriptionFragment.IOnNewJobDescriptionDataUpdatedListener {

    public static final String STACK_TAG = "STACK_TAG.AddNewJobFragment";
    private static final String EXIT_DIALOG_TAG = "EXIT_DIALOG_TAG.AddNewJobFragment";
    private static final String TAG = "TAG." + AddNewJobFragment.class.getSimpleName();

    private Job mJob = new Job();

    private AppCompatImageButton mImageButtonNewJobDetails;
    private AppCompatImageButton mImageButtonJobType;
    private AppCompatImageButton mImageButtonJobDescription;
    private AppCompatImageButton mImageButtonJobLocation;
    private AppCompatImageButton mImageButtonJobPicAttachment;

    private ViewPager2 mViewPager;
    private NewJobAdapter mAdapter;

    public AddNewJobFragment() {
        // Required empty public constructor
    }

    public static Fragment getInstance() {
        return new AddNewJobFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_new_job, container, false);

        mImageButtonNewJobDetails = view.findViewById(R.id.imageButtonJobDetails);
        mImageButtonNewJobDetails.setOnClickListener(this);
        mImageButtonJobType = view.findViewById(R.id.imageButtonJobType);
        mImageButtonJobType.setOnClickListener(this);
        mImageButtonJobDescription = view.findViewById(R.id.imageButtonJobDescription);
        mImageButtonJobDescription.setOnClickListener(this);
        mImageButtonJobPicAttachment = view.findViewById(R.id.imageButtonJobPicAttachment);
        mImageButtonJobPicAttachment.setOnClickListener(this);
        mImageButtonJobLocation = view.findViewById(R.id.imageButtonJobLocation);
        mImageButtonJobLocation.setOnClickListener(this);

        mAdapter = new NewJobAdapter(this, mJob, this);
        mViewPager = view.findViewById(R.id.viewPagerAddNewJob);
        mViewPager.setAdapter(mAdapter);
        mViewPager.setUserInputEnabled(false);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        setUpToolBar((AppCompatActivity) requireActivity(), true);
    }

    @Override
    public void onStop() {
        super.onStop();

        setUpToolBar((AppCompatActivity) requireActivity(), false);
    }

    private void setUpToolBar(AppCompatActivity activity, boolean start) {
        activity.getSupportActionBar().show();

        Toolbar toolbar = activity.findViewById(R.id.tool_bar);
        ((TextView) toolbar.findViewById(R.id.text_view_tool_bar_title)).setText(R.string.add_new_job_tool_bar_title);

        TextView textViewCancel = toolbar.findViewById(R.id.text_view_add_job_cancel);
        textViewCancel.setOnClickListener(this);

        TextView textViewNext = toolbar.findViewById(R.id.text_view_add_job_next);
        textViewNext.setOnClickListener(this);

        if (start) {
            toolbar.findViewById(R.id.image_view_main_nav).setVisibility(View.GONE);
            textViewCancel.setVisibility(View.VISIBLE);
            textViewNext.setVisibility(View.VISIBLE);
        } else {
            toolbar.findViewById(R.id.image_view_main_nav).setVisibility(View.VISIBLE);
            textViewCancel.setVisibility(View.GONE);
            textViewNext.setVisibility(View.GONE);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.text_view_add_job_cancel:
//                Toast.makeText(requireContext(), "cancel", Toast.LENGTH_SHORT).show();
                shoExitDialog();
                break;
            case R.id.text_view_add_job_next:
//                Toast.makeText(requireContext(), "next", Toast.LENGTH_SHORT).show();
                mViewPager.setCurrentItem(mViewPager.getCurrentItem() + 1);
                break;
            case R.id.imageButtonJobDetails:
//                Toast.makeText(requireContext(), "imageButtonNewJobDetails", Toast.LENGTH_SHORT).show();
                mViewPager.setCurrentItem(0);
                break;
            case R.id.imageButtonJobType:
                Toast.makeText(requireContext(), "imageButtonJobType", Toast.LENGTH_SHORT).show();
                mViewPager.setCurrentItem(1);
                break;
            case R.id.imageButtonJobDescription:
                Toast.makeText(requireContext(), "imageButtonJobDescription", Toast.LENGTH_SHORT).show();
                mViewPager.setCurrentItem(2);
                break;
            case R.id.imageButtonJobLocation:
//                Toast.makeText(requireContext(), "imageButtonJobLocation", Toast.LENGTH_SHORT).show();
                mViewPager.setCurrentItem(3);
                break;
            case R.id.imageButtonJobPicAttachment:
                Toast.makeText(requireContext(), "imageButtonJobPicAttachment", Toast.LENGTH_SHORT).show();
                mViewPager.setCurrentItem(4);
                break;
        }
    }

    private void shoExitDialog() {
        final NewJobExitDialog dialog = NewJobExitDialog.getInstance(new NewJobExitDialog.IDialogButtonOnClickListener() {
            @Override
            public void onClick(int buttonId) {
                NewJobExitDialog dialog = (NewJobExitDialog) requireActivity().getSupportFragmentManager().findFragmentByTag(EXIT_DIALOG_TAG);
                if (dialog != null)
                    dialog.dismiss();

                if (buttonId == R.id.buttonClose) {
                    requireActivity().getSupportFragmentManager().popBackStack();
                }
            }
        });
        dialog.show(requireActivity().getSupportFragmentManager(), EXIT_DIALOG_TAG);
    }

    private void updateViewPagerNavButtonView(AppCompatImageButton btn, int imageResourceId, int backgroundColorId) {
        btn.setImageResource(imageResourceId);
        btn.setBackgroundColor(getResources().getColor(backgroundColorId));
    }

    @Override
    public void companyDetailsDataUpdated(boolean dataLegal) {
        if (dataLegal) {
            updateViewPagerNavButtonView(mImageButtonNewJobDetails, R.drawable.ic_new_job_data_set, R.color.new_job_filled_out);
        } else {
            updateViewPagerNavButtonView(mImageButtonNewJobDetails, R.drawable.ic_new_job_company_name, R.color.new_job_not_filled_out);
        }

        Log.d(TAG, mJob.toString());
    }


    @Override
    public void jobLocationUpdated(boolean dataLegal) {
        if (dataLegal) {
            updateViewPagerNavButtonView(mImageButtonJobLocation, R.drawable.ic_new_job_data_set, R.color.new_job_filled_out);
        } else {
            updateViewPagerNavButtonView(mImageButtonJobLocation, R.drawable.ic_new_job_location, R.color.new_job_not_filled_out);
        }

        Log.d(TAG, mJob.toString());
    }

    @Override
    public void onJobTypeUpdated(boolean dataLegal) {
        if (dataLegal) {
            updateViewPagerNavButtonView(mImageButtonJobType, R.drawable.ic_new_job_data_set, R.color.new_job_filled_out);
        } else {
            updateViewPagerNavButtonView(mImageButtonJobType, R.drawable.ic_new_job_location, R.color.new_job_not_filled_out);
        }

        Log.d(TAG, mJob.toString());
    }

    @Override
    public void oneDescriptionUpdated(boolean isDataLegal) {
        if (isDataLegal) {
            updateViewPagerNavButtonView(mImageButtonJobDescription, R.drawable.ic_new_job_data_set, R.color.new_job_filled_out);
        } else {
            updateViewPagerNavButtonView(mImageButtonJobDescription, R.drawable.ic_new_job_location, R.color.new_job_not_filled_out);
        }

        Log.d(TAG, mJob.toString());
    }
}
