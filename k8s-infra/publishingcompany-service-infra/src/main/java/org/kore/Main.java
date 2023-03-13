package org.kore;

import software.constructs.Construct;

import org.cdk8s.App;
import org.cdk8s.Chart;
import org.cdk8s.ChartProps;
import org.kore.k8s.services.infra.WebServiceConstruct;

public class Main extends Chart 
{

    public Main(final Construct scope, final String id) {
        this(scope, id, null);
    }

    public Main(final Construct scope, final String id, final ChartProps options) {
        super(scope, id, options);

        new QuarkusComponent(this, "app");
    }

    public static void main(String[] args) {
        final App app = new App();
        Main main = new Main(app, "publishingcompany-service");
        app.synth();
    }
}
