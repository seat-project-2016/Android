package com.seatcorporation.seat.Models;

import android.net.Uri;

public class ItemObjects {
    private String name;
    private Uri photo;

    public ItemObjects(String name, Uri photo) {
        this.name = name;
        this.photo = photo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Uri getPhoto() {
        return photo;
    }

    public void setPhoto(Uri photo) {
        this.photo = photo;
    }
}
