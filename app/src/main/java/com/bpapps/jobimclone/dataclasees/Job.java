package com.bpapps.jobimclone.dataclasees;

import android.location.Location;

import androidx.annotation.NonNull;

public class Job {
    private String mCompanyName;
    private String mBranchName;
    private @JobType int mJobType;
    private JobDescription mJobDescription;
    private JobLocation mJobLocation;
    private JobPicture mJobPicture;

    public Job() {
    }

    public String getCompanyName() {
        return mCompanyName;
    }

    public void setCompanyName(@NonNull String companyName) {
        mCompanyName = companyName;
    }

    public String getBranchName() {
        return mBranchName;
    }

    public void setBranchName(String branchName) {
        mBranchName = branchName;
    }

    public int getJobType() {
        return mJobType;
    }

    public void setJobType(int jobType) {
        mJobType = jobType;
    }

    public JobDescription getJobDescription() {
        return mJobDescription;
    }

    public void setJobDescription(JobDescription jobDescription) {
        mJobDescription = jobDescription;
    }

    public JobLocation getJobLocation() {
        return mJobLocation;
    }

    public void setJobLocation(JobLocation jobLocation) {
        mJobLocation = jobLocation;
    }

    public JobPicture getJobPicture() {
        return mJobPicture;
    }

    public void setJobPicture(JobPicture jobPicture) {
        mJobPicture = jobPicture;
    }

    @Override
    public String toString() {
        return "Job{" +
                "mCompanyName='" + mCompanyName + '\'' +
                ", mBranchName='" + mBranchName + '\'' +
                ", mJobType=" + mJobType +
                ", mJobDescription=" + mJobDescription +
                ", mJobLocation=" + mJobLocation +
                ", mJobPicture=" + mJobPicture +
                '}';
    }
}
