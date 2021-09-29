package fr.romain.bibliotheque.utils;

import fr.romain.bibliotheque.entity.Book;

import java.sql.Connection;
import java.sql.Statement;

public class AddBook {
    // Members
    private final Connection connectDB = Utils.databaseConnection();
    private final Book _book;

    // Constructor
    public AddBook(Book book){
        this._book = book;
        requestInsertBook();
    }

    // Methods
    // Insert book in database
    private void requestInsertBook(){
        String querry = "INSERT INTO book (title, author, year, pages) VALUES ('" +_book.getTitle()+ "','"
                + _book.getAuthor() + "','" + _book.getYear() + "','" + _book.getPages() + "')";
        try{
            // Execute querry
            Statement statement = connectDB.createStatement();
            statement.executeUpdate(querry);

            // Close database connection
            connectDB.close();
            System.out.println("Connection closed");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
