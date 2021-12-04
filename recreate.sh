#!/bin/bash
docker rm trips_app_1
docker-compose pull
docker-compose up --force-recreate
