#!/bin/sh

export POD_NAME=$(kubectl get pods --namespace platform -l "app.kubernetes.io/name=signoz,app.kubernetes.io/instance=my-release,app.kubernetes.io/component=frontend" -o jsonpath="{.items[0].metadata.name}")
echo "Visit http://127.0.0.1:3301 to use your application"
kubectl --namespace platform port-forward $POD_NAME 3301:3301 
