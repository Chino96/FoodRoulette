package com.example.recyclerview;

public class Restaurant {
    private String mName;
    private String mDescription;
    private int mImageID;

    public Restaurant() {} //default constructor
    // Argument Constructor
    public Restaurant(String name, String description, int imageID) {
        mName = name;
        mDescription = description;
        mImageID = imageID;
    }

    public int getmAddedOn() {
        return mImageID;
    }

    public String getmName() {
        return mName;
    }

    public String getmDescription() {
        return mDescription;
    }

    public void setmAddedOn(int id) {
        this.mImageID = id;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public void setmDescription(String mDescription) {
        this.mDescription = mDescription;
    }
}