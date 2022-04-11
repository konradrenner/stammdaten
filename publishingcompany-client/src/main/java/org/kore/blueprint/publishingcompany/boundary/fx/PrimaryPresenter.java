package org.kore.blueprint.publishingcompany.boundary.fx;

import com.gluonhq.charm.glisten.application.AppManager;
import com.gluonhq.charm.glisten.control.AppBar;
import com.gluonhq.charm.glisten.control.CardPane;
import com.gluonhq.charm.glisten.mvc.View;
import com.gluonhq.charm.glisten.visual.MaterialDesignIcon;
import java.util.function.Consumer;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javax.inject.Inject;
import org.kore.blueprint.publishingcompany.Event;
import org.kore.blueprint.publishingcompany.boundary.LocalCacheAuthorRepository;
import org.kore.blueprint.publishingcompany.entity.Author;

public class PrimaryPresenter {

    @FXML
    View primary;

    @FXML
    CardPane<HBox> authors;
    
    @Inject
    LocalCacheAuthorRepository repo;    
    
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
        final ObservableList<HBox> items = authors.getItems();
        items.clear();
        
        Consumer<Event<Author>> eventHandler = event -> {
            switch(event.type()){
                case NEW:
                    items.add(createAuthorBox(event.entity()));
                    break;
            }
        };
        
        repo.addElementObserver(eventHandler);
    }
    
    HBox createAuthorBox(Author author){
        Label firstname = new Label(author.getName().firstname());
        Label lastname = new Label(author.getName().lastname());
        
        return new HBox(firstname, lastname);
    }
}
