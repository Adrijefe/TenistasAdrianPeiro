package com.example.tenistasadrianpeiro;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
@Entity
public class Tenista implements Serializable {
    @PrimaryKey(autoGenerate = true)
private int id;
private String name;
private String date;
private int age;
private String grandSlams;
private String sprite;


    public Tenista(int id, String name, int age, String grandSlams, String date, String sprite) {
        this.id = id;
        this.grandSlams = grandSlams;
        this.age = age;
        this.name = name;
        this.date = date;
        this.sprite = sprite;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGrandSlams() {
        return grandSlams;
    }

    public void setGrandSlams(String grandSlams) {
        this.grandSlams = grandSlams;
    }

    public String getSprite() {
        return sprite;
    }

    public void setSprite(String sprite) {
        this.sprite = sprite;
    }

    @Override
    public String toString() {
        return "Tenista{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", date='" + date + '\'' +
                ", age=" + age +
                ", img=" + sprite+
                ", grandSlams=" + grandSlams +
                '}';
    }
}
