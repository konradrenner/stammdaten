#!/bin/bash

echo "starting quarkus build with container creation"

mvn -Dquarkus.container-image.build=true install

echo "loading docker-image into kind"

kind load docker-image kore/publishingcompany-service:2.0-SNAPSHOT
