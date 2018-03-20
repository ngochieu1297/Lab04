package com.example.admin.lab04;

import java.io.Serializable;

/**
 * Created by Admin on 3/20/2018.
 */

public class Book implements Serializable {
    private int id;
    private String BookName;
    private int page;
    private float price;
    private String description;

    public Book() {
    }

    public Book(int id, String bookName, int page, float price, String description) {
        this.id = id;
        BookName = bookName;
        this.page = page;
        this.price = price;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBookName() {
        return BookName;
    }

    public void setBookName(String bookName) {
        BookName = bookName;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return BookName;
    }
}
