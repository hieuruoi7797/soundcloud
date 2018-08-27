package com.example.admin.scloud.data.model;

public class Genre {

    private String mApiName;
    private int mImagerResource;
    private int mTitleResource;

    public Genre(String name, int titleResource, int imagerResource) {
        mApiName = name;
        mImagerResource = imagerResource;
        mTitleResource = titleResource;
    }

    public Genre() {
    }

    public int getTitleResource() {
        return mTitleResource;
    }

    public void setTitleResource(int titleResource) {
        mTitleResource = titleResource;
    }

    public void setApiName(String name) {
        mApiName = name;
    }

    public void setImagerResource(int imagerResource) {
        mImagerResource = imagerResource;
    }

    public String getApiName() {
        return mApiName;
    }

    public int getImagerResource() {
        return mImagerResource;
    }
}
