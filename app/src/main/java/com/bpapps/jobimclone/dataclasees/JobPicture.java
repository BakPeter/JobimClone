package com.bpapps.jobimclone.dataclasees;

public class JobPicture {
    private String mUrl;

    public JobPicture(String url) {
        mUrl = url;
    }

    public String getUrl() {
        return mUrl;
    }

    @Override
    public String toString() {
        return "JobPicture{" +
                "mUrl='" + mUrl + '\'' +
                '}';
    }
}
