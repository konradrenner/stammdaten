package org.kore.blueprint.publishingcompany;

import org.kore.blueprint.publishingcompany.boundary.fx.PrimaryView;
import com.gluonhq.charm.glisten.application.AppManager;
import com.gluonhq.charm.glisten.visual.Swatch;
import com.gluonhq.charm.glisten.mvc.View;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import static com.gluonhq.charm.glisten.application.AppManager.HOME_VIEW;
import java.util.Locale;
import java.util.ResourceBundle;

public class PublishingcompanyMain extends Application {

    public static final String PRIMARY_VIEW = HOME_VIEW;
    private static final ResourceBundle APP_BUNDLE = ResourceBundle.getBundle("messages", Locale.getDefault());

    private final AppManager appManager = AppManager.initialize(this::postInit);

    @Override
    public void init() {
        appManager.addViewFactory(PRIMARY_VIEW, () -> (View) new PrimaryView(APP_BUNDLE).getView());

        DrawerManager.buildDrawer(appManager, APP_BUNDLE);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        appManager.start(primaryStage);
    }

    private void postInit(Scene scene) {
        Swatch.BLUE.assignTo(scene);

        scene.getStylesheets().add(PublishingcompanyMain.class.getResource("style.css").toExternalForm());
        ((Stage) scene.getWindow()).getIcons().add(new Image(PublishingcompanyMain.class.getResourceAsStream("/icon.png")));
    }

    public static void main(String args[]) {
        launch(args);
    }
}
