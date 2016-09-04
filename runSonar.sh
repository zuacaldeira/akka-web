#!/usr/bin/env bash
mvn compile jacoco:prepare-agent test jacoco:report sonar:sonar
#mvn sonar:sonar