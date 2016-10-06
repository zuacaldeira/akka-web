#!/bin/bash
# TODO: All tests are skipped and never automatically run.
# Partially Fixed: Deselect sonar profile in Maven Projects toolbal
mvn clean jacoco:prepare-agent jacoco:prepare-agent-integration install -DskipITs=true


# TODO: Make tests to run at "saving" source code
# Partially Fixed:
