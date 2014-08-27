package org.kore.stammdaten.lager.fx;

import java.net.URL;
import java.util.ArrayList;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import org.kore.stammdaten.lager.fx.rest.VersandkostenClient;

public class FXMLController implements Initializable {
    
    @FXML
    private Button ladeDaten;

    @FXML
    private ListView<VersandkostenModel> kosten;

    @FXML
    private TextField land;

    @FXML
    private TextField betrag;

    @FXML
    private TextField freibetrag;

    private ObservableList<VersandkostenModel> daten;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        daten = FXCollections.observableArrayList();
        kosten.setItems(daten);
        kosten.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        kosten.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {

            land.setText(newValue.getLand());
            betrag.setText(newValue.getBetrag() + " " + newValue.getWaehrung());
            freibetrag.setText(newValue.getFreikosten() == null ? "" : newValue.getFreikosten() + " " + newValue.getWaehrung());
        });
    }

    @FXML
    public void callWebService() {
        ArrayList all = new VersandkostenClient().getAll(ArrayList.class);

        daten.clear();
        for (Object o : all) {
            Map map = (Map) o;
            System.out.println("Akutelles Item:" + o);
            VersandkostenModel model = new VersandkostenModel();
            model.setLand(map.get("land").toString());
            model.setBetrag(map.get("betrag").toString());
            model.setWaehrung(map.get("waehrung").toString());

            if (map.get("freibetrag") != null) {
                model.setFreikosten(map.get("freibetrag").toString());
            }

            daten.add(model);
        }
    }

}
