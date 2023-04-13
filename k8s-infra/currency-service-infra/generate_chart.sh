#!/bin/bash

echo "generating chart"

mvn compile exec:java -DcontainerImage=kore/currency-service:0.2.0

echo "done"
