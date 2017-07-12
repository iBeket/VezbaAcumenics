package com.example.milos.vezba;

import java.io.Serializable;

/**
 * Created by Milos on 07-Jul-17.
 */

public class ListModel implements Serializable {
    private String name;
    private String image;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
