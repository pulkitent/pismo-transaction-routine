#!/bin/bash

# Check if Colima is installed
if ! command -v colima &> /dev/null
then
    echo "Colima is not installed. Installing..."
    brew install colima

fi

colima start --cpu 4 --memory 4 --disk 60

# Set environment variables
export TESTCONTAINERS_DOCKER_SOCKET_OVERRIDE=/var/run/docker.sock
export DOCKER_HOST="unix://${HOME}/.colima/docker.sock"

# Create symbolic link
ln -s $HOME/.colima/default/docker.sock /var/run/docker.sock

# Run docker-compose
docker-compose up --build
