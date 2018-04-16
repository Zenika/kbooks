#!/bin/bash
set -e
set -o pipefail

PROJECT_HOME=$(cd "$(dirname "$0")/.." && pwd)

# Buid project
"${PROJECT_HOME}/gradlew" clean build

# Build docker images
docker build -t "kbooks/backend/author" "${PROJECT_HOME}/backend/author"
docker build -t "kbooks/backend/book" "${PROJECT_HOME}/backend/book"
