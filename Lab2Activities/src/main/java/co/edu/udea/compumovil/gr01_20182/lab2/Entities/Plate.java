package co.edu.udea.compumovil.gr01_20182.lab2.Entities;

import java.io.Serializable;

public class Plate implements Serializable {
    private String pName;
    private String pType;
    private String pPrice;
    private String pTime;
    private int pImage;
    private int pImageDetail;

    public Plate() {
    }



    public Plate(String pName, String pType, String pPrice, String pTime, int pImage, int pImageDetail) {
        this.pName = pName;
        this.pType = pType;
        this.pPrice = pPrice;
        this.pTime = pTime;
        this.pImage = pImage;
        this.pImageDetail = pImageDetail;
    }

    public int getpImageDetail() {
        return pImageDetail;
    }

    public void setpImageDetail(int pImageDetail) {
        this.pImageDetail = pImageDetail;
    }

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    public String getpType() {
        return pType;
    }

    public void setpType(String pType) {
        this.pType = pType;
    }

    public String getpPrice() {
        return pPrice;
    }

    public void setpPrice(String pPrice) {
        this.pPrice = pPrice;
    }

    public String getpTime() {
        return pTime;
    }

    public void setpTime(String pTime) {
        this.pTime = pTime;
    }

    public int getpImage() {  return pImage; }

    public void setpImage(int pImage) { this.pImage = pImage; }
}
