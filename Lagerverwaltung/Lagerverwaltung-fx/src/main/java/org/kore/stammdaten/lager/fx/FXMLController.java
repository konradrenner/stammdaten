package org.kore.stammdaten.lager.fx;

import java.math.BigDecimal;
import java.net.URL;
import java.util.ArrayList;
import java.util.Currency;
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
import org.kore.runtime.currency.Money;
import org.kore.stammdaten.lager.fx.rest.VersandkostenClient;
import org.kore.stammdaten.lager.rest.Versandkosten;

public class FXMLController implements Initializable {
    
    @FXML
    private Button ladeDaten;

    @FXML
    private Button uebernehmen;

    @FXML
    private Button loeschen;

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
        VersandkostenClient versandkostenClient = new VersandkostenClient();
        ArrayList all = versandkostenClient.getAll(ArrayList.class);

        daten.clear();
        for (Object o : all) {
            Map map = (Map) o;
            VersandkostenModel model = new VersandkostenModel();
            model.setLand(map.get("land").toString());
            model.setBetrag(map.get("betrag").toString());
            model.setWaehrung(map.get("waehrung").toString());

            if (map.get("freibetrag") != null) {
                model.setFreikosten(map.get("freibetrag").toString());
            }

            daten.add(model);
        }

        versandkostenClient.close();
    }

    @FXML
    public void callUebernehmenWebService() {
        String sland = this.land.getText();
        if (sland != null && sland.trim().length() > 0 && this.betrag.getText() != null && this.betrag.getText().trim().length() > 0) {
            String[] split = this.betrag.getText().split(" ");
            String swaehrung = split[1];
            String sbetrag = split[0];
            Versandkosten.Builder vkostenBuilder = new Versandkosten.Builder(sland, new Money(new BigDecimal(sbetrag), Currency.getInstance(swaehrung)));
            if (this.freibetrag.getText() != null && this.freibetrag.getText().trim().length() > 0) {
                vkostenBuilder.withFreibetrag(new BigDecimal(this.freibetrag.getText().split(" ")[0]));
            }


            VersandkostenClient versandkostenClient = new VersandkostenClient();
            versandkostenClient.updateDetail(sland, vkostenBuilder.build());
            versandkostenClient.close();

            callWebService();
        }
    }

    @FXML
    public void callLoeschenWebService() {
        String sland = this.land.getText();

        if (sland != null && sland.trim().length() > 0) {

            VersandkostenClient versandkostenClient = new VersandkostenClient();
            versandkostenClient.deleteDetail(sland);
            versandkostenClient.close();

            callWebService();
        }
    }
}
