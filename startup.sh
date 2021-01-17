#!/bin/bash
echo
echo "#################"
echo "Start Environment"
echo "#################"
echo

echo
echo "#################"
echo "Build Application"
echo "#################"
echo
./gradlew clean build

echo
echo "#############################"
echo "Run Database Migration Flyway"
echo "#############################"
echo
./gradlew -Dflyway.configFiles=src/main/resources/flyway.conf flywayRepair flywayMigrate flywayInfo

echo
echo "#################"
echo "Start Application"
echo "#################"
echo
./gradlew bootRun --args='--spring.profiles.active=local'