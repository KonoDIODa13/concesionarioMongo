package application.controller;

import application.CRUD.CocheCRUD;
import application.domain.Coche;
import application.domain.Tipo;
import application.utils.AlertUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import javax.swing.*;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AppController implements Initializable {
    @FXML
    private ComboBox<Tipo> cbTipo;

    @FXML
    private TextField tfMatricula, tfModelo, tfMarca;

    @FXML
    private TableView<Coche> tvCoches;

    @FXML
    private TableColumn<Coche, String> tcMatricula, tcMarca, tcModelo, tcTipo;

    CocheCRUD crud;
    List<Coche> coches;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cargarTabla();
        cargarCB();

    }

    public AppController() {
        crud = new CocheCRUD();
    }


    @FXML
    void nuevoCoche(ActionEvent event) {
        tfMatricula.setText("");
        tfMarca.setText("");
        tfModelo.setText("");
        cargarCB();
    }

    @FXML
    void guardarCoche(ActionEvent event) {
        List<String> campos = new ArrayList<>();
        String matricula = tfMatricula.getText();
        String marca = tfMarca.getText();
        String modelo = tfModelo.getText();
        String tipo = cbTipo.getValue().toString();
        insertarCampo(campos, matricula);
        insertarCampo(campos, marca);
        insertarCampo(campos, modelo);
        insertarCampo(campos, tipo);
        crud.insertarCoche(campos);
        cargarTabla();
        nuevoCoche(event);
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
        int opcion = JOptionPane.showConfirmDialog(null,
                "¿Está seguro de que desea salir?", "Confirmación", JOptionPane.YES_NO_OPTION);
        if (opcion == JOptionPane.YES_OPTION) {
            crud.salir();
            System.exit(0); // CERRAR APLICACIÓN
        }
    }

    public void cargarTabla() {
        tvCoches.getItems().clear();
        coches = crud.getCoches();

        tvCoches.setItems(FXCollections.observableList(coches));

        tcMatricula.setCellValueFactory(new PropertyValueFactory<>("matricula"));
        tcMarca.setCellValueFactory(new PropertyValueFactory<>("marca"));
        tcModelo.setCellValueFactory(new PropertyValueFactory<>("modelo"));
        tcTipo.setCellValueFactory(new PropertyValueFactory<>("tipo"));
    }

    public void cargarCB() {
        cbTipo.getItems().clear();
        cbTipo.getItems().addAll(Tipo.values());
    }

    public void insertarCampo(List<String> campos, String campo) {
        campos.add(campo);
    }
}
