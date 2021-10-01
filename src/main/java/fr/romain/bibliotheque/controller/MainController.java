package fr.romain.bibliotheque.controller;

import com.mysql.cj.x.protobuf.MysqlxCrud;
import fr.romain.bibliotheque.entity.Book;
import fr.romain.bibliotheque.utils.Constant;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ResourceBundle;

public class MainController implements Initializable, Constant {
    private ObservableList<Book> data = FXCollections.observableArrayList();
    private String imgPath = "";

    @FXML
    private TextField txtTitre, txtAuteur, txtAnnee, txtPages, txtImagePath;

    @FXML
    private Button btnAjouter, btnSupprimer, btnAddImage;

    @FXML
    private Pane pane;

    @FXML
    private TableView<Book> tvDDB;

    @FXML
    private TableColumn<Book, String> tcTitre, tcAuteur;

    @FXML
    private TableColumn<Book, Integer> tcAnnee, tcPages;

    @FXML
    private ImageView ivImage;

    // Button add new book
    @FXML
    void clickBtnAjouter(ActionEvent event) {
        try {
            // If book has an image -> add in project directory
            if(imgPath != ""){
                BufferedImage image = ImageIO.read(new File(imgPath));
                // Add image in project folder
                ImageIO.write(image,"jpg", new File(IMAGEPATH + imgPath.substring(imgPath.lastIndexOf("\\")+1)));
            }
            // Init new Book
            Book book = new Book(imgPath,tvDDB.getColumns().size()+1 ,txtTitre.getText(), txtAuteur.getText(), Integer.parseInt(txtAnnee.getText()), Integer.parseInt(txtPages.getText()));

            // Add new book in database
            Utils.requestDB("INSERT INTO book (title, author, year, pages, img) VALUES ('" +book.getTitle()+ "','"
                    + book.getAuthor() + "','" + book.getYear() + "','" + book.getPages() + "','"+ IMAGEPATH+imgPath.substring(imgPath.lastIndexOf("\\")+1) +"')", data, tvDDB);

            // Clear textField
            txtTitre.clear();
            txtAuteur.clear();
            txtAnnee.clear();
            txtPages.clear();
            txtImagePath.clear();
            ivImage.setImage(null);
        }catch (Exception e){
            System.out.println("TextField Empty !");
        }
    }

    // Button delete selected book
    @FXML
    void clickBtnSupprimer(ActionEvent event) {
        // Delete selected Book
        try {
            Files.delete(Paths.get(Utils.requestImage(tvDDB.getSelectionModel().getSelectedItem().getId())));
            Utils.requestDB("DELETE FROM book WHERE id = '" + tvDDB.getSelectionModel().getSelectedItem().getId() + "';", data, tvDDB);
            ivImage.setImage(null);
        }catch (Exception e){
            System.out.println("No book selected");
        }
    }

    // Add an image
    @FXML
    void clickBtnAddImage(ActionEvent event) {
        // Create and open folder explorer
        FileChooser fc = new FileChooser();
        imgPath = fc.showOpenDialog(pane.getScene().getWindow()).toString();

        // If user chose one file -> init imageview with file and textfield
        if(imgPath != null){
            txtImagePath.setText(imgPath);
            ivImage.setImage(new Image(new File(imgPath).toURI().toString()));
        }
    }

    // Click on tableview
    @FXML
    void tvClick(MouseEvent event) {
        // Display image
        ivImage.setImage(new Image("file:" + Utils.requestImage(tvDDB.getSelectionModel().getSelectedItem().getId())));
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