package fr.romain.bibliotheque.controller;

import fr.romain.bibliotheque.entity.Book;
import fr.romain.bibliotheque.utils.AddBook;
import fr.romain.bibliotheque.utils.ConnectionBDD;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    @FXML
    private TextField txtTitre, txtAuteur, txtAnnee, txtPages;

    @FXML
    private Button btnAjouter, btnSupprimer;

    @FXML
    private Pane pane;

    @FXML
    private TableView<?> tvDDB;

    @FXML
    private TableColumn<?, ?> tvDDBTitre, tvDDBAuteur, tvDDBAnnee, tvDDBPages;

    @FXML
    void clickBtnAjouter(ActionEvent event) {
        // Create and add new book
        new AddBook(new Book(tvDDB.getItems().size(), txtTitre.getText(), txtAuteur.getText(), Integer.parseInt(txtAnnee.getText()), Integer.parseInt(txtPages.getText())));
    }

    @FXML
    void clickBtnSupprimer(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Remove focus on first text field
        Platform.runLater(() -> pane.requestFocus());

        // Display all data from database in tableview
        // Create database connection
        ConnectionBDD dataBaseConnection = new ConnectionBDD();
        Connection connectDB = dataBaseConnection.getConnection();

        try{
            Statement statement = connectDB.createStatement();
            // Querry all data from database
            ResultSet resultSet = statement.executeQuery("SELECT * FROM book");
            ResultSetMetaData metaData = resultSet.getMetaData();


            while (resultSet.next()){
                System.out.println(resultSet.getObject("author"));
                // TODO test avec plusieurs elements dans la  table

            }

            // Close database connection
            connectDB.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}