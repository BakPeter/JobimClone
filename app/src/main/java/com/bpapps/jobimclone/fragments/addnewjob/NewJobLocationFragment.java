package com.bpapps.jobimclone.fragments.addnewjob;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.bpapps.jobimclone.R;
import com.bpapps.jobimclone.dataclasees.Job;
import com.bpapps.jobimclone.dataclasees.JobLocation;

import java.io.IOException;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewJobLocationFragment extends Fragment {
    private static final String TAG = "TAG." + NewJobLocationFragment.class.getSimpleName();

    private IJobLocationUpdated mIJobLocationUpdatedListener;

    private EditText mEditTextAddress;
    private Button mButtonFindLocation;
    private TextView mTextViewLocationUpdatedResultShower;

    private Geocoder mGeocoder;
    private Job mJob;

    public NewJobLocationFragment() {
        // Required empty public constructor
    }

    public static NewJobLocationFragment getInstance(Job job, IJobLocationUpdated callBack) {
        NewJobLocationFragment instance = new NewJobLocationFragment();
        instance.setJob(job);
        instance.setJobLocationUpdatedListener(callBack);

        return instance;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_new_job_location, container, false);

        mEditTextAddress = view.findViewById(R.id.editTextAddress);

        mButtonFindLocation = view.findViewById(R.id.buttonFindLocation);
        mButtonFindLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Address> addresses;
                try {
                    String userInput = mEditTextAddress.getText().toString();
                    if (userInput.length() <= 0) {
                        badAddress();
                        return;
                    }

                    addresses = mGeocoder.getFromLocationName(userInput, 1);
                    if (addresses.size() > 0) {
                        Address address = addresses.get(0);

                        mJob.setJobLocation(new JobLocation(address.getLongitude(), address.getLatitude()));
                        goodAddress();

                    } else {
                        badAddress();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    Log.d(TAG, e.getMessage());
                }

                Log.d(TAG, mJob.getJobLocation() == null ? "job location = NULL" : mJob.getJobLocation().toString());
            }

            private void goodAddress() {
                mTextViewLocationUpdatedResultShower.setText("כתובת המשרה עודכנה");
                mTextViewLocationUpdatedResultShower.setTextColor(getResources().getColor(R.color.colorPrimary));

                if (mIJobLocationUpdatedListener != null) {
                    mIJobLocationUpdatedListener.jobLocationUpdated(true);
                }
            }

            private void badAddress() {
                mTextViewLocationUpdatedResultShower.setText("הכתובת לא נכונה, נסה שוב");
                mTextViewLocationUpdatedResultShower.setTextColor(getResources().getColor(R.color.new_job_bad_address));

                mJob.setJobLocation(null);

                if (mIJobLocationUpdatedListener != null) {
                    mIJobLocationUpdatedListener.jobLocationUpdated(false);
                }
            }
        });

        mTextViewLocationUpdatedResultShower = view.findViewById(R.id.textViewLocationUpdatedResultShower);

        mGeocoder = new Geocoder(requireContext());

        return view;
    }


    public void setJob(Job job) {
        mJob = job;
    }

    public void setJobLocationUpdatedListener(IJobLocationUpdated IJobLocationUpdatedListener) {
        mIJobLocationUpdatedListener = IJobLocationUpdatedListener;
    }

    public interface IJobLocationUpdated extends IDataUpdatedBaseInterface {
        void jobLocationUpdated(boolean dataLegal);
    }
}
