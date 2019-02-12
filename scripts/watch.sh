#!/usr/bin/env bash


rm -rf target/classes
mvn -offline  package -T 9 -DskipTests -Dmaven.javadoc.skip=true -P activation -Dmaven.test.skip