package application.controller;

import application.CRUD.CocheCRUD;
import application.domain.Coche;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class AppController {
    @FXML
    private ComboBox<?> cbTipo;

    @FXML
    private TextField tfMatricula, TfModelo, tfMarca;

    @FXML
    private TableView<?> tvCoches;

    CocheCRUD crud;

    public AppController() {
        crud = new CocheCRUD();
    }

    @FXML
    void nuevoCoche(ActionEvent event) {

    }

    @FXML
    void guardarCoche(ActionEvent event) {

    }

    @FXML
    void modificarCoche(ActionEvent event) {

    }

    @FXML
    void borrarCoche(ActionEvent event) {

    }

    @FXML
    void getCoche(MouseEvent event) {

    }

    @FXML
    void salirApp(ActionEvent event) {

    }
}
