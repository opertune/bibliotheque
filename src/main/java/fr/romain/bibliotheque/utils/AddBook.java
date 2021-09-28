package fr.romain.bibliotheque.utils;

import fr.romain.bibliotheque.entity.Book;

import java.sql.Connection;
import java.sql.Statement;

public class AddBook {
    // Members
    private ConnectionBDD dataBaseConnection = new ConnectionBDD();
    private Connection connectDB = dataBaseConnection.getConnection();
    private Book _book;

    // Constructor
    public AddBook(Book book){
        this._book = book;
        requestInsertBook();
    }

    // Methods
    // Insert book in database
    private void requestInsertBook(){
        String querry = "INSERT INTO book (id, title, author, year, pages) VALUES ('" + _book.get_id() + "','" +_book.get_title()+ "','"
                + _book.get_author() + "','" + _book.get_year() + "','" + _book.get_pages() + "')";

        try{
            Statement statement = connectDB.createStatement();
            statement.executeUpdate(querry);
        }catch (Exception e){
            e.printStackTrace();
        }
        closeDDB();
    }

    // Close database
    private void closeDDB(){
        try{
            connectDB.close();
            System.out.println("Connection closed");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
