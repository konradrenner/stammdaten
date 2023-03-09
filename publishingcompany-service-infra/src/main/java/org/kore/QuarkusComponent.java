/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.kore;

import imports.k8s.Container;
import imports.k8s.ContainerPort;
import imports.k8s.DeploymentSpec;
import imports.k8s.EnvVar;
import imports.k8s.HttpGetAction;
import imports.k8s.HttpIngressPath;
import imports.k8s.HttpIngressRuleValue;
import imports.k8s.IngressBackend;
import imports.k8s.IngressRule;
import imports.k8s.IngressServiceBackend;
import imports.k8s.IngressSpec;
import imports.k8s.IntOrString;
import imports.k8s.KubeDeployment;
import imports.k8s.KubeDeploymentProps;
import imports.k8s.KubeIngress;
import imports.k8s.KubeIngressProps;
import imports.k8s.KubeService;
import imports.k8s.KubeServiceProps;
import imports.k8s.LabelSelector;
import imports.k8s.ObjectMeta;
import imports.k8s.PodSpec;
import imports.k8s.PodTemplateSpec;
import imports.k8s.Probe;
import imports.k8s.ServiceBackendPort;
import imports.k8s.ServicePort;
import imports.k8s.ServiceSpec;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import software.constructs.Construct;

/**
 *
 * @author koni
 */
public class QuarkusComponent extends Construct{
    
    
    public QuarkusComponent(Construct scope, String id) {
        super(scope, id);

        // TODO fancy idea to set such props
        final int portNumber = 80;
        final int containerPortNumber = 8080;
        final int replicas = 2;
        final String image = "kore/publishingcompany-service:1.0-SNAPSHOT";
        final String name = "publishingcompany-service";
        

        // Defining a LoadBalancer Service
//        final String serviceType = "LoadBalancer";
        final Map<String, String> selector = new HashMap<>();
        selector.put("app", name);
        final List<ServicePort> servicePorts = new ArrayList<>();
        final ServicePort servicePort = new ServicePort.Builder()
            .port(portNumber)
            .targetPort(IntOrString.fromNumber(containerPortNumber))
            .build();
        servicePorts.add(servicePort);
        final ServiceSpec serviceSpec = new ServiceSpec.Builder()
//            .type(serviceType)
            .selector(selector)
            .ports(servicePorts)
            .build();
        final KubeServiceProps serviceProps = new KubeServiceProps.Builder()
            .spec(serviceSpec)
            .build();

        KubeService kubeService = new KubeService(this, "service", serviceProps);

        // Defining a Deployment
        HttpGetAction livenessAction = new HttpGetAction.Builder()
                .port(IntOrString.fromNumber(Integer.valueOf(containerPortNumber)))
                .path("/q/health/live")
                .build();
        
        Probe livenessProbe = new Probe.Builder()
                .httpGet(livenessAction)
                .periodSeconds(Integer.valueOf(5))
                .initialDelaySeconds(3)
                .build();
        
        HttpGetAction readynessAction = new HttpGetAction.Builder()
                .port(IntOrString.fromNumber(Integer.valueOf(containerPortNumber)))
                .path("/q/health/ready")
                .build();
        
        Probe readyProbe = new Probe.Builder()
                .httpGet(readynessAction)
                .periodSeconds(Integer.valueOf(5))
                .initialDelaySeconds(3)
                .build();

        EnvVar otelEndpoint = new EnvVar.Builder()
                .name("QUARKUS_OPENTELEMETRY_TRACER_EXPORTER_OTLP_ENDPOINT")
                .value("http://my-release-signoz-otel-collector.platform.svc.cluster.local:4317")
                .build();

        EnvVar currencyServiceEndpoint = new EnvVar.Builder()
                .name("QUARKUS_REST-CLIENT_CURRENCY-SERVICE-API_URL")
                .value("http://currency-service.default.svc.cluster.local")
                .build();

        
        final LabelSelector labelSelector = new LabelSelector.Builder().matchLabels(selector).build();
        final ObjectMeta objectMeta = new ObjectMeta.Builder().labels(selector).build();
        final List<ContainerPort> containerPorts = new ArrayList<>();
        final ContainerPort containerPort = new ContainerPort.Builder()
            .containerPort(containerPortNumber)
            .build();
        containerPorts.add(containerPort);
        final List<Container> containers = new ArrayList<>();
        final Container container = new Container.Builder()
            .name(name)
            .image(image)
            .ports(containerPorts)
            .livenessProbe(livenessProbe)
                .readinessProbe(readyProbe)
                .env(List.of(otelEndpoint, currencyServiceEndpoint))
                .build();
        containers.add(container);
        final PodSpec podSpec = new PodSpec.Builder()
            .containers(containers)
            .build();
        final PodTemplateSpec podTemplateSpec = new PodTemplateSpec.Builder()
            .metadata(objectMeta)
            .spec(podSpec)
            .build();
        final DeploymentSpec deploymentSpec = new DeploymentSpec.Builder()
            .replicas(replicas)
            .selector(labelSelector)
            .template(podTemplateSpec)
            .build();
        final KubeDeploymentProps deploymentProps = new KubeDeploymentProps.Builder()
            .spec(deploymentSpec)
            .build();

        new KubeDeployment(this, "deployment", deploymentProps);
        
        
        final ServiceBackendPort serviceBackendPort = new ServiceBackendPort.Builder()
                .number(portNumber)
                .build();
        
        final IngressServiceBackend ingressService = new IngressServiceBackend.Builder()
                .name(kubeService.getName())
                .port(serviceBackendPort)
                .build();
        
        final IngressBackend ingressBackend = new IngressBackend.Builder()
                .service(ingressService)
                .build();
        
        final HttpIngressPath ingressPath = new HttpIngressPath.Builder()
                .backend(ingressBackend)
                .pathType("Prefix")
                .path("/authors")
                .build();
        
        final HttpIngressRuleValue ingresrulevalue = new HttpIngressRuleValue.Builder()
                .paths(List.of(ingressPath))
                .build();
        
        final IngressRule ingressRule = new IngressRule.Builder()
                .http(ingresrulevalue)
                .build();
        
        final IngressSpec ingressSpec = new IngressSpec.Builder()
                .rules(List.of(ingressRule))
                .build();
        
        final KubeIngressProps ingressProps = new KubeIngressProps.Builder()
                .spec(ingressSpec)
                .build();
        
        new KubeIngress(this,"ingress", ingressProps);
        
    }
    
}
