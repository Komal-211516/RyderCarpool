#!/usr/bin/env bash
APP_HOME="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"
java -jar "$APP_HOME/gradle/wrapper/gradle-wrapper.jar" "$@"
