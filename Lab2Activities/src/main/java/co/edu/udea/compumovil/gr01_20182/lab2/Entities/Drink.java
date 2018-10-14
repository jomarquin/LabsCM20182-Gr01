package co.edu.udea.compumovil.gr01_20182.lab2.Entities;

import java.io.Serializable;

public class Drink implements Serializable{

    private String dName;
    private String dPrice;
    private int dImage;

    public Drink() {
    }

    public String getdName() {
        return dName;
    }

    public void setdName(String dName) {
        this.dName = dName;
    }

    public String getdPrice() {
        return dPrice;
    }

    public void setdPrice(String dPrice) {
        this.dPrice = dPrice;
    }

    public int getdImage() {
        return dImage;
    }

    public void setdImage(int dImage) {
        this.dImage = dImage;
    }
}
