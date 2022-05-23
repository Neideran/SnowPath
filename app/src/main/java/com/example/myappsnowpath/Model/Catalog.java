package com.example.myappsnowpath.Model;

public class Catalog {
    String categoryName;
    String image;
    String parent;

    public Catalog () {}

    public Catalog(String categoryName, String image, String parent) {
        this.categoryName = categoryName;
        this.image = image;
        this.parent = parent;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public String getImage() {
        return image;
    }

    public String getParent() {
        return parent;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

}
