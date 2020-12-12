## Channel Popularity using State Pattern


Following are the commands & instructions to run the project using ANT.

Note: build.xml is present in [channelpopularity/src](./channelpopularity/src/) folder.

### Instruction to clean:

```commandline
ant -buildfile channelpopularity/src/build.xml clean
```

Description: It cleans up all the .class files that were generated when you
compiled your code.

### Instructions to compile:

```commandline
ant -buildfile channelpopularity/src/build.xml all
```
The above command compiles your code and generates .class files inside the BUILD folder.

### Instructions to run:

```commandline
ant -buildfile channelpopularity/src/build.xml run -Dinput="input.txt" -Doutput="output.txt"
```
Note: Arguments accept the absolute path of the files.


### Description:

precision 3 digits after decimal.
HashMap is used to store videos in context class. 
commands to compile and run the program are the same.

Date:  June 17, 2020


