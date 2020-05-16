package com.bpapps.jobimclone.dataclasees;

public class JobTypeDataManager {
    private @JobType int mType;
    private String mDescription;
    private int mJobIconId;
    private boolean mIsChecked;

    public JobTypeDataManager(int type, String description, int jobIconId) {
        mType = type;
        mDescription = description;
        mJobIconId = jobIconId;
    }

    public int getType() {
        return mType;
    }

    public String getDescription() {
        return mDescription;
    }

    public boolean isChecked() {
        return mIsChecked;
    }

    public void setChecked(boolean checked) {
        mIsChecked = checked;
    }

    public int getJobIconId() {
        return mJobIconId;
    }

    public void setType(@JobType int type) {
        mType = type;
    }

    @Override
    public String toString() {
        return "JobTypeDataManager{" +
                "mType=" + mType +
                ", mDescription='" + mDescription + '\'' +
                ", mJobIconId=" + mJobIconId +
                '}';
    }
}
