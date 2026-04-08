## Java `StringBuilder` — Deep & Detailed Guide

**

**StringBuilder (Java)** is a class used to **create and modify strings efficiently**.
It belongs to the **java.lang package**.

Unlike **String (Java)**, `StringBuilder` is **mutable**, meaning its content can be changed without creating new objects.

---

# 1. Why StringBuilder Exists

### Problem with String

```java
String s = "Java";
s = s + " Programming";
```

Each modification creates **a new object in memory**.

Memory example:

```
"Java" → new object
"Java Programming" → another object
```

This is **slow and memory expensive** when many modifications happen.

---

### Solution: StringBuilder

```java
StringBuilder sb = new StringBuilder("Java");
sb.append(" Programming");
```

Only **one object is modified internally**.

---

# 2. Class Hierarchy

```
Object
   │
AbstractStringBuilder
   │
StringBuilder
```

Related class:

* **StringBuffer (Java)**

Difference:

| Class         | Thread Safe | Speed  |
| ------------- | ----------- | ------ |
| StringBuilder | ❌ No        | Fast   |
| StringBuffer  | ✔ Yes       | Slower |

---

# 3. Creating StringBuilder

### Empty Builder

```java
StringBuilder sb = new StringBuilder();
```

Default capacity:

```
16 characters
```

---

### With Initial String

```java
StringBuilder sb = new StringBuilder("Hello");
```

---

### With Capacity

```java
StringBuilder sb = new StringBuilder(50);
```

---

# 4. Important StringBuilder Methods

---

# 4.1 `append()`

Adds text at the end.

```java
StringBuilder sb = new StringBuilder("Java");

sb.append(" Programming");

System.out.println(sb);
```

Output

```
Java Programming
```

Supports many types:

```java
sb.append(10);
sb.append(true);
sb.append('A');
```

---

# 4.2 `insert()`

Inserts text at a position.

```java
StringBuilder sb = new StringBuilder("Java");

sb.insert(2, "XX");

System.out.println(sb);
```

Output

```
JaXXva
```

---

# 4.3 `delete()`

Deletes characters from a range.

```java
StringBuilder sb = new StringBuilder("Hello Java");

sb.delete(5,10);

System.out.println(sb);
```

Output

```
Hello
```

Range:

```
start inclusive
end exclusive
```

---

# 4.4 `deleteCharAt()`

Removes character at index.

```java
StringBuilder sb = new StringBuilder("Java");

sb.deleteCharAt(1);

System.out.println(sb);
```

Output

```
Jva
```

---

# 4.5 `replace()`

Replaces characters.

```java
StringBuilder sb = new StringBuilder("Java Programming");

sb.replace(5,16,"Language");

System.out.println(sb);
```

Output

```
Java Language
```

---

# 4.6 `reverse()`

Reverses string.

```java
StringBuilder sb = new StringBuilder("Java");

sb.reverse();

System.out.println(sb);
```

Output

```
avaJ
```

---

# 4.7 `charAt()`

Returns character at index.

```java
StringBuilder sb = new StringBuilder("Java");

System.out.println(sb.charAt(2));
```

Output

```
v
```

---

# 4.8 `setCharAt()`

Changes character.

```java
StringBuilder sb = new StringBuilder("Java");

sb.setCharAt(0,'K');

System.out.println(sb);
```

Output

```
Kava
```

---

# 4.9 `length()`

Returns length.

```java
StringBuilder sb = new StringBuilder("Java");

System.out.println(sb.length());
```

Output

```
4
```

---

# 4.10 `capacity()`

Returns buffer size.

```java
StringBuilder sb = new StringBuilder();

System.out.println(sb.capacity());
```

Output

```
16
```

---

# 4.11 `ensureCapacity()`

Ensures minimum capacity.

```java
StringBuilder sb = new StringBuilder();

sb.ensureCapacity(50);
```

---

# 4.12 `trimToSize()`

Reduces capacity to current length.

```java
sb.trimToSize();
```

---

# 4.13 `substring()`

Extracts substring.

```java
StringBuilder sb = new StringBuilder("Programming");

System.out.println(sb.substring(3));
```

Output

```
gramming
```

---

# 4.14 `indexOf()`

Finds substring index.

```java
StringBuilder sb = new StringBuilder("Java Programming");

System.out.println(sb.indexOf("Pro"));
```

Output

```
5
```

---

# 4.15 `lastIndexOf()`

Finds last occurrence.

```java
System.out.println(sb.lastIndexOf("g"));
```

---

# 4.16 `toString()`

Converts builder to string.

```java
StringBuilder sb = new StringBuilder("Java");

String s = sb.toString();
```

Now `s` is normal `String`.

---

# 5. Capacity Growth

When capacity is exceeded:

```
newCapacity = (oldCapacity * 2) + 2
```

Example:

```
Initial capacity = 16
New capacity = 34
```

---

# 6. Example Program

```java
public class Main {

    public static void main(String[] args) {

        StringBuilder sb = new StringBuilder("Java");

        sb.append(" Programming");
        sb.insert(4," Language");
        sb.delete(4,13);

        System.out.println(sb);

        sb.reverse();

        System.out.println(sb);
    }
}
```

Output

```
Java Programming
gnimmargorP avaJ
```

---

# 7. Performance Comparison

| Operation     | String       | StringBuilder |
| ------------- | ------------ | ------------- |
| Concatenation | Slow         | Fast          |
| Memory        | More objects | One object    |
| Mutable       | ❌            | ✔             |

---

# 8. Real Example: Fast String Concatenation

```java
StringBuilder sb = new StringBuilder();

for(int i=0;i<5;i++){
    sb.append(i);
}

System.out.println(sb);
```

Output

```
01234
```

---

# 9. Key Advantages

✔ Mutable
✔ Fast concatenation
✔ Memory efficient
✔ Useful in loops

---

# 10. Important Methods Summary

| Method      | Purpose          |
| ----------- | ---------------- |
| append()    | add text         |
| insert()    | insert text      |
| delete()    | remove text      |
| replace()   | replace text     |
| reverse()   | reverse string   |
| charAt()    | get character    |
| setCharAt() | modify character |
| length()    | string length    |
| capacity()  | buffer size      |

---

✅ **Summary**

`StringBuilder` is used when:

* Many string modifications occur
* Performance is important
* Thread safety is not required

It is **much faster than String for concatenation operations**.

---

If you want, I can also show **20 advanced `StringBuilder` tricks used in competitive programming and interviews (like palindrome checks, fast string building, custom parsers, etc.)**, which are very useful for Java developers.
