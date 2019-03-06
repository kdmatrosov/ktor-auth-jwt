#!/usr/bin/env bash
# Exit on first error, print all commands.
set -e

docker-compose -f docker-compose-build.yml build