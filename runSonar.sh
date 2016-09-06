#!/usr/bin/env bash
mvn test jacoco:prepare-agent jacoco:report sonar:sonar
#mvn sonar:sonar