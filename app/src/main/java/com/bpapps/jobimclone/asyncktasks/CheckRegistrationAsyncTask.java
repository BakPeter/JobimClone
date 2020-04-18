package com.bpapps.jobimclone.asyncktasks;

import android.os.AsyncTask;

public class CheckRegistrationAsyncTask extends AsyncTask<Void, Void, Boolean> {

    private IOnCheckedRegistrationListener mOnCheckedRegistrationListener;

    public CheckRegistrationAsyncTask(IOnCheckedRegistrationListener OnCheckedRegistrationListener) {
        mOnCheckedRegistrationListener = OnCheckedRegistrationListener;
    }

    public interface IOnCheckedRegistrationListener {
        void onRegistrationCheckFinished(Boolean result);
    }

    @Override
    protected Boolean doInBackground(Void... voids) {

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    protected void onPostExecute(Boolean aBoolean) {
        super.onPostExecute(aBoolean);

        if (mOnCheckedRegistrationListener != null)
            mOnCheckedRegistrationListener.onRegistrationCheckFinished(aBoolean);
    }
}