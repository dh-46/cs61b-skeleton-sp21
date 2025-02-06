# Lab6-Getting Started on Project 2

[Lab Info](https://sp21.datastructur.es/materials/lab/lab6/lab6)

## Java and Compilation

### Check your Java version

```bash
$ javac -version
$ java -version
```

> Windows 要使用 Git Bash 

### Java Compilation

- `$ javac *.class`: Compile all .class file in this directory.
- `$ java capers.Main`: Execute Main (must use fully canonical package)

## Files and Directories in Java

### Current Working Directory (CWD)

- Access the CWD from within a Java program: `System.getProperty("user.dir");`
- Access the CWD with terminal/ Git Bash command: `pwd`
- Get CWD in IntelliJ IDE: Run/ Edit Configurations/ Working Directory

### Absolute and Relative Paths

A path is the location of a file or directory.

- Absolute path: relative to the root of the file system.
  - E.g (Windows): `C:/Users/Michelle/example/Example.java`
  - E.g (Mac): `/home/Michelle/example/Example.java`
- Relative path: relative to the CWD of your program.

#### the dot `.`

- `.`: refers to the CWD.
  - `"./example/Example.java" == "example/Example.java"`
- `..`: refers to the parent directory.
- `../..`: refers to parent's parent directory.

### File and Directory Manipulation in Java

`File` is a Java class represents a file or directory in your operating system.

```java
import java.io.File;

// Create File object. reference to a file named 'dummy.txt'
File f = new File("dummy.txt");

// Actually create the file
f.createNewFile();

// Check whether file exits or not
boolean isExists = f.exists();

// Create a folder
File d = new File("dummyFolder");
d.mkdir();
```

## Serializable

_Serialization is the process of translating an object to a series of bytes that can then be stored in the file._

Java Interface `java.io.Serializable`.

```java
import java.io.Serializable;

public class Model implements Serializable {
    // ...
}
```