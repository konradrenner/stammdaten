package org.kore;

import imports.k8s.EnvVar;
import java.util.List;
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

        final String image = System.getProperty("containerImage", "kore/currency-service:0.2.0");
        final String name = "currency-service";
        final String pathPrefix = "/conversion-rates";

        EnvVar otelEndpoint = new EnvVar.Builder()
                .name("OTEL_EXPORTER_OTLP_ENDPOINT")
                .value("http://my-release-signoz-otel-collector.platform.svc.cluster.local:4317")
                .build();

        EnvVar otelServiceName = new EnvVar.Builder()
                .name("OTEL_RESOURCE_ATTRIBUTES")
                .value("service.name=currency-service")
                .build();

        WebServiceConstruct.newInstance(this, "app")
                .withName(name)
                .withContainerImage(image)
                .withIngressRuleHttpPathPrefix(pathPrefix)
                .withContainerPortNumber(8000)
                .withReadinessProbePath("/q/health")
                .withLivenessProbePath("/q/health")
                .withEnvVars(List.of(otelEndpoint, otelServiceName))
                .addToChart();
    }

    public static void main(String[] args) {
        final App app = new App();
        Main main = new Main(app, "currency-service");
        app.synth();
    }
}
