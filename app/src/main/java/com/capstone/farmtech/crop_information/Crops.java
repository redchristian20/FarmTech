package com.capstone.farmtech.crop_information;

public class Crops {
    private String mCropName,mCropDescription;

    public Crops(){
    }
    public Crops(String CropName, String CropDescription){
        this.mCropName = CropName;
        this.mCropDescription = CropDescription;
    }
    public String getmCropName() {
        return mCropName;
    }
    public String getmCropDescription() {
        return mCropDescription;
    }

}
