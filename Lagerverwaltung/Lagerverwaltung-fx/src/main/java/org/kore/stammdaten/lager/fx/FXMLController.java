package org.kore.stammdaten.lager.fx;

import java.net.URL;
import java.util.Collection;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import org.kore.stammdaten.lager.fx.rest.VersandkostenClient;
import org.kore.stammdaten.lager.rest.Versandkosten;

public class FXMLController implements Initializable {
    
    @FXML
    private Button ladeDaten;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // nothing at the moment
    }

    @FXML
    public void callWebService() {
        System.out.println("Hallo");
        VersandkostenClient versandkostenClient = new VersandkostenClient();

        Versandkosten detail = versandkostenClient.getDetail(Versandkosten.class, "AT");

        System.out.println("detail:" + detail);

        Collection all = versandkostenClient.getAll(Collection.class);

        System.out.println("all:" + all);

        versandkostenClient.close();
    }
}
