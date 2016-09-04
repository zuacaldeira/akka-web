#!/usr/bin/env bash
mvn clean jacoco:prepare-agent jacoco:report sonar:sonar
#mvn sonar:sonar