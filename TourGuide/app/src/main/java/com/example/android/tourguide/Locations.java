package com.example.android.tourguide;

/**
 * Created by hp on 9/17/2016.
 */
public class Locations {
    private static final int NO_IMAGE_PROVIDED = -1;
    private int name;
    private int location;
    private int ImageResourceId = NO_IMAGE_PROVIDED;

    public Locations(int name, int location, int imageResourceId) {
        this.name = name;
        this.location = location;
        ImageResourceId = imageResourceId;
    }

    public Locations(int name, int location) {
        this.name = name;
        this.location = location;
    }

    public int getName() {
        return name;
    }

    public int getLocation() {
        return location;
    }

    public int getImageResourceId() {
        return ImageResourceId;
    }

    public boolean hasImage() {
        return ImageResourceId != NO_IMAGE_PROVIDED;
    }
}

