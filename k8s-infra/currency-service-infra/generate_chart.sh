#!/bin/bash

echo "generating chart"

mvn compile exec:java -DcontainerImage=kore/currency-service:0.1.1

echo "done"
