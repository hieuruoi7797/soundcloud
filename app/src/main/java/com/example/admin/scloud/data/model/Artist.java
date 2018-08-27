package com.example.admin.scloud.data.model;

public class Artist {
    private String mName;

    public Artist( String name) {
        mName = name;
    }

    public Artist() {
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }
}
