package com.capstone.farmtech.crop_information;

public class Crops {
    private String mCropName,mCropDescription,mCropImageLink;

    public Crops(){
    }

    public Crops(String mCropName, String mCropDescription, String mCropImageLink) {
        this.mCropName = mCropName;
        this.mCropDescription = mCropDescription;
        this.mCropImageLink = mCropImageLink;
    }

    public String getmCropName() {
        return mCropName;
    }


    public String getmCropDescription() {
        return mCropDescription;
    }

    public String getmCropImageLink() {
        return mCropImageLink;
    }
}
