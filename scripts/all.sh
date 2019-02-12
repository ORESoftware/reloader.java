#!/usr/bin/env bash


mvn -T 5 -offline \
           clean \
           package \
           exec:java \
           -DskipTests -Dmaven.javadoc.skip=true -Dmaven.test.skip