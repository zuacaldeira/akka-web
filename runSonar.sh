#!/usr/bin/env bash
mvn clean jacoco:prepare-agent install sonar:sonar
#mvn sonar:sonar