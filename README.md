# RPN calculator
RPN Calculator is a command-line Reverse Polish Notation calculator.

## Description
* Supports basic math operators `+`,`-`,`*`,`/`,`sqrt`.
* `undo` and `clear` functionality.
* `exit` will finish the session.

## Prerequisites
* Java 11
* Maven

## How to build and run
* Run `./run_calculator.sh` from the command line. If a system user has no execution permissions for the file try to run `chmod u+x run_calculator.sh`.
* Other option is to run `mvn clean compile exec:exec -Dexec.executable="java"`.

## How to use the calculator
* Add numbers
```shell
 ----=-=------------------------------------=-=-=---
/   Welcome to the command-line RPN calculator!   /
--=-=-=------------------------------------=-=----

7 3 +
stack: 10
```
* Multiple operations in as a single input line
```shell
clear
stack:

5 10 * 1 - sqrt
stack: 7

undo
stack: 49

exit
```








