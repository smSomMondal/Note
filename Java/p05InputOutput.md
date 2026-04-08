Below is a **deep explanation of the `Scanner` class and `println` in Java**, including **internal working, methods, examples, pitfalls, and best practices**.

---

# 1. Scanner Class in Java

## 1.1 What is Scanner?

`Scanner` is a class in the **Java Standard Library** inside the **java.util package** used to **read input from different sources** such as:

* Keyboard (System.in)
* Files
* Strings
* Input streams

It was introduced in **Java SE 5**.

### Import Statement

```java
import java.util.Scanner;
```

---

# 1.2 Creating a Scanner Object

### Reading from Keyboard

```java
Scanner sc = new Scanner(System.in);
```

Explanation:

| Part        | Meaning                          |
| ----------- | -------------------------------- |
| `Scanner`   | Class name                       |
| `sc`        | Object name                      |
| `new`       | Creates object                   |
| `System.in` | Standard input stream (keyboard) |

---

# 1.3 Internal Working of Scanner

`Scanner` internally uses:

1. **InputStream**
2. **Buffer**
3. **Delimiter Pattern**

### Working Steps

1. Input comes from **System.in**
2. Scanner reads data into a **buffer**
3. Input is **split into tokens using delimiters**
4. Tokens are **converted into required datatype**

Default delimiter:

```
Whitespace (space, tab, newline)
```

Example input:

```
10 20 30
```

Tokens:

```
10
20
30
```

---

# 1.4 Scanner Constructors

| Constructor                   | Description            |
| ----------------------------- | ---------------------- |
| `Scanner(InputStream source)` | Read from input stream |
| `Scanner(File file)`          | Read from file         |
| `Scanner(String source)`      | Read from string       |
| `Scanner(Path path)`          | Read from file path    |

Example:

```java
Scanner sc = new Scanner("Hello 100 Java");
```

---

# 1.5 Important Scanner Methods

## 1.5.1 next()

Reads **one word**

Stops at whitespace.

```java
Scanner sc = new Scanner(System.in);
String name = sc.next();
```

Input:

```
Hello World
```

Output:

```
Hello
```

---

## 1.5.2 nextLine()

Reads **entire line**

```java
String line = sc.nextLine();
```

Input

```
Hello World Java
```

Output

```
Hello World Java
```

---

## 1.5.3 nextInt()

Reads integer

```java
int n = sc.nextInt();
```

Example:

```
Input: 25
Output: 25
```

---

## 1.5.4 nextDouble()

```java
double d = sc.nextDouble();
```

---

## 1.5.5 nextFloat()

```java
float f = sc.nextFloat();
```

---

## 1.5.6 nextBoolean()

```java
boolean b = sc.nextBoolean();
```

Input:

```
true
```

---

## 1.5.7 nextLong()

```java
long l = sc.nextLong();
```

---

# 1.6 Checking Input Availability

### hasNext()

```java
if(sc.hasNext()){
}
```

### hasNextInt()

```java
if(sc.hasNextInt()){
}
```

Example:

```java
Scanner sc = new Scanner(System.in);

if(sc.hasNextInt()){
    int x = sc.nextInt();
    System.out.println(x);
}
```

---

# 1.7 Scanner Delimiters

Delimiter defines **how tokens are separated**.

Default:

```
Whitespace
```

Change delimiter:

```java
sc.useDelimiter(",");
```

Example:

Input

```
10,20,30
```

Code:

```java
Scanner sc = new Scanner("10,20,30");
sc.useDelimiter(",");

while(sc.hasNext()){
    System.out.println(sc.next());
}
```

Output

```
10
20
30
```

---

# 1.8 Scanner with File

```java
import java.util.*;
import java.io.*;

public class Test {
    public static void main(String[] args) throws Exception {

        File file = new File("data.txt");

        Scanner sc = new Scanner(file);

        while(sc.hasNextLine()){
            System.out.println(sc.nextLine());
        }
    }
}
```

---

# 1.9 Closing Scanner

Always close scanner.

```java
sc.close();
```

Why?

Because it **releases system resources**.

---

# 1.10 Common Scanner Problem (nextLine issue)

Example:

```java
int x = sc.nextInt();
String s = sc.nextLine();
```

Problem:

`nextLine()` reads leftover **newline character**.

Solution:

```java
int x = sc.nextInt();
sc.nextLine(); // consume newline
String s = sc.nextLine();
```

---

# 1.11 Performance Issue

`Scanner` is **slow** for large input.

Competitive programming prefers:

* `BufferedReader`
* `Fast IO`

# 1.12 Example: Sum of Automatic Input Array

```java
import java.util.*;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int[] arr = Arrays.stream(sc.nextLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        int sum = 0;

        for(int x : arr){
            sum += x;
        }

        System.out.println("Sum = " + sum);
    }
}
```
Input

`5 10 15 20`

Output

`Sum = 50`

## ✅ Best Method (Recommended)
```c++
    int[] arr = Arrays.stream(sc.nextLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
    int[] arr = Arrays.stream(sc.nextLine().split(",\\s*"))
                    .mapToInt(Integer::parseInt)
                    .toArray();
```
- ✔ Automatic
- ✔ No loop needed
- ✔ Used in modern Java coding
---

# 2. System.out.println() in Java

## 2.1 What is println?

`println()` prints **text to the console and moves to next line**.

Example:

```java
System.out.println("Hello");
```

Output

```
Hello
```

---

# 2.2 Understanding System.out.println

Breakdown:

```java
System.out.println("Hello");
```

| Part        | Meaning       |
| ----------- | ------------- |
| `System`    | Class         |
| `out`       | Static object |
| `println()` | Method        |

---

## 2.3 System Class

`System` belongs to:

```
java.lang package
```

It contains:

* standard input
* standard output
* environment variables

---

## 2.4 out Object

`out` is an object of class:

```
PrintStream
```

So actually:

```java
PrintStream out = System.out;
```

---

## 2.5 println() Method

Defined inside **PrintStream class**.

Purpose:

```
print + new line
```

Equivalent to:

```
print()
then
new line
```

---

# 2.6 println() vs print()

### println()

```java
System.out.println("Hello");
System.out.println("Java");
```

Output

```
Hello
Java
```

---

### print()

```java
System.out.print("Hello ");
System.out.print("Java");
```

Output

```
Hello Java
```

---

# 2.7 println() Overloaded Methods

`println()` supports many types.

Examples:

```java
println(int)
println(double)
println(float)
println(char)
println(boolean)
println(String)
println(Object)
```

Example:

```java
System.out.println(10);
System.out.println(5.5);
System.out.println(true);
System.out.println('A');
System.out.println("Java");
```

---

# 2.8 Printing Objects

```java
class Test{
}

Test t = new Test();
System.out.println(t);
```

Output:

```
Test@1b6d3586
```

Because it prints **hashCode**.

Override `toString()` to customize.

---

# 2.9 println with String Concatenation

```java
int a = 10;

System.out.println("Value = " + a);
```

Output

```
Value = 10
```

---

# 2.10 println with Formatting

Better approach:

```java
System.out.printf("Value = %d", a);
```

---

# 2.11 println Internal Process

Steps:

1. Data passed to `println()`
2. Converted to **String**
3. Sent to **PrintStream**
4. Printed to **console**
5. Newline added

---

# 3. Complete Example (Scanner + println)

```java
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("Enter your name:");

        String name = sc.nextLine();

        System.out.println("Enter your age:");

        int age = sc.nextInt();

        System.out.println("Name: " + name);
        System.out.println("Age: " + age);

        sc.close();
    }
}
```

Output:

```
Enter your name:
Som
Enter your age:
22
Name: Som
Age: 22
```

---

# 4. Scanner vs BufferedReader

| Feature                 | Scanner   | BufferedReader |
| ----------------------- | --------- | -------------- |
| Speed                   | Slow      | Fast           |
| Parsing                 | Automatic | Manual         |
| Easy to use             | Yes       | Moderate       |
| Competitive programming | No        | Yes            |

---

# 5. Best Practices

✔ Always close Scanner
✔ Handle input mismatch
✔ Use `nextLine()` carefully
✔ Use `BufferedReader` for large input

---

If you want, I can also explain **10 advanced Scanner tricks used in competitive programming and interviews** (most Java developers don't know them).
