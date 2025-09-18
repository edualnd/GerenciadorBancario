package main.bancaria.gui;



import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class MainController {

    @FXML
    private TableView<ObservableList<String>> tableAnalise;
    @FXML
    private TableColumn<ObservableList<String>, String> numeroCol;
    @FXML
    private TableColumn<ObservableList<String>, String> titularCol;
    @FXML
    private TableColumn<ObservableList<String>, String> saldoCol;
    @FXML
    private TableColumn<ObservableList<String>, String> tarifaCol;

    @FXML
    public void initialize() {


        numeroCol.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().get(0)));
        titularCol.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().get(1)));
        saldoCol.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().get(2)));
        tarifaCol.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().get(3)));

        ObservableList<ObservableList<String>> data = FXCollections.observableArrayList();
        data.add(FXCollections.observableArrayList("1", "Duda", "1500", "5"));
        data.add(FXCollections.observableArrayList("2", "Carlos", "2500", "3"));
        data.add(FXCollections.observableArrayList("1", "Duda", "1500", "5"));
        data.add(FXCollections.observableArrayList("2", "Carlos", "2500", "3"));
        data.add(FXCollections.observableArrayList("1", "Duda", "1500", "5"));
        data.add(FXCollections.observableArrayList("2", "Carlos", "2500", "3"));

        tableAnalise.setItems(data);
    }



}
