#!/bin/bash

mvn clean package -DskipTests
java -jar target/rpn-calculator-1.0.0.jar
