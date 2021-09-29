module fr.romain.bibliotheque {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires mysql.connector.java;
    requires java.sql;

    opens fr.romain.bibliotheque to javafx.fxml;
    exports fr.romain.bibliotheque;
    exports fr.romain.bibliotheque.controller;
    opens fr.romain.bibliotheque.controller to javafx.fxml;
    opens fr.romain.bibliotheque.entity to javafx.base;
}