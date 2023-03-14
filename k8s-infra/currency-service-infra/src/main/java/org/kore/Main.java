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

        final String image = System.getProperty("containerImage", "kore/currency-service:0.1.1");
        final String name = "currency-service";
        final String pathPrefix = "/conversion-rates";

        WebServiceConstruct.newInstance(this, "app")
                .withName(name)
                .withContainerImage(image)
                .withIngressRuleHttpPathPrefix(pathPrefix)
                .withContainerPortNumber(8000)
                .withReadinessProbePath("/q/health")
                .withLivenessProbePath("/q/health")
                .addToChart();
    }

    public static void main(String[] args) {
        final App app = new App();
        Main main = new Main(app, "currency-service");
        app.synth();
    }
}
