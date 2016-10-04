#!/bin/bash
mvn clean jacoco:prepare-agent install -DskipITs=true sonar:sonar jetty:run
