#!/usr/bin/env bash

# 1. http://deathstartup.com/?p=81
# 2. https://gist.github.com/KioKrofovitch/716e6a681acb33859d16
# 3. https://stackoverflow.com/questions/35440907/can-circle-ci-reference-gradle-properties-credentials

export GRADLE_PROPERTIES=$HOME"/popmovie/gradle.properties"
export FABRIC_PROPERTIES=$HOME"/popmovie/app/fabric.properties"

function copyEnvVarsToProperties {

    echo "Gradle Properties should exist at $GRADLE_PROPERTIES"
    echo "Fabric Properties should exist at $FABRIC_PROPERTIES"

    if [ ! -f "$FABRIC_PROPERTIES" ]
    then
        echo "${FABRIC_PROPERTIES} does not exist...Creating file"

        touch ${FABRIC_PROPERTIES}

        echo "apiKey=$FABRIC_API_KEY" >> ${FABRIC_PROPERTIES}
        echo "apiSecret=$FABRIC_API_SECRET" >> ${FABRIC_PROPERTIES}
    fi

    if [ ! -f "$GRADLE_PROPERTIES" ]
    then
        echo "${GRADLE_PROPERTIES} does not exist...Creating Properties file"
    fi
}

# execute functions
copyEnvVarsToProperties