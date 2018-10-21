package co.edu.udea.compumovil.gr01_20182.lab3.Entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Plates {

    @SerializedName("foods")
    @Expose
    private List<Plate> foods = null;

    public List<Plate> getPlates() {
        return foods;
    }

    public void setPlates(List<Plate> foods) {
        this.foods = foods;
    }
}
