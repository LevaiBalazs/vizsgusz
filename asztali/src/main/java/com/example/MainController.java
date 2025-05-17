package com.example;

import java.time.LocalDate;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class MainController {

    ArrayList<Restoration> resList = Storage.readRestorations();

    @FXML
    private TableColumn<Restoration, LocalDate> datumCol;

    @FXML
    private DatePicker datumPicker;

    @FXML
    private TableColumn<Restoration, Integer> idCol;

    @FXML
    private TableColumn<Restoration, String> megjegyzesCol;

    @FXML
    private TextField megjegyzesField;

    @FXML
    private TableColumn<Restoration, String> muveletCol;

    @FXML
    private TextField muveletField;

    @FXML
    private TableColumn<Restoration, Integer> paintingidCol;

    @FXML
    private TextField paintingidField;

    @FXML
    private TableView<Restoration> restorationTable;

    @FXML
    void initialize() {
        this.resList = Storage.readRestorations();

        datumCol.setCellValueFactory(new PropertyValueFactory<>("datum"));
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        megjegyzesCol.setCellValueFactory(new PropertyValueFactory<>("megjegyzes"));
        muveletCol.setCellValueFactory(new PropertyValueFactory<>("muvelet"));
        paintingidCol.setCellValueFactory(new PropertyValueFactory<>("paintingid"));

        restorationTable.getItems().addAll(this.resList);
    }

    @FXML
    void onClickAddButton(ActionEvent event) {
        System.out.println("Add button clicked");
        startAdd();
    }

    void startAdd() {
        Restoration res = new Restoration();
        res.setId(resList.size() + 1);
        res.setDatum(datumPicker.getValue());
        res.setMuvelet(muveletField.getText());
        res.setMegjegyzes(megjegyzesField.getText());
        res.setPaintingid(Integer.parseInt(paintingidField.getText()));
        this.resList.add(res);
        restorationTable.getItems().add(res);
        Storage.writeRestorations(this.resList);
        clearFields();
    }

    private void clearFields() {
        datumPicker.setValue(null);
        muveletField.setText("");
        megjegyzesField.setText("");
        paintingidField.setText("");
    }

}
