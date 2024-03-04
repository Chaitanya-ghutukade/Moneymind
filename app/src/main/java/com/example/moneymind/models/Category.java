package com.example.moneymind.models;

public class Category {
    private  String categoryName;
    private  int CategoryImage;

    private  int categorycolor;

    public Category(String categoryName, int categoryImage, int categorycolor) {
        this.categoryName = categoryName;
        CategoryImage = categoryImage;
        this.categorycolor = categorycolor;
    }

    public Category() {
    }

    public int getCategorycolor() {
        return categorycolor;
    }

    public void setCategorycolor(int categorycolor) {
        this.categorycolor = categorycolor;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public int getCategoryImage() {
        return CategoryImage;
    }

    public void setCategoryImage(int categoryImage) {
        CategoryImage = categoryImage;
    }
}
