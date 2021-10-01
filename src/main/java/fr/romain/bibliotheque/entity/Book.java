package fr.romain.bibliotheque.entity;

import javafx.scene.image.Image;

public class Book {
    // Members
    private String img;
    private int id;
    private String title;
    private String author;
    private int year;
    private int pages;

    // Constructor
    public Book(String img, int id, String title, String author, int year, int pages){
        this.img = img;
        this.id = id;
        this.title = title;
        this.author = author;
        this.year = year;
        this.pages = pages;
    }

    // Getter & Setter
    public String getTitle() {
        return this.title;
    }

    public void set_title(String _title) {
        this.title = _title;
    }

    public String getAuthor() {
        return this.author;
    }

    public void set_author(String _author) {
        this.author = _author;
    }

    public int getYear() {
        return this.year;
    }

    public void set_year(int _year) {
        this.year = _year;
    }

    public int getPages() {
        return this.pages;
    }

    public void set_pages(int _pages) {
        this.pages = _pages;
    }

    public int getId() { return this.id; }

    public void set_id(int id) { this.id=id; }
}
