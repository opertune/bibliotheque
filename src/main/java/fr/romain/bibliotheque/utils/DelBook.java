package fr.romain.bibliotheque.utils;

import fr.romain.bibliotheque.entity.Book;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;

import java.sql.Connection;
import java.sql.Statement;

public class DelBook {
    // Members
    private final Connection connectDB = Utils.databaseConnection();
    private Book selectedBook;
    private TableView tvDDB;
    private ObservableList data;

    // Constructor
    public DelBook(Book selectedBook, TableView tvDDB, ObservableList data) {
        this.selectedBook = selectedBook;
        this.tvDDB = tvDDB;
        this.data = data;
        deleteBook();
    }

    // Delete book
    private void deleteBook(){
        // DELETE book WHERE book data == tableView selectedBook data
        String querry = "DELETE FROM book WHERE title = '" + selectedBook.getTitle() +"' AND author = '" + selectedBook.getAuthor() + "' AND year = '" + selectedBook.getYear() +"' AND pages = '" + selectedBook.getPages() + "';" ;
        try{
            // Execute querry
            Statement statement = connectDB.createStatement();
            statement.executeUpdate(querry);

            // Close database connection
            connectDB.close();
            System.out.println("Connection closed");

            // Clear TableView and update with new database
            data.clear();
            Utils.displayDatabaseInTableView(data ,tvDDB);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
