/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.kore.k8s.services.infra;

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
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import software.constructs.Construct;

/**
 *
 * @author koni
 */
public class WebServiceConstruct extends Construct {

    public WebServiceConstruct(Construct scope, String id) {
        super(scope, id);
    }

    public static final WithName newInstance(Construct scope, String id) {
        WebServiceConstruct constr = new WebServiceConstruct(scope, id);
        WebServiceConstructBuilder builder = constr.new WebServiceConstructBuilder();
        return builder;
    }

    public static interface WithName {
        WithContainerImage withName(String name);
    }

    public static interface WithContainerImage {

        Builder withContainerImage(String image);
    }

    public static interface Builder {

        Builder withEnvVars(List<EnvVar> envVars);

        Builder withIngressRuleHttpPathPrefix(String pathPrefix);

        Builder withLivenessProbePath(String path);

        Builder withReadinessProbePath(String path);

        Builder withReplicas(int replicas);

        Builder withPortNumber(int port);

        Builder withContainerPortNumber(int port);

        void addToChart();
    }

    public class WebServiceConstructBuilder implements WithName, WithContainerImage, Builder {

        private String image;
        private String name;

        private int portNumber = 80;
        private int containerPortNumber = 8080;
        private int replicas = 2;
        private String livenessProbePath = "/q/health/live";
        private String readinessProbePath = "/q/health/ready";
        private List<EnvVar> envVars = Collections.emptyList();
        private String ingressRulePath = "/";

        private WebServiceConstructBuilder() {
            // just to make it private
        }

        @Override
        public WithContainerImage withName(String name) {
            Objects.requireNonNull(name);
            this.name = name;
            return this;
        }

        @Override
        public Builder withContainerImage(String image) {
            Objects.requireNonNull(image);
            this.image = image;
            return this;
        }

        @Override
        public Builder withEnvVars(List<EnvVar> envVars) {
            Objects.requireNonNull(envVars);
            this.envVars = Collections.unmodifiableList(envVars);
            return this;
        }

        @Override
        public Builder withIngressRuleHttpPathPrefix(String pathPrefix) {
            Objects.requireNonNull(pathPrefix);
            this.ingressRulePath = pathPrefix;
            return this;
        }

        @Override
        public Builder withLivenessProbePath(String path) {
            Objects.requireNonNull(path);
            this.livenessProbePath = path;
            return this;
        }

        @Override
        public Builder withReadinessProbePath(String path) {
            Objects.requireNonNull(path);
            this.readinessProbePath = path;
            return this;
        }

        @Override
        public Builder withReplicas(int replicas) {
            if (replicas <= 0) {
                throw new IllegalArgumentException("replicas must be greater than 0");
            }
            this.replicas = replicas;
            return this;
        }

        @Override
        public Builder withPortNumber(int port) {
            if (port <= 0) {
                throw new IllegalArgumentException("port must be greater than 0");
            }
            this.portNumber = port;
            return this;
        }

        @Override
        public Builder withContainerPortNumber(int port) {
            if (port <= 0) {
                throw new IllegalArgumentException("container port must be greater than 0");
            }
            this.containerPortNumber = port;
            return this;
        }


        @Override
        public void addToChart() {
// Defining a LoadBalancer Service
//        final String serviceType = "LoadBalancer";
            ObjectMeta serviceMetadata = new ObjectMeta.Builder()
                    .name(name)
                    .build();

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
                    .metadata(serviceMetadata)
                    .build();

            KubeService kubeService = new KubeService(WebServiceConstruct.this, "service", serviceProps);

            // Defining a Deployment
            HttpGetAction livenessAction = new HttpGetAction.Builder()
                    .port(IntOrString.fromNumber(Integer.valueOf(containerPortNumber)))
                    .path(this.livenessProbePath)
                    .build();

            Probe livenessProbe = new Probe.Builder()
                    .httpGet(livenessAction)
                    .periodSeconds(Integer.valueOf(5))
                    .initialDelaySeconds(3)
                    .build();

            HttpGetAction readynessAction = new HttpGetAction.Builder()
                    .port(IntOrString.fromNumber(Integer.valueOf(containerPortNumber)))
                    .path(this.readinessProbePath)
                    .build();

            Probe readyProbe = new Probe.Builder()
                    .httpGet(readynessAction)
                    .periodSeconds(Integer.valueOf(5))
                    .initialDelaySeconds(3)
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
                    .env(this.envVars)
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

            new KubeDeployment(WebServiceConstruct.this, "deployment", deploymentProps);

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
                    .path(this.ingressRulePath)
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

            new KubeIngress(WebServiceConstruct.this, "ingress", ingressProps);
        }

    }
}
