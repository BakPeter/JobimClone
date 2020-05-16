package com.bpapps.jobimclone.dataclasees;

import androidx.annotation.NonNull;

public class JobDescription {
    private String mSummary;
    private String mDescription;

    public JobDescription() {

    }

    public JobDescription(@NonNull String title, String description) {
        mSummary = title;
        mDescription = description;
    }

    public String getSummary() {
        return mSummary;
    }

    public void setSummary(String summary) {
        mSummary = summary;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    @Override
    public String toString() {
        return "JobDescription{" +
                "mTitle='" + mSummary + '\'' +
                ", mDescription='" + mDescription + '\'' +
                '}';
    }
}
