Here’s a **deep, structured explanation of `ArrayList` in Java** — covering **types, methods, internal working, and examples**.

---

# 🔷 1. What is `ArrayList`?

`ArrayList` is a **resizable array implementation** of the `List` interface in Java.

📦 Package:

```java
import java.util.ArrayList;
```

👉 Key properties:

* Dynamic size (grows automatically)
* Maintains insertion order
* Allows duplicates
* Fast random access (O(1))
* Slow insertion/deletion in middle (O(n))

---

# 🔷 2. Types of ArrayList (Based on Data)

## ✅ 1. Generic ArrayList (Most Used)

```java
ArrayList<Integer> list = new ArrayList<>();
ArrayList<String> list2 = new ArrayList<>();
```

## ✅ 2. Non-Generic (Not recommended)

```java
ArrayList list = new ArrayList();
list.add(10);
list.add("Hello");
```

## ✅ 3. Custom Object ArrayList

```java
class Student {
    int id;
    String name;
}

ArrayList<Student> students = new ArrayList<>();
```

---

# 🔷 3. Constructors of ArrayList

```java
ArrayList<String> list1 = new ArrayList<>();
ArrayList<String> list2 = new ArrayList<>(10); // initial capacity
ArrayList<String> list3 = new ArrayList<>(collection);
```

---

# 🔷 4. Important Methods of ArrayList

---

## 🔹 4.1 Adding Elements

```java
list.add("A");             // add at end
list.add(1, "B");          // add at index
list.addAll(list2);        // add all elements
list.addAll(1, list2);     // add at specific index
```

---

## 🔹 4.2 Accessing Elements

```java
list.get(0);   // get element at index
```

---

## 🔹 4.3 Updating Elements

```java
list.set(0, "New Value");
```

---

## 🔹 4.4 Removing Elements

```java
list.remove(0);           // remove by index
list.remove("A");         // remove by value
list.clear();             // remove all
list.removeAll(list2);    // remove matching elements
```

---

## 🔹 4.5 Searching

```java
list.contains("A");  // true/false
list.indexOf("A");   // first occurrence
list.lastIndexOf("A");
```

---

## 🔹 4.6 Size & Check

```java
list.size();       // number of elements
list.isEmpty();    // true if empty
```

---

## 🔹 4.7 Iteration Methods

### 🔸 Using for loop

```java
for(int i = 0; i < list.size(); i++) {
    System.out.println(list.get(i));
}
```

### 🔸 Enhanced for loop

```java
for(String s : list) {
    System.out.println(s);
}
```

### 🔸 Iterator

```java
Iterator<String> it = list.iterator();
while(it.hasNext()) {
    System.out.println(it.next());
}
```

### 🔸 ListIterator

```java
ListIterator<String> it = list.listIterator();
```

### 🔸 forEach (Java 8)

```java
list.forEach(System.out::println);
```

---

## 🔹 4.8 Sorting

```java
Collections.sort(list);                  // ascending
Collections.sort(list, Collections.reverseOrder()); // descending
```

---

## 🔹 4.9 Conversion

```java
Object[] arr = list.toArray();
String[] arr2 = list.toArray(new String[0]);
```

---

## 🔹 4.10 Capacity Methods (Important for Interview)

```java
list.ensureCapacity(50);  // increase capacity
list.trimToSize();        // reduce capacity
```

---

## 🔹 4.11 Sublist

```java
List<String> sub = list.subList(1, 3);
```

---

## 🔹 4.12 Clone

```java
ArrayList<String> newList = (ArrayList<String>) list.clone();
```

---

# 🔷 5. Internal Working (Very Important 🔥)

👉 `ArrayList` internally uses:

```java
transient Object[] elementData;
```

### ✔ Default capacity:

* Initially: **0**
* After first add: **10**

### ✔ Growth formula:

```
newCapacity = oldCapacity + (oldCapacity / 2)
```

➡ 10 → 15 → 22 → 33 → ...

---

# 🔷 6. Time Complexity

| Operation  | Complexity |
| ---------- | ---------- |
| get()      | O(1)       |
| add()      | O(1)*      |
| add(index) | O(n)       |
| remove()   | O(n)       |
| search     | O(n)       |

---

# 🔷 7. Example (Full Code)

```java
import java.util.*;

public class Main {
    public static void main(String[] args) {

        ArrayList<String> list = new ArrayList<>();

        list.add("Apple");
        list.add("Banana");
        list.add("Mango");

        list.add(1, "Orange");

        System.out.println(list);

        list.remove("Banana");

        list.set(0, "Grapes");

        for(String s : list) {
            System.out.println(s);
        }

        Collections.sort(list);

        System.out.println("Sorted: " + list);
    }
}
```

---

# 🔷 8. Key Differences: Array vs ArrayList

| Feature     | Array              | ArrayList       |
| ----------- | ------------------ | --------------- |
| Size        | Fixed              | Dynamic         |
| Data Type   | Primitive + Object | Only Objects    |
| Performance | Faster             | Slight overhead |
| Flexibility | Low                | High            |

---

# 🔷 9. Interview Questions 🔥

* Why ArrayList is not synchronized?
* Difference between ArrayList & LinkedList?
* How ArrayList grows internally?
* Why insertion in middle is slow?
* Fail-fast vs fail-safe iterator?

---

# 🔥 Final Summary

👉 `ArrayList` is:

* Dynamic array
* Best for **read-heavy operations**
* Not good for frequent insert/delete in middle

---

If you want, I can next:
✅ Explain **ArrayList vs LinkedList deeply**
✅ Give **50 interview questions with answers**
✅ Or show **real-world use cases (projects)**
