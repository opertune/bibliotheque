package fr.romain.bibliotheque.utils;

import fr.romain.bibliotheque.entity.Book;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Utils {
    // Create database connection
    public static Connection databaseConnection(){
        ConnectionBDD dataBaseConnection = new ConnectionBDD();
        return dataBaseConnection.getConnection();
    }

    // Display all data from database in tableview
    public static void displayDatabaseInTableView(ObservableList data, TableView tvDDB){
        Connection connectDB = databaseConnection();

        try{
            Statement statement = connectDB.createStatement();
            // Querry all data from database
            ResultSet resultSet = statement.executeQuery("SELECT * FROM book");

            while (resultSet.next()){
                // Add all book in database in ObservableArrayList data
                data.add(new Book(resultSet.getObject("title").toString(), resultSet.getObject("author").toString(), (int)resultSet.getObject("year"), (int)resultSet.getObject("pages")));
            }
            // Add ObservableArrayList data in TableView
            tvDDB.setItems(data);

            // Close database connection
            connectDB.close();
            System.out.println("Database closed");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
