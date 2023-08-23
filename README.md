# JavaCSVOperations

## This repo addresses the following problem statement:
Create a Java utility to meet following requirements.

1. Utility should read a CSV file and support following operations
2. Sort the file based on country name and store it to output file
3. Convert the temperature values to fahrenheit and store it to output file
4. Add/Edit/Delete country/temperature to the existing file.

Utility should take the following as input from the command line.(Not as launch parameters)
1. Input file location
2. Output file location
3. Operation type
4. For Add/Edit/Delete it should ask for appropriate values. 
After completion of the operation, it should print the contents of the output file in readable format.
Create a runnable jar file for this utility.

## How to run the code

Run the assign-1.jar file and follow the instructions on the console.

## Description

The code runs a menu-driven program. Each choice is an integer in the range 1-7 (both included).
1. An entry is added to the file if it does not already exist there.
2. An edited entry is pushed to the end of the file after changes and the prior entry is deleted.
3. In case of variable domain mistmatch (eg. not giving float data for temperature variable) will stop the program with InputMismatchException.
4. On sorting the file, on the column "country name", a new (output) file is created and the sorted data is written to the output file path taken as input from console.
5. On conversion of temperature from Celsius to Farenheit, a new (output) file is created and temperatures in C and F are written to the output file path taken as input from the console.
