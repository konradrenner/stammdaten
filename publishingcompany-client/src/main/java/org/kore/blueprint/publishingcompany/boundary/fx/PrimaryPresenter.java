package org.kore.blueprint.publishingcompany.boundary.fx;

import com.gluonhq.charm.glisten.application.AppManager;
import com.gluonhq.charm.glisten.control.AppBar;
import com.gluonhq.charm.glisten.control.CardPane;
import com.gluonhq.charm.glisten.mvc.View;
import com.gluonhq.charm.glisten.visual.MaterialDesignIcon;
import java.time.ZoneId;
import java.util.function.Consumer;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javax.inject.Inject;
import org.kore.blueprint.publishingcompany.Event;
import org.kore.blueprint.publishingcompany.boundary.LocalCacheAuthorRepository;
import org.kore.blueprint.publishingcompany.entity.Author;

public class PrimaryPresenter {

    @FXML
    View primary;

    @FXML
    CardPane<VBox> authors;
    
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
        
        
        final ObservableList<VBox> items = authors.getItems();
        items.clear();
        
        Consumer<Event<Author>> eventHandler = event -> {
                Platform.runLater(()->{
                    switch(event.type()){
                    case NEW:
                        items.add(createAuthorBox(event));
                        break;
                    case UPDATE:
                        updateAuthorBox(event, items);
                        break;
                }
            });
        };
        
        repo.addElementObserver(eventHandler);
    }
    
    VBox createAuthorBox(Event<Author> event){
        Author author = event.entity();
        Label firstname = new Label(author.getName().firstname());
        Label lastname = new Label(author.getName().lastname());
        
        VBox vBox = new VBox(new HBox(firstname, lastname), new Label(event.modification().atZone(ZoneId.systemDefault()).toString()));
        vBox.setId(createContainerId(author));
        return vBox;
    }

    private static String createContainerId(Author author) {
        return author.getName().firstname()+author.getName().lastname();
    }
    
    void updateAuthorBox(Event<Author> updatedAuthor, ObservableList<VBox> items){
        int i=0;
        boolean exists = false;
        for(;i<items.size();i++){
            if(items.get(i).getId().equals(createContainerId(updatedAuthor.entity()))){
                exists =true;
                break;
            }
        }
        if(exists){
            items.remove(i);
        }
        items.add(createAuthorBox(updatedAuthor));
    }
}
