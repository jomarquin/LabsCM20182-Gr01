package co.edu.udea.compumovil.gr01_20182.lab4.Entities;

import java.io.Serializable;

public class Adopted implements Serializable {

    private String name;
    private String kind; //tipo perro, gato, etc
    private String breed; //raza
    private String age; //edad
    private String description; //descripción
    private String image; //imagen

    public Adopted() {
    }

    public Adopted(String name, String kind, String breed, String age, String description, String image) {
        this.name = name;
        this.kind = kind;
        this.breed = breed;
        this.age = age;
        this.description = description;
        this.image = image;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getKind() {
        return kind;
    }
    public void setKind(String kind) {
        this.kind = kind;
    }
    public String getBreed() {
        return breed;
    }
    public void setBreed(String breed) {
        this.breed = breed;
    }
    public String getAge() {
        return age;
    }
    public void setAge(String age) {
        this.age = age;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getImage() {
        return image;
    }
    public void setImage(String image) {
        this.image = image;
    }
}
