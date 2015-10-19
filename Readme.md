#Lexical Analyzer 

---

## About this program.
This code analyses the input file and breaks it into tokens and analyses these tokens. This is the function of the lexical analyzer. It can be called as a subroutine of a parser.
It can also detect a few errors, such as un-ended comments and strings, wrong numbers. Unsupported operators are ignored.

## Contents
*Java Source code
*Java Class files
*Test1.spl : correct file w/o errors.
*Test2.spl : file w/ errors.
*Test3.spl : file w/ errors.


## How to use this java code?
```Bash
compile:	$javac simpLex.java
run:		$java simpLex name_of_file
eg:			$java simpLex test.spl 
```



Please run refer to the state diagram for more details. read the state diagram clockwise.
