#!/usr/bin/env bash
mvn clean install jacoco:prepare-agent jacoco:report sonar:sonar
#mvn sonar:sonar