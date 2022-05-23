package com.example.myappsnowpath.Model;

public class Item {
    private String name, color, size, brand, sex, description, season, category, image;
    private int price, id;

    public Item() { }

    public Item(String image, int id, String name, String brand, String category, int price,
                String size, String season, String color, String sex, String description) {
        this.id = id;
        this.name = name;
        this.color = color;
        this.size = size;
        this.brand = brand;
        this.sex = sex;
        this.description = description;
        this.season = season;
        this.category = category;
        this.image = image;
        this.price = price;
    }

    public String getName(){
        return name;
    }

    public String getColor() {
        return color;
    }

    public String getSize() {
        return size;
    }

    public String getBrand() {
        return brand;
    }

    public String getSex() {
        return sex;
    }

    public String getDescription() {
        return description;
    }

    public int getPrice() {
        return price;
    }

    public String getSeason() {
        return season;
    }

    public int getId() {
        return id;
    }

    public String getCategory() {
        return category;
    }

    public String getImage() {
        return image;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setSeason(String season) {
        this.season = season;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setId(int id) {
        this.id = id;
    }
}
