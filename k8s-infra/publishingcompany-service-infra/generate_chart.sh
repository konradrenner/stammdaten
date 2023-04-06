#!/bin/bash

echo "generating chart"

mvn compile exec:java -DcontainerImage=kore/publishingcompany-service:2.0-SNAPSHOT

echo "done"
