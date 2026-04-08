## BufferedReader in Java (Deep Explanation)

### 1. What is BufferedReader?

**BufferedReader** is a class used to **read text efficiently from input streams** by **buffering characters**.

It belongs to the **java.io package** and is commonly used for:

* Fast input from keyboard
* Reading large files
* Competitive programming (faster than Scanner)

---

# 2. Why BufferedReader is Faster

### Without Buffer

When a program reads characters **one by one from disk or keyboard**, each read requires a **system call**.

```
Input Source → JVM → Program
(read every character separately)
```

This is **slow**.

---

### With Buffer

BufferedReader reads **a large block of characters at once** and stores them in **memory (buffer)**.

```
Input Source → Buffer → Program
```

Example:

```
Disk → [Buffer 8192 characters] → Program reads quickly
```

Default buffer size:

```
8192 characters (8 KB)
```

---

# 3. Class Hierarchy

BufferedReader belongs to Java I/O stream hierarchy.

```
Object
   │
Reader (abstract class)
   │
BufferedReader
```

Related classes:

* **Reader**
* **InputStreamReader**

---

# 4. Import Statement

```java
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
```

---

# 5. Creating BufferedReader Object

### Reading From Keyboard

```java
BufferedReader br =
    new BufferedReader(new InputStreamReader(System.in));
```

Explanation:

| Part                | Purpose                     |
| ------------------- | --------------------------- |
| `System.in`         | Byte input stream           |
| `InputStreamReader` | Converts bytes → characters |
| `BufferedReader`    | Adds buffering              |

Flow:

```
Keyboard
   ↓
System.in
   ↓
InputStreamReader
   ↓
BufferedReader
   ↓
Your program
```

---

# 6. Reading Input from Terminal

### Example

```java
import java.io.*;

public class Main {

    public static void main(String[] args) throws Exception {

        BufferedReader br =
            new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Enter your name:");

        String name = br.readLine();

        System.out.println("Hello " + name);
    }
}
```

Input

```
Som
```

Output

```
Hello Som
```

---

# 7. Important Methods of BufferedReader

## 7.1 read()

Reads **single character**

```java
int c = br.read();
```

Returns:

```
ASCII / Unicode value
```

Example:

```java
int c = br.read();
System.out.println((char)c);
```

---

## 7.2 readLine()

Most used method.

Reads **entire line of text**.

```java
String line = br.readLine();
```

Stops at:

```
newline (\n)
```

Example:

```java
String line = br.readLine();
System.out.println(line);
```

---

## 7.3 read(char[] buffer)

Reads characters into array.

```java
char[] arr = new char[100];

int n = br.read(arr);
```

`n` = number of characters read.

---

## 7.4 ready()

Checks if stream is ready to read.

```java
if(br.ready()){
    String line = br.readLine();
}
```

---

## 7.5 skip()

Skips characters.

```java
br.skip(10);
```

---

## 7.6 close()

Releases system resources.

```java
br.close();
```

---

# 8. Reading Integers Using BufferedReader

BufferedReader **only reads strings**, so we must convert manually.

Example:

```java
import java.io.*;

public class Main {

    public static void main(String[] args) throws Exception {

        BufferedReader br =
            new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        System.out.println(n);
    }
}
```

Input

```
25
```

Output

```
25
```

---

# 9. Reading Space-Separated Values

Example input:

```
10 20 30 40
```

Code:

```java
BufferedReader br =
    new BufferedReader(new InputStreamReader(System.in));

String[] arr = br.readLine().split(" ");

for(String s : arr){
    int num = Integer.parseInt(s);
    System.out.println(num);
}
```

---

# 10. Reading Array Using BufferedReader

```java
BufferedReader br =
    new BufferedReader(new InputStreamReader(System.in));

String[] parts = br.readLine().split(" ");

int[] arr = new int[parts.length];

for(int i=0;i<parts.length;i++){
    arr[i] = Integer.parseInt(parts[i]);
}
```

Input

```
1 2 3 4 5
```

Array

```
[1,2,3,4,5]
```

---

# 11. BufferedReader with File

```java
import java.io.*;

public class Main {

    public static void main(String[] args) throws Exception {

        BufferedReader br =
            new BufferedReader(new FileReader("data.txt"));

        String line;

        while((line = br.readLine()) != null){
            System.out.println(line);
        }

        br.close();
    }
}
```

---

# 12. BufferedReader vs Scanner

| Feature                 | BufferedReader | Scanner             |
| ----------------------- | -------------- | ------------------- |
| Speed                   | Very fast      | Slower              |
| Parsing                 | Manual         | Automatic           |
| Reads                   | Strings        | Multiple data types |
| Competitive programming | Preferred      | Not preferred       |

Example speed difference:

```
BufferedReader ≈ 3–10x faster
```

---

# 13. Why Competitive Programmers Use BufferedReader

Reasons:

1. Faster input
2. Less overhead
3. Better for large datasets

Example problem:

```
Input size = 10^6 numbers
```

Scanner → slow
BufferedReader → fast

---

# 14. Internal Working

Steps:

1. Stream opened
2. Data loaded into buffer
3. Program reads from buffer
4. Buffer refills automatically

Pseudo flow:

```
File/Keyboard
     ↓
InputStream
     ↓
InputStreamReader
     ↓
BufferedReader Buffer
     ↓
Program
```

---

# 15. Buffer Example Visualization

If buffer size is:

```
8192 characters
```

Input file:

```
20000 characters
```

Process:

```
Read 8192 → process
Read next 8192 → process
Read remaining → process
```

Instead of **20000 reads**, only **3 reads** happen.

---

# 16. Common Errors

### Forgetting Exception Handling

BufferedReader throws:

**IOException**

Example fix:

```java
public static void main(String[] args) throws IOException
```

---

# 17. Best Practice Example

```java
import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {

        BufferedReader br =
            new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int[] arr = Arrays.stream(br.readLine().split(" "))
                         .mapToInt(Integer::parseInt)
                         .toArray();

        int sum = 0;

        for(int x : arr){
            sum += x;
        }

        System.out.println(sum);
    }
}
```

---

# 18. Advantages

- ✔ Very fast
- ✔ Efficient memory usage
- ✔ Best for large input
- ✔ Supports file reading

---

# 19. Disadvantages

✖ Only reads text
✖ No automatic parsing
✖ Requires manual conversion

---

# 20. Summary

BufferedReader is a **high-performance text input class** used for:

* Reading large input efficiently
* File processing
* Competitive programming

Main concept:

```
Buffer → fewer system calls → faster input
```

---

If you'd like, I can also explain **Fast I/O in Java (BufferedReader + StringTokenizer)** which is the **fastest input method used in competitive programming**.
