package fr.romain.bibliotheque.utils;

import fr.romain.bibliotheque.entity.Book;
import javafx.scene.control.TableView;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class DelBook {
    // Members
    private final Connection connectDB = Utils.databaseConnection();
    private Book selectedBook;
    private TableView tvDDB;

    // Constructor
    public DelBook(Book selectedBook, TableView tvDDB) {
        this.selectedBook = selectedBook;
        this.tvDDB = tvDDB;
        deleteBook();
    }

    // Delete book
    private void deleteBook(){
        // DELETE book WHERE book data == tableView selectedBook data
        String querry = "DELETE FROM book WHERE title = '" + selectedBook.getTitle() +"' AND author = '" + selectedBook.getAuthor() + "' AND year = '" + selectedBook.getYear() +"' AND pages = '" + selectedBook.getPages() + "';" ;
        try{
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
