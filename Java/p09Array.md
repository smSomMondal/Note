In Java, **arrays are objects** but they are **not classes like `ArrayList`**, so they have **very few built-in properties and no direct methods**. Most operations on arrays are done using the **`java.util.Arrays` utility class**.

Below is a **complete explanation of Java Array properties and useful methods**.

---

# 1. Array Properties in Java

Java arrays have **only one direct property**.

| Property | Description                                 |
| -------- | ------------------------------------------- |
| `length` | Returns the number of elements in the array |

### Example

```java
public class Main {
    public static void main(String[] args) {

        int[] arr = {10,20,30,40,50};

        System.out.println(arr.length);
    }
}
```

Output

```
5
```

Important points:

* `length` is **not a method**
* Do **not use `arr.length()`**
* Correct syntax: `arr.length`

---

# 2. Important Methods for Arrays (java.util.Arrays)

The **`Arrays` class** provides many useful methods for working with arrays.

Import it:

```java
import java.util.Arrays;
```

---

# 3. Arrays.toString()

Converts array to readable string.

### Example

```java
int[] arr = {1,2,3,4};

System.out.println(Arrays.toString(arr));
```

Output

```
[1, 2, 3, 4]
```

---

# 4. Arrays.sort()

Sorts the array in ascending order.

### Example

```java
int[] arr = {5,2,8,1};

Arrays.sort(arr);

System.out.println(Arrays.toString(arr));

Arrays.sort(numbers, (a, b) -> b - a);
        
System.out.println(Arrays.toString(numbers)); // [10, 8, 5, 2, 1]

int[] sortedArray = Arrays.stream(myIntArray)
                          .boxed()
                          .sorted(Collections.reverseOrder())
                          .mapToInt(Integer::intValue)
                          .toArray();
```

Output

```
[1, 2, 5, 8]
```

---

# 5. Arrays.binarySearch()

Searches element in **sorted array** using **binary search**.

### Example

```java
int[] arr = {1,2,3,4,5};

int index = Arrays.binarySearch(arr, 3);

System.out.println(index);
```

Output

```
2
```

---

# 6. Arrays.equals()

Compares two arrays.

### Example

```java
int[] a = {1,2,3};
int[] b = {1,2,3};

System.out.println(Arrays.equals(a,b));
```

Output

```
true
```

---

# 7. Arrays.fill()

Fills the array with a specific value.

### Example

```java
int[] arr = new int[5];

Arrays.fill(arr, 7);

System.out.println(Arrays.toString(arr));
```

Output

```
[7, 7, 7, 7, 7]
```

---

# 8. Arrays.copyOf()

Copies array into a new array.

### Example

```java
int[] arr = {1,2,3};

int[] copy = Arrays.copyOf(arr, 5);

System.out.println(Arrays.toString(copy));
```

Output

```
[1, 2, 3, 0, 0]
```

---

# 9. Arrays.copyOfRange()

Copies a specific range.

### Example

```java
int[] arr = {1,2,3,4,5};

int[] copy = Arrays.copyOfRange(arr,1,4);

System.out.println(Arrays.toString(copy));
```

Output

```
[2, 3, 4]
```

---

# 10. Arrays.deepToString()

Used for **multidimensional arrays**.

### Example

```java
int[][] arr = {
        {1,2},
        {3,4}
};

System.out.println(Arrays.deepToString(arr));
```

Output

```
[[1, 2], [3, 4]]
```

---

# 11. Arrays.deepEquals()

Compares **multidimensional arrays**.

### Example

```java
int[][] a = {{1,2},{3,4}};
int[][] b = {{1,2},{3,4}};

System.out.println(Arrays.deepEquals(a,b));
```

Output

```
true
```

---

# 12. Arrays.parallelSort()

Sorts arrays using **parallel processing (multithreading)**.

### Example

```java
int[] arr = {9,3,5,1};

Arrays.parallelSort(arr);

System.out.println(Arrays.toString(arr));
```

Output

```
[1, 3, 5, 9]
```

---

# 13. Arrays.stream()

Converts array to **Stream API**.

### Example

```java
int[] arr = {1,2,3,4};

int sum = Arrays.stream(arr).sum();

System.out.println(sum);
```

Output

```
10
```

---

# 14. Arrays.asList()

Converts array to **List**.

### Example

```java
String[] arr = {"A","B","C"};

List<String> list = Arrays.asList(arr);

System.out.println(list);
```

Output

```
[A, B, C]
```

---

# 15. Arrays.mismatch() (Java 9+)

Finds first index where arrays differ.

### Example

```java
int[] a = {1,2,3};
int[] b = {1,2,4};

System.out.println(Arrays.mismatch(a,b));
```

Output

```
2
```

---

# 16. Arrays.compare()

Compares arrays lexicographically.

### Example

```java
int[] a = {1,2,3};
int[] b = {1,2,4};

System.out.println(Arrays.compare(a,b));
```

Output

```
-1
```

---

# 17. Arrays.setAll()

Sets elements using a function.

### Example

```java
int[] arr = new int[5];

Arrays.setAll(arr, i -> i * 2);

System.out.println(Arrays.toString(arr));
```

Output

```
[0, 2, 4, 6, 8]
```

---

# Complete Summary Table

| Property / Method       | Description               |
| ----------------------- | ------------------------- |
| `length`                | Size of array             |
| `Arrays.sort()`         | Sort array                |
| `Arrays.toString()`     | Convert to string         |
| `Arrays.binarySearch()` | Search element            |
| `Arrays.equals()`       | Compare arrays            |
| `Arrays.fill()`         | Fill with value           |
| `Arrays.copyOf()`       | Copy array                |
| `Arrays.copyOfRange()`  | Copy range                |
| `Arrays.deepToString()` | Print 2D array            |
| `Arrays.deepEquals()`   | Compare 2D arrays         |
| `Arrays.parallelSort()` | Multithread sorting       |
| `Arrays.stream()`       | Convert to Stream         |
| `Arrays.asList()`       | Convert to List           |
| `Arrays.mismatch()`     | Find difference           |
| `Arrays.compare()`      | Compare arrays            |
| `Arrays.setAll()`       | Initialize using function |

---

💡 **Important Interview Point**

Java arrays have **only one property**:

```
array.length
```

Everything else is handled by:

```
java.util.Arrays class
```

---

If you want, I can also show you **30 most important Java array interview problems (with solutions)** that are commonly asked in coding interviews and competitive programming.
