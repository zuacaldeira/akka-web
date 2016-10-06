#!/bin/bash
mvn clean install -DskipTests=true sonar:sonar jetty:run
