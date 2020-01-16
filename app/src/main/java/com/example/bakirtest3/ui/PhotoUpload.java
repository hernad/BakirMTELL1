package com.example.bakirtest3.ui;

public class PhotoUpload {
    private String mName;
    private String mImageUrl;

    public PhotoUpload(){
        //empty constructor needed
    }

    public PhotoUpload(String mName, String mImageUrl) {
        if (mName.trim().equals("")){mName = "No name";}
        this.mName = mName;
        this.mImageUrl = mImageUrl;
    }

    public String getmName() {
        return mName;
    }

    public String getmImageUrl() {
        return mImageUrl;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public void setmImageUrl(String mImageUrl) {
        this.mImageUrl = mImageUrl;
    }
}
