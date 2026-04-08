## Java `String` Class — Deep & Detailed Guide

**String (Java)** is one of the most important classes in the **Java Standard Library**. It represents a **sequence of characters** and is used for **text manipulation**.

It belongs to the **java.lang package**, so it **does not need to be imported**.

---

# 1. What is a String?

A **String** is an **immutable sequence of characters**.

Example:

```java
String name = "Java";
```

Characters stored:

```
J  a  v  a
0  1  2  3
```

---

# 2. String Immutability

Strings in Java are **immutable**, meaning **once created they cannot be changed**.

Example:

```java
String s = "Hello";
s.concat(" World");

System.out.println(s);
```

Output

```
Hello
```

Reason: `concat()` creates **a new object**, the original string remains unchanged.

---

# 3. Creating Strings

### Method 1: String Literal

```java
String s = "Java";
```

Stored in **String Pool**.

---

### Method 2: Using `new`

```java
String s = new String("Java");
```

Creates **new object in heap**.

---

# 4. Important String Methods

Below are the **most used String methods**.

---

# 4.1 length()

Returns number of characters.

```java
String s = "Java";

System.out.println(s.length());
```

Output

```
4
```

---

# 4.2 charAt()

Returns character at index.

```java
String s = "Java";

System.out.println(s.charAt(2));
```

Output

```
v
```

---

# 4.3 substring()

Extracts part of string.

```java
String s = "Programming";

System.out.println(s.substring(3));
```

Output

```
gramming
```

With range:

```java
System.out.println(s.substring(3,7));
```

Output

```
gram
```

---

# 4.4 equals()

Checks string equality.

```java
String a = "Java";
String b = "Java";

System.out.println(a.equals(b));
```

Output

```
true
```

---

# 4.5 equalsIgnoreCase()

Ignores case.

```java
String a = "java";
String b = "JAVA";

System.out.println(a.equalsIgnoreCase(b));
```

Output

```
true
```

---

# 4.6 compareTo() and compareToIgnoreCase()

Lexicographical comparison.

```java
String a = "Apple";
String b = "Banana";

System.out.println(a.compareTo(b));

String a = "java";
String b = "JAVA";

System.out.println(a.compareToIgnoreCase(b));

```

Output

```
negative number
```

Rules

```
0  → equal
<0 → smaller
>0 → greater
```

---

# 4.7 contains()

Checks if substring exists.

```java
String s = "Hello Java";

System.out.println(s.contains("Java"));
```

Output

```
true
```

---

# 4.8 startsWith()

```java
String s = "Java Programming";

System.out.println(s.startsWith("Java"));
```

Output

```
true
```

---

# 4.9 endsWith()

```java
String s = "file.txt";

System.out.println(s.endsWith(".txt"));
```

Output

```
true
```

---

# 4.10 toUpperCase()

```java
String s = "java";

System.out.println(s.toUpperCase());
```

Output

```
JAVA
```

---

# 4.11 toLowerCase()

```java
String s = "JAVA";

System.out.println(s.toLowerCase());
```

Output

```
java
```

---

# 4.12 trim()

Removes spaces.

```java
String s = "   Java   ";

System.out.println(s.trim());
```

Output

```
Java
```

---

# 4.13 replace()

Replace characters.

```java
String s = "Java";

System.out.println(s.replace('a','o'));
```

Output

```
Jovo
```

---

# 4.14 replaceAll()

Uses **regex**.

```java
String s = "Java123";

System.out.println(s.replaceAll("[0-9]",""));
```

Output

```
Java
```

---

# 4.15 split()

Splits string.

```java
String s = "Java Python C++";

String[] arr = s.split(" ");

for(String x : arr)
    System.out.println(x);
```

Output

```
Java
Python
C++
```

---

# 4.16 indexOf()

Returns position.

```java
String s = "Programming";

System.out.println(s.indexOf("g"));
```

Output

```
3
```

---

# 4.17 lastIndexOf()

```java
String s = "Programming";

System.out.println(s.lastIndexOf("g"));
```

Output

```
10
```

---

# 4.18 concat()

Joins strings.

```java
String a = "Hello";
String b = "World";

System.out.println(a.concat(b));
```

Output

```
HelloWorld
```

---

# 4.19 isEmpty()

```java
String s = "";

System.out.println(s.isEmpty());
```

Output

```
true
```

---

# 4.20 valueOf()

Converts data types to string.

```java
int n = 10;

String s = String.valueOf(n);

System.out.println(s);
```

Output

```
"10"
```

---

# 5. String Pool

Java stores **string literals in a special memory area** called **String Pool**.

Example:

```java
String a = "Java";
String b = "Java";
```

Both reference **same object**.

```
a -----
       → "Java"
b -----
```

---

# 6. String Comparison

### Using `==`

Checks **reference**

```java
String a = "Java";
String b = new String("Java");

System.out.println(a == b);
```

Output

```
false
```

---

### Using `equals()`

Checks **value**

```java
System.out.println(a.equals(b));
```

Output

```
true
```

---

# 7. Converting String to Other Types

### String → int

```java
String s = "100";

int x = Integer.parseInt(s);
```

---

### String → double

```java
double d = Double.parseDouble("10.5");
```

---

# 8. Iterating Through String

### Using for loop

```java
String s = "Java";

for(int i=0;i<s.length();i++){
    System.out.println(s.charAt(i));
}
```

Output

```
J
a
v
a
```

---

# 9. Convert String to Character Array

```java
String s = "Java";

char[] arr = s.toCharArray();

System.out.println(arr[1]);
```

Output

```
a
```

---

# 10. StringBuilder vs String

| Feature     | String                | StringBuilder |
| ----------- | --------------------- | ------------- |
| Mutable     | ❌                     | ✔             |
| Thread safe | ✔                     | ❌             |
| Speed       | Slow for modification | Fast          |

Example:

```java
StringBuilder sb = new StringBuilder("Java");

sb.append(" Programming");

System.out.println(sb);
```

Output

```
Java Programming
```

---

# 11. Real Example Program

Reverse a string.

```java
import java.util.*;

public class Main {

    public static void main(String[] args){

        String s = "Java";

        String rev = "";

        for(int i=s.length()-1;i>=0;i--){
            rev += s.charAt(i);
        }

        System.out.println(rev);
    }
}
```

Output

```
avaJ
```

---

# 12. Most Important String Methods Summary

| Method      | Purpose            |
| ----------- | ------------------ |
| length()    | string size        |
| charAt()    | character at index |
| substring() | extract string     |
| equals()    | compare strings    |
| contains()  | check substring    |
| split()     | divide string      |
| replace()   | replace characters |
| trim()      | remove spaces      |
| indexOf()   | find position      |

---

# 13. Key Points

✔ String is **immutable**
✔ Stored in **String Pool**
✔ Most used class in Java
✔ Provides many built-in methods

---

✅ If you want, I can also explain **40+ String interview problems (reverse, palindrome, anagram, substring search, compression, etc.) with Java solutions**, which are **very important for coding interviews and DSA**.
