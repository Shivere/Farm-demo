package com.shawnshivere.farmdemo;

public class Animal {
    private int id, thumbnail;
    private String name, gender, mother;
    private double age;

    public Animal() {
    }

    public Animal(int id, String name, double age, String gender, String mother,int thumbnail) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.mother = mother;
        this.thumbnail = thumbnail;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getAge() {
        return age;
    }

    public void setAge(double age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMother() {
        return mother;
    }

    public void setMother(String mother) {
        this.mother = mother;
    }

    public int getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(int thumbnail) {
        this.thumbnail = thumbnail;
    }
}
