#!/usr/bin/env bash

# 1. http://deathstartup.com/?p=81
# 2. https://gist.github.com/KioKrofovitch/716e6a681acb33859d16
# 3. https://stackoverflow.com/questions/35440907/can-circle-ci-reference-gradle-properties-credentials

export GRADLE_PROPERTIES=$HOME"/popmovie/gradle.properties"

function copyEnvVarsToProperties {

    echo "Gradle Properties should exist at $GRADLE_PROPERTIES"

    if [ ! -f "$GRADLE_PROPERTIES" ]
    then
        echo "${GRADLE_PROPERTIES} does not exist...Creating Properties file"
        echo "FABRIC_API_KEY=$FABRIC_API_KEY" >> ${GRADLE_PROPERTIES}
    fi
}

# execute functions
copyEnvVarsToProperties