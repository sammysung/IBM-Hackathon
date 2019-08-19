---
layout: single
title: Readme
permalink: /readme/
---

# Usage
Structure: `java -jar frame.jar [-{h,f,o,d,i,v,q,w,a,n,t}] [input file] [safe file] [output file]`


**-h**: Print this help message.

**-a**: Run all tests implemented sequentially. Order to be updated as implementation is completed.

**-n**: Run AMNESIA test only. End once queries are scanned and analyzed. (Not yet implemented)

**-t**: Run Positive Tainting tests only. End once queries are scanned and analyzed. (In progress)

**-f**: Enable command line input file naming mode (first argument will be input filename).

**-o**: Enable command line output file naming mode (third argument will be output filename if -f is present, first otherwise).

**-d**: Enables command line safe query list definition (second argument will be safe filename)

**-i**: Enable user input output file naming mode (program will prompt for output filename).

**-v**: Enable verbose mode (as of now, is just a stub of functionality).

**-w**: If an output filename is reused, overwrite without asking.

**-q**: Use this to signal immediate shutdown (focused for testing purposes or help message only runs).

Example: "`java -jar frame.jar -hfov in.txt out.txt`" will print the help message, read from **in.txt**, output to **out.txt**, show a test selection menu, prompt for a safe file, and be verbose.

"`java -jar frame.jar -ftdow query.txt safe.txt test.txt`" will run the Positive Tainting test, pulling input data from **query.txt**, a safe file list from **safe.txt**, output to **test.txt**, and will overwrite **test.txt** if it already exists with no prompts.

Arguments **-f** and **-o** do not run in order of appearance; if **-o** is before **-f**, the first argument will still be the input file.

By default, with no arguments, the program will prompt for user input to assign the input filename and auto generate an output filename with the scheme "*report-{UTC Local Time}.txt*".

Arguments **-i** and **-o** are mutually exclusive. The program will use the autogenerate scheme if they are used together.

If a test is not selected in the arguments, a selection menu will be shown to the user.

Multiple test options can be selected, and they will run sequentially.

Query file shows format this project expects as of right now.
