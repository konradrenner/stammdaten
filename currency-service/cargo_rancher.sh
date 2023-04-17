#!/bin/bash

file="./Cargo.toml"

function prop {
    grep "${1}" ${file} | cut -d'=' -f2 | tail -c +3 | head -c -2
}

docker_tag=$(prop 'docker_tag')


echo "Docker-Tag:" $docker_tag
echo "starting cargo build"

cargo build

echo "starting container build"

docker build -t $docker_tag .