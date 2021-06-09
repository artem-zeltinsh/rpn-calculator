#!/bin/bash

mvn clean package -DskipTests
java -jar target/rpn-calculator-0.1.jar