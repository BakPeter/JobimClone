package com.bpapps.jobimclone.dataclasees;

import android.graphics.drawable.Drawable;

public class Country {

    private String mName;
    private String mCodeNumber;
    private Drawable mFlagDrawable;

    public Country(String name, String codeNumber, Drawable flagDrawableResource) {
        mName = name;
        mCodeNumber = codeNumber;
        mFlagDrawable = flagDrawableResource;
    }

    public String getName() {
        return mName;
    }

    public String getCodeNumber() {
        return mCodeNumber;
    }

    public Drawable getFlagDrawable() {
        return mFlagDrawable;
    }
}
