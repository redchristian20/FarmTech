package com.capstone.farmtech.harvest;

public class HarvestItem {
    String id;
    String harvestTitle;
    String harvestCrop;
    String harvestDuration;

    public HarvestItem() {

    }

    public HarvestItem(String id, String harvestTitle, String harvestCrop, String harvestDuration) {

        this.id = id;
        this.harvestTitle = harvestTitle;
        this.harvestCrop = harvestCrop;
        this.harvestDuration = harvestDuration;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHarvestTitle() {
        return harvestTitle;
    }

    public void setHarvestTitle(String harvestTitle) {
        this.harvestTitle = harvestTitle;
    }

    public String getHarvestCrop() {
        return harvestCrop;
    }

    public void setHarvestCrop(String harvestCrop) {
        this.harvestCrop = harvestCrop;
    }

    public String getHarvestDuration() {
        return harvestDuration;
    }

    public void setHarvestDuration(String harvestDuration) {
        this.harvestDuration = harvestDuration;
    }
}