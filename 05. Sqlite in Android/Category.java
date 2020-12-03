package com.jabarian.adabazi;

public class Category {
    public int Id;
    public String Title;
    public int Point;

    public Category(int id, String title, int point) {
        Id = id;
        Title = title;
        Point = point;
    }

    public Category(String title, int point) {
        Title = title;
        Point = point;
    }

    public Category() {

    }
}
