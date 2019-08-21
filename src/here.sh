#!/bin/bash

javac -cp .:mysql-connector-java-8.0.17.jar test.java

java -cp .:mysql-connector-java-8.0.17.jar test

