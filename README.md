# Project Title: Arbitrary Precision Arithmetic Library in Java

In this Java Project, implementation of arbitrary precision arithmetic library is done which can be used for dealing with big number computations. It consists of two classes - AFloat and AInteger, which handles integer and floating point arithmetic respectively. Each class suppports addition, subtraction, multiplication, and division operations. The numbers are stored as strings so that the precision is not lost. 


## About the Library

This library is actually a jar file named aarithmetic.jar which can used in any program and even in any machine. It has been created using Ant build.xml file. It includes the compiled AInteger.class and AFloat.class files.

The contents of aarithmetic.jar can be displayed using the command in the terminal

        jar tf /<Path>/arrithmetic.jar

    where Path is the directory in which the jar file is present

The contents of aarithmetic.jar are as follows:

        META-INF/
        META-INF/MANIFEST.MF
        arbitraryarithmetic/
        arbitraryarithmetic/AFloat.class
        arbitraryarithmetic/AInteger.class

## Features

It consists of two classes - AInteger and AFloat

### AInteger Class:
- It deals with integers.
- It stores the integer in string representation.
- It supports addition, subtraction, multiplication and division operations.

### AFloat Class:
- It deals with Floating point numbers.
- It stores the floating point number in string represntation.
- It supports addition, subtraction, multiplication and division.
- It supports addition, subtraction, multiplication and division operations.
- It truncates the result to 30 decimal places.

## Implementation

- **Language :** **Java 21**

- **Build :** **ANT**

- Both AInteger and AFloat classes store the number in the attribute named number. This attribute is made private. An getter method named getInteger() and getFloat() is defined to access the integer and floating point number respectively.



- The actual methods which perform the arithmetic operations make use of many helper functions defined in either class. These operator methods are made static so that they can be used by writing **\<className>.<operation_method>(className number1,className number2).**
Each of this method returns the result as an object of same type as that of the operands.The number can be accessed using the corresponding getter method.


## Build and Run

- **Build Tool : ANT** 
  
- How to create aarithmetic.jar file 

        ant distributable

- How to link the aarithmetic.jar to any java program and run


    - First, include the import statements should be included in the java file.
        - If AInteger.class is used, add

                    import arbitraryarithmetic.AInteger;

        - If Afloat.class is used, add

                    import arbitraryarithmetic.AFloat;

    
    - To compile the java file which uses the library (aarithmetic.jar), we need to execute the following command in the terminal window

            javac -cp /<path>/aarithmetic.jar <name>.java
    
    - Once the compilation is successful, we run the java file using the following command in the terminal. If the java file takes command line arguments, they can be added after the java file name in this command.

            javac -cp /<path>/aarithmetic.jar <name>.java <int/float> <add/sub/mul/div> <operand1> <operand2>

        In the above two commands, path is the path of directory where .jar file is present and name is the name of java file.
    
    - For example, we can run a java file with the following command: 

            java -cp /<path>/aarithmetic.jar <name>.java float div 1 3

        **Output: 0.333333333333333333333333333333**


## Limitations

- It does work for the numbers given in scientific notation.

- The precision of the output of AFloat arithmetic operations is 30 decimal places. It does not round-off the result but truncates it.

- The length of the input number cannot be more than $2^{31} - 1$. This is because the
maximum length of a string in Java is INT_MAX.

- There is no input validation for the numbers in AFloat and AInteger classes.

    



