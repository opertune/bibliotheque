package fr.romain.bibliotheque.controller;

import fr.romain.bibliotheque.entity.Book;
import fr.romain.bibliotheque.utils.AddBook;
import fr.romain.bibliotheque.utils.ConnectionBDD;
import fr.romain.bibliotheque.utils.DelBook;
import fr.romain.bibliotheque.utils.Utils;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    private ObservableList<Book> data = FXCollections.observableArrayList();

    @FXML
    private TextField txtTitre, txtAuteur, txtAnnee, txtPages;

    @FXML
    private Button btnAjouter, btnSupprimer;

    @FXML
    private Pane pane;

    @FXML
    private TableView<Book> tvDDB;

    @FXML
    private TableColumn<Book, String> tcTitre, tcAuteur;

    @FXML
    private TableColumn<Book, Integer> tcAnnee, tcPages;

    // Button add new book
    @FXML
    void clickBtnAjouter(ActionEvent event) {
        // Add new book in ObservableArrayList data
        data.add(new Book(txtTitre.getText(), txtAuteur.getText(), Integer.parseInt(txtAnnee.getText()), Integer.parseInt(txtPages.getText())));

        // Add ObservableArrayList data with new book in TableView
        tvDDB.setItems(data);

        // Create and add new book
        new AddBook(new Book(txtTitre.getText(), txtAuteur.getText(), Integer.parseInt(txtAnnee.getText()), Integer.parseInt(txtPages.getText())));

        // Clear textField
        txtTitre.clear();
        txtAuteur.clear();
        txtAnnee.clear();
        txtPages.clear();
    }

    // Button delete selected book
    @FXML
    void clickBtnSupprimer(ActionEvent event) {
        // Delete selected Book
        new DelBook(tvDDB.getSelectionModel().getSelectedItem(), tvDDB);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Remove focus on first text field
        Platform.runLater(() -> pane.requestFocus());

        // Add property value for each column
        tcTitre.setCellValueFactory(new PropertyValueFactory<>("title"));
        tcAuteur.setCellValueFactory(new PropertyValueFactory<>("author"));
        tcAnnee.setCellValueFactory(new PropertyValueFactory<>("year"));
        tcPages.setCellValueFactory(new PropertyValueFactory<>("pages"));

        // Display database in TableView
        Utils.displayDatabaseInTableView(data, tvDDB);
    }
}