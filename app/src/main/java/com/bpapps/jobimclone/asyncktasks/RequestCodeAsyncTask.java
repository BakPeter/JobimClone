package com.bpapps.jobimclone.asyncktasks;

import android.os.AsyncTask;
import android.util.Log;

import com.bpapps.jobimclone.MainActivity;

public class RequestCodeAsyncTask extends AsyncTask<String, Void, Void> {
    private static String TAG = "TAG." + MainActivity.PACKAGE_NAME + "." + RequestCodeAsyncTask.class.getSimpleName();

    private IOnCodeRequestedListener mOnCodeRequestedListener;

    public interface IOnCodeRequestedListener {
        void onCodeRequested();
    }

    public RequestCodeAsyncTask(IOnCodeRequestedListener callBack) {
        mOnCodeRequestedListener = callBack;
    }


    @Override
    protected Void doInBackground(String... params) {

        String phoneNumber = params[0];
        String code = params[1];

        Log.d(TAG, "doInBackground: phoneNumber = " + phoneNumber + ", code = " + code);

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Log.d(TAG, "after request");

        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        Log.d(TAG, "onPostExecute");
        if (mOnCodeRequestedListener != null) {
            mOnCodeRequestedListener.onCodeRequested();
        }
    }
}
