package com.bpapps.jobimclone.asyncktasks;

import android.os.AsyncTask;
import android.util.Log;

import com.bpapps.jobimclone.MainActivity;

public class CodeVerificationAsyncTask extends AsyncTask<String, Void, Boolean> {
    private static String TAG = "TAG." + MainActivity.PACKAGE_NAME + "." + RequestCodeAsyncTask.class.getSimpleName();

    private IOnCodeVerifiedResult OnCodeVerifiedResult;

    public interface IOnCodeVerifiedResult {
        void onCodeVerified(boolean isVerified);
    }

    public CodeVerificationAsyncTask(IOnCodeVerifiedResult callBack) {
        OnCodeVerifiedResult = callBack;
    }

    @Override
    protected Boolean doInBackground(String... params) {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        String phoneNumber = params[0];
        String countryCode = params[1];
        String code = params[2];

        Log.d(TAG, "doInBackground : phoneNumber = " + phoneNumber + ", countryCode = " + countryCode + ", code = " + code);

        Boolean isCodeVerified = true;
        return isCodeVerified;
    }

    @Override
    protected void onPostExecute(Boolean isCodeVerified) {
        Log.d(TAG, "onPostExecute : isCodeVerified = " + isCodeVerified);

        if (OnCodeVerifiedResult != null) {
            OnCodeVerifiedResult.onCodeVerified(isCodeVerified);
        }
    }
}
