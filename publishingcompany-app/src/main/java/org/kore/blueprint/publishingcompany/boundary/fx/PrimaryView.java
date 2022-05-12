package org.kore.blueprint.publishingcompany.boundary.fx;

import com.airhacks.afterburner.views.FXMLView;
import java.util.ResourceBundle;

public class PrimaryView extends FXMLView  {

    public PrimaryView(ResourceBundle bundle) {
        // just required because bundle ist not located in default scheme: packagename.ExampleView -> packagename.example
        this.bundle = bundle;
    }
}
