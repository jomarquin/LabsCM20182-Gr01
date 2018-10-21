package co.edu.udea.compumovil.gr01_20182.lab3.Entities;

import java.io.Serializable;

public class Drink implements Serializable{

    private String dName;
    private String dDescription;
    private String dPrice;
    private String dImage;
    //private int dImage;

    public Drink() {
    }

    public String getdDescription() {
        return dDescription;
    }

    public void setdDescription(String dDescription) {
        this.dDescription = dDescription;
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

    public String getdImage() {
        return dImage;
    }

    public void setdImage(String dImage) {
        this.dImage = dImage;
    }
}
