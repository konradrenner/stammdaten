package org.kore.blueprint.publishingcompany.boundary.fx;

import com.gluonhq.charm.glisten.application.AppManager;
import com.gluonhq.charm.glisten.control.AppBar;
import com.gluonhq.charm.glisten.control.CardPane;
import com.gluonhq.charm.glisten.mvc.View;
import com.gluonhq.charm.glisten.visual.MaterialDesignIcon;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class PrimaryPresenter {

    @FXML
    private View primary;

    @FXML
    private CardPane<HBox> authors;
     
    public void initialize() {
        primary.showingProperty().addListener((obs, oldValue, newValue) -> {
            if (newValue) {
                AppBar appBar = AppManager.getInstance().getAppBar();
                appBar.setNavIcon(MaterialDesignIcon.MENU.button(e -> 
                        AppManager.getInstance().getDrawer().open()));
                appBar.setTitleText("Galactic Authors");
                appBar.getActionItems().add(MaterialDesignIcon.SEARCH.button(e -> 
                        System.out.println("Search")));
            }
        });
        
        initCards();
        
    }
    
    void initCards(){
        authors.getItems().clear();
        
        Label firstname = new Label("Luke");
        Label lastname = new Label("Skywalker");
        
        HBox box = new HBox(firstname, lastname);
        
        authors.getItems().add(box);
    }
}
