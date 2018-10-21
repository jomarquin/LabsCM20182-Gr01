package co.edu.udea.compumovil.gr01_20182.lab3.Entities;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


public class Food implements Serializable {

    @SerializedName("fName")
    private String fName;

    @SerializedName("fDescription")
    private String fDescription;

    @SerializedName("fImage")
    private String fImage;

    @SerializedName("fType")
    private String fType;

    @SerializedName("fTime")
    private String fTime;

    @SerializedName("fPrice")
    private String fPrice;


    public Food(){

    }

    public Food(String fName, String fDescription, String fImage, String fType, String fTime, String fPrice) {
        this.fName = fName;
        this.fDescription = fDescription;
        this.fImage = fImage;
        this.fType = fType;
        this.fTime = fTime;
        this.fPrice = fPrice;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getfDescription() {
        return fDescription;
    }

    public void setfDescription(String fDescription) {
        this.fDescription = fDescription;
    }

    public String getfImage() {
        return fImage;
    }

    public void setfImage(String fImage) {
        this.fImage = fImage;
    }

    public String getfType() {
        return fType;
    }

    public void setfType(String fType) {
        this.fType = fType;
    }

    public String getfTime() {
        return fTime;
    }

    public void setfTime(String fTime) {
        this.fTime = fTime;
    }

    public String getfPrice() {
        return fPrice;
    }

    public void setfPrice(String fPrice) {
        this.fPrice = fPrice;
    }
}
