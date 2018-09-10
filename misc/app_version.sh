#!/usr/bin/env bash

function copyEnvVarsToProperties {
    appVersion=$(grep appVersionString ../build.gradle | sed -e 's/ //g;s/.*appVersionString="//;s/".*//g')
    appVersionNumber=$(grep appVersionCode ../build.gradle | sed -e 's/[^0-9]//g')
    mkdir ../notes
    git log --format=%B 29d35de7f08e40b69ff97bdaa7126236c4f79d91..HEAD >> ../notes/$appVersion-$appVersionNumber
#    git log --format=%B -n 1 HEAD >> dev/$appVersion-$appVersionNumber.txt
}

copyEnvVarsToProperties