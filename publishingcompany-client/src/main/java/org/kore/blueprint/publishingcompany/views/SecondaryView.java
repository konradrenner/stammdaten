package org.kore.blueprint.publishingcompany.views;

import com.gluonhq.charm.glisten.mvc.View;
import java.io.IOException;
import javafx.fxml.FXMLLoader;

public class SecondaryView {
    
    public View getView() {
        try {
            View view = FXMLLoader.load(SecondaryView.class.getResource("secondary.fxml"));
            return view;
        } catch (IOException e) {
            System.out.println("IOException: " + e);
            return new View();
        }
    }
}
