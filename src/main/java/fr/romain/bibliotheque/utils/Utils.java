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
                data.add(new Book(resultSet.getObject("img").toString() ,(int)resultSet.getObject("id"), resultSet.getObject("title").toString(), resultSet.getObject("author").toString(), (int)resultSet.getObject("year"), (int)resultSet.getObject("pages")));
            }
            // Add ObservableArrayList data in TableView
            tvDDB.setItems(data);

            // Close database connection
            connectDB.close();
            System.out.println("Connection closed");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    // Database Request
    public static void requestDB(String querry, ObservableList data, TableView tvDDB){
        Connection connectionDB = databaseConnection();

        try{
            // Execute querry
            Statement statement = connectionDB.createStatement();
            statement.executeUpdate(querry);

            // Clear TableView and update with new database
            data.clear();
            Utils.displayDatabaseInTableView(data ,tvDDB);

            // Close database connection
            connectionDB.close();
            System.out.println("Connection closed");
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    // Request image
    public static String requestImage(int ID){
        String imgName = "";
        Connection connectionDB = databaseConnection();
        try{
            // Execute querry
            Statement statement = connectionDB.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT img FROM book WHERE id = '" + ID +"';");

            while (resultSet.next()){
                imgName = (String) resultSet.getObject("img");
            }

            // Close database connection
            connectionDB.close();
            System.out.println("Connection closed");
        }catch (Exception e){
            e.printStackTrace();
        }
        return imgName;
    }
}
