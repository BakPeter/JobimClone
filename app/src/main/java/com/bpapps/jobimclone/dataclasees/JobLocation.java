package com.bpapps.jobimclone.dataclasees;

public class JobLocation {
    private double mLongitude;
    private double mLatitude;

    public JobLocation(double longitude, double latitude) {
        mLongitude = longitude;
        mLatitude = latitude;
    }

    public double getLongitude() {
        return mLongitude;
    }

    public double getLatitude() {
        return mLatitude;
    }

    @Override
    public String toString() {
        return "JobLocation{" +
                "mLongitude=" + mLongitude +
                ", mLatitude=" + mLatitude +
                '}';
    }
}
