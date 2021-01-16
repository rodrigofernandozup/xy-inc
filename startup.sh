#!/bin/bash
echo "Start Environment"
echo "Build Application"
./gradlew clean build
echo "Docker Compose UP Conteiners"
docker-compose up -d
sleep 10
echo "Run Database Migration Flyway"
./gradlew -Dflyway.configFiles=src/main/resources/flyway.conf flywayRepair flywayMigrate flywayInfo
echo "Start Application"
./gradlew bootRun --args='--spring.profiles.active=local'