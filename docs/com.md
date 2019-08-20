---
layout: single
title: Sample Commands
permalink: /com/
---

# Starter Commands

## Important Notes

As of this writing, the JAR file must be run from the command line due to a lack of an implemented GUI.
Because of this, simply double-clicking the JAR will appear to make it crash.
This also means that the code will only work on Linux, Mac, or Windows (if you take the time to add the proper PATH variable, go to [this page](/SQLI/setup/#Pathing) if you need help) from within the respective CLI.

Maven works from both a command line interface and from a IDE standpoint, as long as one takes the time to setup the IDE with the proper options. Refer to the [Configuration section](/SQLI/setup/#Config) for more help with this. 

## First run

`java -jar frame.jar -hq`

_This displays the help message and exits the program completely. Use to test compilation and/or see the help message._

## General Use

`java -jar frame.jar`

_Runs the default options for every setting, which will prompt for command line input of the filename to pull the test queries from, auto-generate an output filename based on UTC time, and show a selection menu for the tests that are to be performed. This mode will likely be the baseline for the GUI version of the project._

`java -jar frame.jar -f query.txt`

_Runs the default options, except that the filename for the test query file is defined in the CLI._

`java -jar frame.jar -i`

_Runs the default options, except that the program will prompt for the user to input an output filename during program execution._

`java -jar frame.jar -fdow query.txt test safe.txt`

_Takes the first argument as the input filename, the second as the root output filename, and the third as the safe query list, while overwriting it without asking if found. Note that order of the **-f**, **-d**, and **-o** options does not matter; the input name, then the root filename, and then the safe query list file will be read in that order every time._
 
