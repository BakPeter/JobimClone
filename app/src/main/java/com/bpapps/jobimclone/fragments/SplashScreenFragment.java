package com.bpapps.jobimclone.fragments;

import android.Manifest;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.bpapps.jobimclone.MainActivity;
import com.bpapps.jobimclone.R;
import com.bpapps.jobimclone.asyncktasks.CheckRegistrationAsyncTask;
import com.bpapps.jobimclone.dialogs.AppAlertDialog;

/**
 * A simple {@link Fragment} subclass.
 */
public class SplashScreenFragment extends Fragment implements CheckRegistrationAsyncTask.IOnCheckedRegistrationListener {
    public static final String FRAGMENT_TAG = "FRAGMENT_TAG." + MainActivity.PACKAGE_NAME + "." + SplashScreenFragment.class.getName();
    public static final String STACK_TAG = "STACK_TAG." + SplashScreenFragment.class.getName();
    private static final String FRAGMENT_DIALOG_PERMISSIONS_RATIONAL = MainActivity.PACKAGE_NAME + ".FRAGMENT_DIALOG_PERMISSIONS_RATIONAL";

    private static final int ALL_PERMISSIONS = 1;
    private static final String TAG = "TAG." + SplashScreenFragment.class.getName();

    private Boolean mIsRegistered;
    private boolean mArePermissionsGranted;
    private boolean mOnResumeInvoked = false;

    public static SplashScreenFragment getInstance() {
        return new SplashScreenFragment();
    }

    public SplashScreenFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_splash_screen, container, false);

        SharedPreferences pref = requireActivity().getSharedPreferences(MainActivity.PREFERENCES_FILE_NAME, Context.MODE_PRIVATE);
        boolean isRegistered = pref.getBoolean(MainActivity.PREFERENCE_IS_REGISTERED_KEY, false);

        if (isRegistered) {
            mIsRegistered = isRegistered;
        } else {
            new CheckRegistrationAsyncTask(this).execute();
        }

        return v;
    }


    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume");


        if (!mOnResumeInvoked) {
            AppCompatActivity activity = (AppCompatActivity) requireActivity();
            activity.getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_LTR);
            activity.getSupportActionBar().hide();

            requestPermissionsForApp();

            mOnResumeInvoked = true;
        }
    }

    private void requestPermissionsForApp() {
        if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                || ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED
                || ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED
                || ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(requireActivity(), Manifest.permission.ACCESS_FINE_LOCATION)
                    || ActivityCompat.shouldShowRequestPermissionRationale(requireActivity(), Manifest.permission.READ_CONTACTS)
                    || ActivityCompat.shouldShowRequestPermissionRationale(requireActivity(), Manifest.permission.CALL_PHONE)
                    || ActivityCompat.shouldShowRequestPermissionRationale(requireActivity(), Manifest.permission.READ_EXTERNAL_STORAGE)) {

                showPermissionsRational();
            } else {
                askForPermissions();
            }
        } else {
            mArePermissionsGranted = true;
            navigateToNextScreen();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (grantResults.length > 0) {
            int grantedResultsCount = 0;
            for (int result : grantResults) {
                if (result == PackageManager.PERMISSION_GRANTED) {
                    grantedResultsCount++;
                }
            }

            if (grantedResultsCount == 4) {
                mArePermissionsGranted = true;
                navigateToNextScreen();
            }
        }
    }

    private void showPermissionsRational() {
        AppAlertDialog dialog = AppAlertDialog.newInstance(
                getResources().getString(R.string.permission_rational_dialog_title),
                getResources().getString(R.string.permission_rational_dialog_massage),
                getResources().getString(R.string.permission_rational_dialog_button_text),
                new AppAlertDialog.IDialogButtonOnClickListener() {
                    @Override
                    public void onClick() {
                        AppAlertDialog dialog = (AppAlertDialog) getParentFragmentManager().findFragmentByTag(FRAGMENT_DIALOG_PERMISSIONS_RATIONAL);
                        if (dialog != null)
                            dialog.dismiss();

                        askForPermissions();
                    }
                });

        dialog.setCancelable(false);

        dialog.show(requireActivity().getSupportFragmentManager(), FRAGMENT_DIALOG_PERMISSIONS_RATIONAL);
    }

    private void askForPermissions() {
        requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.READ_CONTACTS,
                        Manifest.permission.CALL_PHONE,
                        Manifest.permission.READ_EXTERNAL_STORAGE},
                ALL_PERMISSIONS);
    }

    @Override
    public void onRegistrationCheckFinished(Boolean result) {
        mIsRegistered = result;
        if (mIsRegistered) {
            SharedPreferences pref = requireActivity().getSharedPreferences(MainActivity.PREFERENCES_FILE_NAME, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = pref.edit();
            editor.putBoolean(MainActivity.PREFERENCE_IS_REGISTERED_KEY, mIsRegistered);
            editor.apply();
        }

        navigateToNextScreen();
    }

    private void navigateToNextScreen() {
        if (mArePermissionsGranted) {
            if (mIsRegistered != null) {
                if (mIsRegistered) {
                    FragmentManager fm = requireActivity().getSupportFragmentManager();
                    fm.popBackStack();
                    fm.beginTransaction()
                            .replace(R.id.fragment_nav_container, MyJobsFragment.getInstance())
                            .addToBackStack(MyJobsFragment.STACK_TAG)
                            .commit();
                } else {
                    requireActivity().getSupportFragmentManager()
                            .beginTransaction()
                            .add(R.id.fragment_nav_container, RegisterPhoneNumberFragment.getInstance(), FRAGMENT_TAG)
                            .addToBackStack(RegisterPhoneNumberFragment.STACK_TAG)
                            .commit();
                }
            }
        }
    }

    private class PermissionTaskHelper extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            askForPermissions();
        }
    }
}
